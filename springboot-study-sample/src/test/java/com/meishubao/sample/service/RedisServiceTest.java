package com.meishubao.sample.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.meishubao.sample.model.entity.User;
import com.meishubao.sample.utils.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    String KEY = "SPRINGBOOT_STUDY_SAMPLE:REDIS_SERVICE_TEST:USER";

    @Test
    void set() {
        List<User> list = userService.list();
        if (CollUtil.isNotEmpty(list)) {
            list.forEach(x -> redisService.hset(KEY, String.valueOf(x.getId()), x));
            redisService.expire(KEY, 15, TimeUnit.MINUTES);
            System.out.println("存储完成");
        } else {
            System.out.println("没有数据");
        }
    }

    @Test
    void get() {
        Map<Object, Object> map = redisService.hgetall(KEY);
        if (MapUtil.isNotEmpty(map)) {
            map.forEach((k, v) -> System.out.println(StrUtil.format("key:{}, value:{}", k, JsonUtils.toJson(v))));
            System.out.println("展示完成");
        } else {
            System.out.println("没有数据");
        }
    }

}