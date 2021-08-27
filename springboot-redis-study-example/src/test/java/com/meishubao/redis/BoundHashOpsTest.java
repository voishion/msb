package com.meishubao.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author lilu
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class BoundHashOpsTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test() throws InterruptedException {
        //给指定的建设置map值，不能为已有的bound参数绑定其他类型
        BoundHashOperations<String, String, Object> boundHashOperations = redisTemplate.boundHashOps("li");

        boundHashOperations.put("ww", "i");
        boundHashOperations.put("w1", "i1");
        boundHashOperations.put("w2", "i2");

        //获取设置的绑定key值
        System.out.println("获取设置的绑定key值:" + boundHashOperations.getKey());

        //获取map中的value值
        boundHashOperations.values().forEach(v -> System.out.println("获取map中的value值" + v));

        //获取map键值对
        boundHashOperations.entries().forEach((m, n) -> System.out.println("获取map键值对:" + m + "-" + n));

        //获取map键的值
        System.out.println("获取map建的值:" + boundHashOperations.get("w1"));

        //获取map的键
        boundHashOperations.keys().forEach(v -> System.out.println("获取map的键:" + v));

        //根据map键批量获取map值
        List list = new ArrayList<>(Arrays.asList("ww", "w1"));
        boundHashOperations.multiGet(list).forEach(v -> System.out.println("根据map键批量获取map值:" + v));

        //批量添加键值对
        Map map = new HashMap<>();
        map.put("m1", "n1");
        map.put("m2", "n2");
        boundHashOperations.putAll(map);
        boundHashOperations.entries().forEach((m, n) -> System.out.println("批量添加键值对:" + m + "-" + n));

        //自增长map键的值
        boundHashOperations.increment("c", 1);
        System.out.println("自增长map键的值:" + boundHashOperations.get("c"));

        //如果map键不存在，则新增，存在，则不变
        boundHashOperations.putIfAbsent("m2", "n2-1");
        boundHashOperations.putIfAbsent("m3", "n3");
        boundHashOperations.entries().forEach((m, n) -> System.out.println("新增不存在的键值对:" + m + "-" + n));

        //查看绑定建的map大小
        System.out.println("查看绑定建的map大小:" + boundHashOperations.size());

        //遍历绑定键获取所有值
        Cursor<Map.Entry<String, Object>> cursor = boundHashOperations.scan(ScanOptions.NONE);
        while (cursor.hasNext()) {
            Map.Entry<String, Object> entry = cursor.next();
            System.out.println("遍历绑定键获取所有值:" + entry.getKey() + "---" + entry.getValue());
        }

        long delSize = boundHashOperations.delete("m3", "m2");
        System.out.println("删除的键的个数:" + delSize);
        boundHashOperations.entries().forEach((m, n) -> System.out.println("删除后剩余map键值对:" + m + "-" + n));
    }

}
