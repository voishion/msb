package com.meishubao.rabbitmq.controller;

import com.meishubao.rabbitmq.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.altitude.cms.common.entity.AjaxResult;

/**
 * @author lilu
 */
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/batchGenerateOrder")
    public AjaxResult batchGenerateOrder() {
        for (long i = 1; i <= 10; i++) {
            orderService.generateOrder(i);
        }
        return AjaxResult.success("处理成功");
    }

    @GetMapping("/singleGenerateOrder/{orderId}")
    public AjaxResult singleGenerateOrder(@PathVariable("orderId") Long orderId) {
        orderService.generateOrder(orderId);
        return AjaxResult.success("处理成功");
    }

}
