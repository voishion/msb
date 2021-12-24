package com.meishubao.redis.mq;

import com.meishubao.redis.mq.core.RedisMQListenerScanner;
import com.meishubao.redis.mq.core.RedisMQRegister;
import com.meishubao.redis.mq.core.RedisMQSender;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author lilu
 */
@Configuration
@ConditionalOnBean(RedisTemplate.class)
@ConditionalOnProperty(prefix = "redis.queue.listener", name = "enable", havingValue = "true", matchIfMissing = true)
@RequiredArgsConstructor
public class RedisMQConfig {

    private final RedisTemplate redisTemplate;

    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    @ConditionalOnProperty(name = "redis.mq.producer",havingValue = "true")
    public RedisMQSender redisMQSender() {
        return new RedisMQSender(redisTemplate);
    }

    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    @ConditionalOnProperty(name = "redis.mq.consumer",havingValue = "true")
    public RedisMQListenerScanner redisMQListenerScanner() {
        return new RedisMQListenerScanner();
    }

    @Bean
    @ConditionalOnBean(RedisMQListenerScanner.class)
    @ConditionalOnProperty(name = "redis.mq.consumer",havingValue = "true")
    public RedisMQRegister redisMQRegister() {
        return new RedisMQRegister(redisTemplate);
    }

}
