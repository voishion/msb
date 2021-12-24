package com.meishubao.redis.mq;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Redis MQ消息包装实体
 *
 * @author lilu
 */
@Data
@Accessors(chain = true)
public class RedisMQMessage<T> implements Serializable {

    /**
     * 队列名称
     */
    private String queueName;
    /**
     * 消息生成时间
     */
    private Long createTime;
    /**
     * 数据载体
     */
    private T payload;

}
