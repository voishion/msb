package com.meishubao.redis.mq.core;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.Throwables;
import com.meishubao.redis.mq.RedisMQListenerTarget;
import com.meishubao.redis.mq.RedisMQMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author lilu
 */
@Log4j2
public class RedisMQRegister implements ApplicationRunner, ApplicationContextAware {

    private static final String THREAD_PREFIX = "redis-mq-thread-";

    private final Set<String> registerQueueName = new HashSet<>();

    private final List<Thread> registerQueueThreads = new ArrayList<>();

    private final RedisTemplate redisTemplate;

    private ApplicationContext applicationContext;

    public RedisMQRegister(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initializer();
        for (String queueName : registerQueueName) {
            Thread thread = new Thread(new Worker(queueName), THREAD_PREFIX + queueName);
            registerQueueThreads.add(thread);
            thread.start();
            log.info("启动消息队列监听器【{}】", queueName);
        }
    }

    private void initializer() {
        List<RedisMQListenerTarget> redisMQListenerTargets = RedisMQListenerScanner.getRedisListenerTargets();
        if (CollUtil.isNotEmpty(redisMQListenerTargets)) {
            for (RedisMQListenerTarget redisMQListenerTarget : redisMQListenerTargets) {
                registerQueueName.add(redisMQListenerTarget.getQueueName());
            }
        }
    }

    private class Worker implements Runnable {

        private final String queueName;

        private final String queueNameBak;

        private List<RedisMQListenerTarget> consumerTargets = new ArrayList<>();

        /**
         * 阻塞连接超时时间，一定要比${spring.redis.timeout}配置的时间短，否是会出现连接超时异常
         * 10秒
         */
        private int TIME_OUT = 10;

        private Worker(String queueName) {
            this.queueName = queueName;
            this.queueNameBak = queueName + ":bak";
            initConsumerTargets();
            checkNeedRecoverMessage();
        }

        private void initConsumerTargets() {
            if (consumerTargets.size() == 0) {
                List<RedisMQListenerTarget> redisMQListenerTargets = RedisMQListenerScanner.getRedisListenerTargets();
                consumerTargets.addAll(redisMQListenerTargets.stream().filter(f -> f.match(queueName)).collect(Collectors.toList()));
            }
        }

        @Override
        public void run() {
            if (StrUtil.isBlank(queueName)) {
                return;
            }
            while (true) {
                RedisMQMessage message = null;
                try {
                    // 删除并返回存储在key处的列表中的第一个元素。阻塞连接，直到元素可用或超时，可靠消费
                    message = (RedisMQMessage) redisTemplate.opsForList().rightPopAndLeftPush(queueName, queueNameBak, TIME_OUT, TimeUnit.SECONDS);
                    if (Objects.nonNull(message)) {
                        handleMessage(message);
                        clearBakMessage();
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    log.error("消息队列【{}】处理消息时异常, error=>{}", queueName, Throwables.getStackTraceAsString(e));
                    if (Objects.nonNull(message)) {
                        recoverMessage();
                    }
                }
            }
        }

        private void handleMessage(RedisMQMessage message) throws IllegalAccessException, InvocationTargetException {
            if (Objects.nonNull(message) && consumerTargets.size() > 0) {
                for (RedisMQListenerTarget target : consumerTargets) {
                    Method targetMethod = target.getMethod();
                    if (target.getMethodParameterClassName().equals(RedisMQMessage.class.getName())) {
                        targetMethod.invoke(target.getBean(applicationContext), message);
                    } else if (target.getMethodParameterClassName().equalsIgnoreCase(message.getPayload().getClass().getName())) {
                        targetMethod.invoke(target.getBean(applicationContext), message.getPayload());
                    } else {
                        throw new RedisMQException(StrUtil.format("消息队列【{}】中的消息类型与【{}】方法中定义的消息类型不一致", queueName, targetMethod.getName()));
                    }
                }
            }
        }

        private void checkNeedRecoverMessage() {
            while (redisTemplate.opsForList().size(queueNameBak) > 0) {
               recoverMessage();
            }
        }

        private void recoverMessage() {
            redisTemplate.opsForList().leftPush(queueName, redisTemplate.opsForList().leftPop(queueNameBak));
        }

        private void clearBakMessage() {
            redisTemplate.opsForList().leftPop(queueNameBak);
        }

    }

}
