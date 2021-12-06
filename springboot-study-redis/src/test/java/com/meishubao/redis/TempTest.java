package com.meishubao.redis;

import com.meishubao.redis.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @author lilu
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class TempTest {

    @Autowired
    private com.meishubao.redis.service.RedisService redisService;

    String key = "temp_test";

    @Test
    public void set() {
        Person person = new Person();
        person.setId(123456789L);
        person.setMtime(LocalDateTime.now());
//        person.setAge(32);
        redisService.set(key, person);

        get();
    }

    @Test
    public void get() {
        Person person = (Person) redisService.get(key);
        System.out.println(person);
    }

}
