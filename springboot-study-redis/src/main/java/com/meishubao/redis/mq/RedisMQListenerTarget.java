package com.meishubao.redis.mq;

import cn.hutool.core.util.StrUtil;
import com.meishubao.redis.mq.core.RedisMQException;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 监听目标实体
 *
 * @author lilu
 */
@Data
@Accessors(chain = true)
public class RedisMQListenerTarget {

    /**
     * Bean对象
     */
    private Object bean;
    /**
     * Bean名称
     */
    private String beanName;
    /**
     * 队列名称
     */
    private String queueName;
    /**
     * 目标方法
     */
    private Method method;
    /**
     * 目标方法参数类名
     */
    private String methodParameterClassName;

    public boolean match(String queueName) {
        return StrUtil.equals(this.queueName, queueName);
    }

    public Object getBean(ApplicationContext applicationContext) {
        if (Objects.isNull(bean)) {
            synchronized (this) {
                if (Objects.isNull(bean)) {
                    bean = applicationContext.getBean(beanName);
                    if (Objects.isNull(bean)) {
                        throw new RedisMQException(StrUtil.format("获取包含@RedisMQListener【{}】方法的Bean实例失败", method.getName()));
                    }
                }
            }
        }
        return bean;
    }

}
