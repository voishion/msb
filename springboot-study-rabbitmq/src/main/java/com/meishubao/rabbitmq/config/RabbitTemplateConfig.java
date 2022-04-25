package com.meishubao.rabbitmq.config;

import cn.hutool.core.util.StrUtil;
import com.meishubao.rabbitmq.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import xin.altitude.cms.common.util.RabbitUtils;
import xin.altitude.cms.common.util.RedisUtils;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 使用confirm确认机制，保证延迟任务的实现对生产者的要求是将消息可靠的投递到交换机
 *
 * @author lilu
 */
@Component
@RequiredArgsConstructor
public class RabbitTemplateConfig implements RabbitTemplate.ConfirmCallback {

    public static final String ORDER_KEY = "meishubao:order:{}";

    private final RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * 使用confirm确认，必须提供CorrelationData消息ID信息
     *
     * @param correlationData 相关性数据
     * @param ack             消息投递状态
     * @param cause           可选项
     */
    @Override
    public void confirm(@Nullable CorrelationData correlationData, boolean ack, @Nullable String cause) {
        if (Objects.isNull(correlationData)) {
            return;
        }
        String key = StrUtil.format(ORDER_KEY, correlationData.getId());
        if (ack) {
            /* 如果消息投递成功，则删除Redis中订单数据，回收内存 */
            RedisUtils.deleteObject(key);
        } else {
            /* 从Redis中读取订单数据，重新投递 */
            Order order = RedisUtils.getObject(key, Order.class);
            /* 重新投递消息 */
            rabbitTemplate.convertAndSend(RabbitmqConfig.DELAY_EXCHANGE_NAME, RabbitmqConfig.DELAY_KEY, order, RabbitUtils.setDelay((int) TimeUnit.SECONDS.toMillis(30)), RabbitUtils.correlationData(order.getOrderId()));
        }
    }

}
