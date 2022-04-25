package com.meishubao.rabbitmq.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void selectTest() {
        orderService.selectTest();
    }
    @Test
    public void createOrderTest() {
        for (long i = 1; i <= 10; i++) {
            orderService.generateOrder(i);
        }
    }

}