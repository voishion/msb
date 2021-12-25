package com.meishubao.redis;

import com.meishubao.redis.mq.core.RedisMQException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author lilu
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class ListTest {

    @Autowired
    private RedisTemplate redisTemplate;

    String listKey = "temp_test";
    String listBakKey = "temp_test_bak";

    @Test
    public void queue() {
        redisTemplate.opsForList().leftPush(listKey, 1);
        redisTemplate.opsForList().leftPush(listKey, 2);
        redisTemplate.opsForList().leftPush(listKey, 3);
        redisTemplate.opsForList().leftPush(listKey, 4);
        redisTemplate.opsForList().leftPush(listKey, 5);
        redisTemplate.opsForList().leftPush(listKey, 6);

        while (true) {
            //从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
            Object o = redisTemplate.opsForList().rightPopAndLeftPush(listKey, listBakKey, 5, TimeUnit.SECONDS);
            consume((Integer) o);
        }
    }

    void consume(Integer number) {
        try {
            System.out.println(number);
            if (number % 2 == 0) {
                throw new RedisMQException("");
            } else {
                redisTemplate.opsForList().rightPop(listBakKey);
            }
        } catch (Exception e) {
            Object o = redisTemplate.opsForList().rightPop(listBakKey);
            redisTemplate.opsForList().leftPush(listKey, o);
        }
    }

}
