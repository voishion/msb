package com.meishubao.rabbitmq.controller;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.meishubao.rabbitmq.config.RabbitTemplateConfig;
import com.meishubao.rabbitmq.config.RabbitmqConfig;
import com.meishubao.rabbitmq.domain.Order;
import com.meishubao.rabbitmq.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.util.RabbitUtils;
import xin.altitude.cms.common.util.RedisUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author lilu
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final RabbitTemplate rabbitTemplate;

    @GetMapping("/batchGenerateOrder")
    public AjaxResult batchGenerateOrder() {
        for (long i = 1; i <= 10; i++) {
            generateOrder(i);
        }
        return AjaxResult.success("处理成功");
    }

    @GetMapping("/singleGenerateOrder/{orderId}")
    public AjaxResult singleGenerateOrder(@PathVariable("orderId") Long orderId) {
        generateOrder(orderId);
        return AjaxResult.success("处理成功");
    }

    private void generateOrder(long i) {
        /* 1.模拟生成订单 */
        Order order = createOrder(i);
        /* 2.订单入库 */
        orderService.removeById(order);
        orderService.saveOrUpdate(order);
        /* 3.将订单存入信息Redis */
        RedisUtils.setObject(StrUtil.format(RabbitTemplateConfig.ORDER_KEY, i), order);
        /* 4.向RabbitMQ异步投递消息 */
        rabbitTemplate.convertAndSend(RabbitmqConfig.DELAY_EXCHANGE_NAME, RabbitmqConfig.DELAY_KEY, order, RabbitUtils.setDelay((int) TimeUnit.MINUTES.toMillis(3)), RabbitUtils.correlationData(order.getOrderId()));
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
