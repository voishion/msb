package com.meishubao.rabbitmq.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meishubao.rabbitmq.domain.Order;
import com.meishubao.rabbitmq.mapper.OrderMapper;
import com.meishubao.rabbitmq.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author lilu
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
}
