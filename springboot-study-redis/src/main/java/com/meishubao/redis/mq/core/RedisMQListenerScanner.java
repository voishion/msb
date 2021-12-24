package com.meishubao.redis.mq.core;

import cn.hutool.core.util.StrUtil;
import com.meishubao.redis.mq.RedisMQListener;
import com.meishubao.redis.mq.RedisMQListenerTarget;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.lang.Nullable;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Redis消息队列监听扫描器，扫描{@code @RedisListener}注释并注册到系统中
 *
 * @author lilu
 */
public class RedisMQListenerScanner implements BeanPostProcessor {

    private static final List<RedisMQListenerTarget> REDIS_LISTENER_TARGETS = new ArrayList<>();

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        Method[] methods = ReflectionUtils.getAllDeclaredMethods(clazz);
        if (ArrayUtils.isNotEmpty(methods)) {
            for (Method method : methods) {
                AnnotationAttributes attributes = AnnotatedElementUtils.getMergedAnnotationAttributes(method, RedisMQListener.class.getName(), false, false);
                if (Objects.nonNull(attributes)) {
                    Class<?>[] types = method.getParameterTypes();
                    if (1 == types.length) {
                        String queueName = attributes.getString("queueName");
                        if (StrUtil.isEmpty(queueName)) {
                            throw new RedisMQException(StrUtil.format("@RedisMQListener没有设置[queueName]队列名称, 类名:{}, 方法名:{}", clazz.getSimpleName(), method.getName()));
                        }
                        REDIS_LISTENER_TARGETS.add(new RedisMQListenerTarget()
                                .setBeanName(beanName)
                                .setQueueName(queueName)
                                .setMethod(method)
                                .setMethodParameterClassName(types[0].getName())
                        );
                    } else {
                        throw new RedisMQException(StrUtil.format("@RedisMQListener注解方法有且仅有一个参数, 类名:{}, 方法名:{}", clazz.getSimpleName(), method.getName()));
                    }
                }
            }
        }
        return bean;
    }

    public static List<RedisMQListenerTarget> getRedisListenerTargets() {
        return REDIS_LISTENER_TARGETS;
    }

}
