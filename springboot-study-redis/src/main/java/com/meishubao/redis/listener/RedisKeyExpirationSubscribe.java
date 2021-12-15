package com.meishubao.redis.listener;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Redis Key过期订阅<br>
 * 实现延时消息队列功能
 *
 * @author lilu
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class RedisKeyExpirationSubscribe implements CommandLineRunner {

    private final static String DB_PATTERN = "__keyevent@{}__:expired";

    private final RedisTemplate redisTemplate;

    @Value("${spring.redis.database}")
    private Integer redisDatabase;

    @Override
    public void run(String... strings) {
        redisTemplate.execute((RedisCallback) connection -> {
            connection.pSubscribe((message, pattern) -> {
                byte[] body = message.getBody();
                byte[] channel = message.getChannel();
                System.out.print("onMessage >> ");
                System.out.println(String.format("key: %s, channel: %s, bytes: %s", new String(body), new String(channel), new String(pattern)));
            }, StrUtil.format(DB_PATTERN, redisDatabase).getBytes());
            return null;
        });
    }

}