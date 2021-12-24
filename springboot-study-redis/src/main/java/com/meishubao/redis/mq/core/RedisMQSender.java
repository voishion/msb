package com.meishubao.redis.mq.core;

import cn.hutool.core.util.StrUtil;
import com.meishubao.redis.mq.RedisMQMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.Objects;

/**
 * Redis队列消息发送器
 *
 * @author lilu
 */
@Log4j2
public class RedisMQSender {

    private final RedisTemplate redisTemplate;

    public RedisMQSender(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void send(String queueName, Serializable message) {
        if (StrUtil.isBlank(queueName) || Objects.isNull(message)) {
            return;
        }
        RedisMQMessage redisMQMessage = new RedisMQMessage();
        redisMQMessage.setQueueName(queueName);
        redisMQMessage.setCreateTime(System.currentTimeMillis());
        redisMQMessage.setPayload(message);

        Long length = redisTemplate.opsForList().rightPush(queueName, redisMQMessage);
        log.info("发送Redis队列消息，发送后队列长度:{}，队列名称:{}，消息内容:{}", length, queueName, redisMQMessage);
    }

}
