package com.meishubao.rabbitmq.consumer;

import com.meishubao.rabbitmq.config.RabbitmqConfig;
import com.meishubao.rabbitmq.domain.Order;
import com.meishubao.rabbitmq.service.OrderService;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

/**
 * @author lilu
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OrderConsumer {

    private final OrderService orderService;

    /**
     * 模拟监听消息队列
     *
     * @param channel
     * @param message
     * @param order
     */
    @RabbitListener(queues = RabbitmqConfig.DELAY_QUEUE_NAME)
    public void consumeNode01(Channel channel, Message message, Order order) throws IOException {
        if (Objects.equals(0, order.getOrderStatus())) {
            /* 修改订单状态，设置为关闭状态 */
            orderService.updateById(new Order(order.getOrderId(), -1));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("消费者节点01消费编号为【{}】的消息", order.getOrderId());
        }
    }

    /**
     * 模拟监听消息队列
     *
     * @param channel
     * @param message
     * @param order
     */
    @RabbitListener(queues = RabbitmqConfig.DELAY_QUEUE_NAME)
    public void consumeNode02(Channel channel, Message message, Order order) throws IOException {
        if (Objects.equals(0, order.getOrderStatus())) {
            /* 修改订单状态，设置为关闭状态 */
            orderService.updateById(new Order(order.getOrderId(), -1));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("消费者节点02消费编号为【{}】的消息", order.getOrderId());
        }
    }

}
