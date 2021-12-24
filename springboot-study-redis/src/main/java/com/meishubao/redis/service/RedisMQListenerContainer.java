package com.meishubao.redis.service;

import com.meishubao.redis.constant.RedisMQConstant;
import com.meishubao.redis.mq.RedisMQListener;
import com.meishubao.redis.mq.RedisMQMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * @author lilu
 */
@Log4j2
@Component
public class RedisMQListenerContainer {

    @RedisMQListener(RedisMQConstant.TEST_QUEUE)
    public void dealRedisMessage0(RedisMQMessage message) {
        log.info("dealRedisMessage0收到queue-1队列消息: {}", message);
    }

    @RedisMQListener(RedisMQConstant.TEST_QUEUE)
    public void dealRedisMessage1(String message) {
        log.info("dealRedisMessage0收到queue-1队列消息: {}", message);
    }

    @RedisMQListener(RedisMQConstant.TEST_QUEUE_2)
    public void dealRedisMessage2(RedisMQMessage<String> message) {
        log.info("dealRedisMessage0收到queue-2队列消息: {}", message);
    }

}
