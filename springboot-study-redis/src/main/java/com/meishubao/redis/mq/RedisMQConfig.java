package com.meishubao.redis.mq;

import com.meishubao.redis.mq.core.RedisMQListenerScanner;
import com.meishubao.redis.mq.core.RedisMQRegister;
import com.meishubao.redis.mq.core.RedisMQSender;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author lilu
 */
@Configuration
@AutoConfigureBefore(RedisAutoConfiguration.class)
@RequiredArgsConstructor
public class RedisMQConfig {

    private final RedisTemplate redisTemplate;

    @Bean
    @ConditionalOnProperty(name = "spring.redis.message-queue.producer",havingValue = "true", matchIfMissing = false)
    public RedisMQSender redisMQSender() {
        return new RedisMQSender(redisTemplate);
    }

    @Bean
    @ConditionalOnProperty(name = "spring.redis.message-queue.consumer",havingValue = "true", matchIfMissing = false)
    public RedisMQListenerScanner redisMQListenerScanner() {
        return new RedisMQListenerScanner();
    }

    @Bean
    @ConditionalOnBean(RedisMQListenerScanner.class)
    @ConditionalOnProperty(name = "spring.redis.message-queue.consumer",havingValue = "true", matchIfMissing = false)
    public RedisMQRegister redisMQRegister() {
        return new RedisMQRegister(redisTemplate);
    }

}
