package com.meishubao.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lilu
 */
@Configuration
public class RabbitmqConfig {

    /**
     * 延迟队列路由KEY
     */
    public static final String DELAY_KEY = "meishubao.delay.key";

    /**
     * 延迟队列名称
     */
    public static final String DELAY_QUEUE_NAME = "meishubao.delay.queue";

    /**
     * 延迟交换器名称
     */
    public static final String DELAY_EXCHANGE_NAME = "meishubao.delay.exchange";

    /**
     * 自定义延时交换机，注意这里的交换机类型：CustomExchange
     *
     * @return 自定义延时交换机
     */
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>(16);
        args.put("x-delayed-type", "direct");
        /* 属性参数 交换机名称 交换机类型 是否持久化 是否自动删除 配置参数 */
        return new CustomExchange(DELAY_EXCHANGE_NAME, "x-delayed-message", true, false, args);
    }

    /**
     * 指定延时队列
     *
     * @return 延时队列
     */
    @Bean
    public Queue delayQueue() {
        /* 属性参数 队列名称 是否持久化 */
        return new Queue(DELAY_QUEUE_NAME, true);
    }

    /**
     * 绑定延时队列的交换机
     *
     * @return 绑定关系
     */
    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(DELAY_KEY).noargs();
    }

}
