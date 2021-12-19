package com.meishubao.redis;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Lists;
import com.meishubao.redis.entity.Person;
import com.meishubao.redis.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lilu
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class TempTest {

    @Autowired
    private RedisService redisService;

    String key = "temp_test";
    String listKey = "listKey_test";

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

    @Test
    public void lineList() {
        addLineList();
    }

    /**
     * 插入画线队列，DOWN->MOVE(...)->UP
     */
    private void addLineList() {
        List<String> drawLine = new ArrayList(){{add("D");add("M");add("M");add("U");add("D");add("M");add("M");add("M");add("U");add("D");add("M");add("M");add("M");add("U");add("D");add("M");add("M");add("M");add("U");add("D");add("M");add("M");add("M");}};
        List<String> line = Lists.newArrayList(drawLine);
        line.forEach(p -> {
            redisService.lPush(listKey, p);
            if ("U".equals(p)) {
                drawLinePush();
            }
        });
        redisService.expire(listKey, 180, TimeUnit.MINUTES);
    }

    private void drawLinePush() {
        List<String> points = Lists.newArrayList();
        while (true) {
            String data = (String) redisService.leftPop(listKey);
            points.add(data);
            if ("U".equals(data)) {
                break;
            }
        }
        if (CollUtil.isEmpty(points)) {
            return;
        } else {
            System.out.println(points);
        }
    }

}
