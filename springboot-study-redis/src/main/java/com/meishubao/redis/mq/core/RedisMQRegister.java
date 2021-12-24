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

    private final static String THREAD_PREFIX = "redis-mq-thread-";

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

        private List<RedisMQListenerTarget> consumerTargets = new ArrayList<>();

        private Worker(String queueName) {
            this.queueName = queueName;
            initConsumerTargets();
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
                try {
                    RedisMQMessage message = (RedisMQMessage) redisTemplate.opsForList().leftPop(queueName, 0L, TimeUnit.SECONDS);
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
                } catch (IllegalAccessException | InvocationTargetException e) {
                    log.error("消息队列【{}】处理消息时异常, error=>{}", queueName, Throwables.getStackTraceAsString(e));
                }
            }
        }
    }

}
