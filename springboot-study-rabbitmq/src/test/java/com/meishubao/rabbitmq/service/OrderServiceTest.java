package com.meishubao.rabbitmq.service;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.meishubao.rabbitmq.config.RabbitTemplateConfig;
import com.meishubao.rabbitmq.config.RabbitmqConfig;
import com.meishubao.rabbitmq.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xin.altitude.cms.common.util.RabbitUtils;
import xin.altitude.cms.common.util.RedisUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void createOrderTest() {
        for (long i = 1; i <= 10; i++) {
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