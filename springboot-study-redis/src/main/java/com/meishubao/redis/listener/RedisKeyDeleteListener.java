package com.meishubao.redis.listener;

import com.meishubao.redis.listener.event.KeyDeleteEventMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RedisKeyDeleteListener extends KeyDeleteEventMessageListener {

    public RedisKeyDeleteListener(@Qualifier("redisMessageListenerContainer2") RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对redis数据删除事件，进行数据处理
     * @param message message.toString()获取失效的key
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println(message.toString());
        byte[] body = message.getBody();// 建议使用: valueSerializer
        byte[] channel = message.getChannel();
        System.out.print("onMessage >> " );
        System.out.println(String.format("channel: %s, body: %s, bytes: %s",new String(channel), new String(body), new String(pattern)));
        super.onMessage(message, pattern);
    }

}
