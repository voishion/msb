package com.meishubao.rabbitmq.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author lilu
 */
@Data
@NoArgsConstructor
@TableName("bu_order")
public class Order extends Model<Order> {
    @TableId
    private Long orderId;
    private Long userId;
    private Integer orderStatus;
    private Integer payStatus;
    private BigDecimal orderAmount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
    public Order(Long orderId, Integer orderStatus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }

}
