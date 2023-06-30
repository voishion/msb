package com.meishubao.rabbitmq.service.impl;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meishubao.rabbitmq.config.RabbitTemplateConfig;
import com.meishubao.rabbitmq.config.RabbitmqConfig;
import com.meishubao.rabbitmq.domain.Order;
import com.meishubao.rabbitmq.mapper.OrderMapper;
import com.meishubao.rabbitmq.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import xin.altitude.cms.common.util.RabbitUtils;
import xin.altitude.cms.common.util.RedisUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lilu
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void selectTest() {
        LambdaQueryWrapper<Order> wrapper = Wrappers.<Order>lambdaQuery().select(Order::getOrderId, Order::getOrderStatus);
        List<Order> list = this.list(wrapper);
        log.info(String.valueOf(list));
    }

    @Override
    public void generateOrder(Long orderId) {
        /* 1.模拟生成订单 */
        Order order = createOrder(orderId);
        /* 2.订单入库 */
        removeById(order);
        saveOrUpdate(order);
        /* 3.将订单存入信息Redis */
        RedisUtils.setObject(StrUtil.format(RabbitTemplateConfig.ORDER_KEY, orderId), order);
        /* 4.向RabbitMQ异步投递消息 */
        rabbitTemplate.convertAndSend(RabbitmqConfig.DELAY_EXCHANGE_NAME, RabbitmqConfig.DELAY_KEY, order,
                RabbitUtils.setDelay((int) TimeUnit.MINUTES.toMillis(3)),
                RabbitUtils.correlationData(order.getOrderId()));
    }

    private Order createOrder(long i) {
        Order order = new Order();
        order.setOrderId(i);
        order.setUserId(RandomUtil.randomEle(Arrays.asList(101L, 102L, 103L)));
        //订单状态
        order.setOrderStatus(0);
        //未支付
        order.setPayStatus(0);
        order.setOrderAmount(BigDecimal.valueOf(RandomUtil.randomLong(1, 3) * 50));
        order.setCreateTime(LocalDateTime.now());
        order.setUpdateTime(null);
        ThreadUtil.sleep(1000);
        return order;
    }

}
