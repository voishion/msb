package com.meishubao.rabbitmq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meishubao.rabbitmq.domain.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lilu
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
