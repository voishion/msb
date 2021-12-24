package com.meishubao.redis.listener;

import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Redis Key过期订阅<br>
 * 实现延时消息队列功能
 *
 * @author lilu
 */
//@Log4j2
//@Component
//@RequiredArgsConstructor
public class RedisKeyExpirationSubscribe implements CommandLineRunner {

    /**
     * 新增/修改事件
     */
    private final static String DB_PATTERN_SET = "__keyevent@{}__:set";

    /**
     * 过期事件
     */
    private final static String DB_PATTERN_EXPIRED = "__keyevent@{}__:expired";

    private RedisTemplate redisTemplate;

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
            }, StrUtil.format(DB_PATTERN_SET, redisDatabase).getBytes());
            return null;
        });
        /*redisTemplate.execute((RedisCallback) connection -> {
            connection.pSubscribe((message, pattern) -> {
                byte[] body = message.getBody();
                byte[] channel = message.getChannel();
                System.out.print("onMessage >> ");
                System.out.println(String.format("key: %s, channel: %s, bytes: %s", new String(body), new String(channel), new String(pattern)));
            }, StrUtil.format(DB_PATTERN_EXPIRED, redisDatabase).getBytes());
            return null;
        });*/
    }

}