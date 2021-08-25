package com.meishubao.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Pipeline是redis提供的一种通道功能，通常使用pipeline的地方是不需要等待结果立即返回的时候，使用pipeline的好处是处理数据的速度
 * 更快因为它专门开辟了一个管道来处理数据(具体的对比可以参考网上的文章)，它是单向的，从客户端向服务端发送数据，当管道关闭链接时将会从
 * 服务端返回数据，再次期间是无法获取到服务端的数据的。
 *
 * @author lilu
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class PipelineTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testPipeLine() {
        redisTemplate.opsForValue().set("a", 1);
        redisTemplate.opsForValue().set("b", 2);
        redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.openPipeline();
                for (int i = 0; i < 10; i++) {
                    redisConnection.incr("a".getBytes());
                }
                System.out.println("a:" + redisTemplate.opsForValue().get("a"));
                redisTemplate.opsForValue().set("c", 3);
                for (int j = 0; j < 20; j++) {
                    redisConnection.incr("b".getBytes());
                }
                System.out.println("b:" + redisTemplate.opsForValue().get("b"));
                System.out.println("c:" + redisTemplate.opsForValue().get("c"));
                redisConnection.closePipeline();
                return null;
            }
        });
        System.out.println("b:" + redisTemplate.opsForValue().get("b"));
        System.out.println("a:" + redisTemplate.opsForValue().get("a"));
    }

}
