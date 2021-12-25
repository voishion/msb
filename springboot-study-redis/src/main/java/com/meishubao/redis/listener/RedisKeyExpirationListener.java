package com.meishubao.redis.listener;

import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * Redis过期回调监听方法
 *
 * @author lilu
 */
@Log4j2
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    @Value("${spring.redis.database}")
    private String database;

    private static final String DB_PATTERN = "__keyevent@{}__:expired";

    public RedisKeyExpirationListener(RedisMessageListenerContainer redisMessageListenerContainer) {
        super(redisMessageListenerContainer);
    }

    @Override
    protected void doRegister(RedisMessageListenerContainer listenerContainer) {
        listenerContainer.addMessageListener(this, new PatternTopic(StrUtil.format(DB_PATTERN, database)));
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        log.info("key:{}", message.toString());
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();
        log.info("channel:{}, body:{}, bytes:{}", new String(channel), new String(body), new String(pattern));
    }

}
