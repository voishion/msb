package com.meishubao.redis.listener;

import cn.hutool.core.util.StrUtil;
import com.meishubao.redis.listener.event.RedisKeyUpdateEvent;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyspaceEventMessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * MessageListener通过ApplicationEventPublisher发布RedisKeyUpdateEvents，通过监听Redis keyspace的键新增/修改通知
 *
 * @author lilu
 */
@Log4j2
@Component
public class RedisKeyUpdateListener extends KeyspaceEventMessageListener implements ApplicationEventPublisherAware {

    @Value("${spring.redis.database}")
    private String database;

    private static final String DB_PATTERN = "__keyevent@{}__:set";

    @Nullable
    private ApplicationEventPublisher publisher;

    public RedisKeyUpdateListener(RedisMessageListenerContainer redisMessageListenerContainer) {
        super(redisMessageListenerContainer);
    }

    @Override
    protected void doRegister(RedisMessageListenerContainer listenerContainer) {
        listenerContainer.addMessageListener(this, new PatternTopic(StrUtil.format(DB_PATTERN, database)));
    }

    protected void doHandleMessage(Message message) {
        publishEvent(new RedisKeyUpdateEvent(message.getBody()));
    }

    protected void publishEvent(RedisKeyUpdateEvent event) {
        if (publisher != null) {
            publisher.publishEvent(event);
        }
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        publisher = applicationEventPublisher;
    }

    @Override
    public void onMessage(Message message, @Nullable byte[] pattern) {
        log.info("key:{}", message.toString());
        byte[] body = message.getBody();
        byte[] channel = message.getChannel();
        log.info("channel:{}, body:{}, bytes:{}", new String(channel), new String(body), new String(pattern));
    }

}
