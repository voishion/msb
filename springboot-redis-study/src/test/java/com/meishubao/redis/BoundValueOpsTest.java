package com.meishubao.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author lilu
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class BoundValueOpsTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test() throws InterruptedException {
        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps("bvo");
        boundValueOperations.append("a");
        boundValueOperations.append("b");

        //获取从指定位置开始，到指定位置为止的值
        System.out.println("获取从指定位置开始，到指定位置为止的值:" + boundValueOperations.get(0, 2));

        //获取所有值
        System.out.println("获取所有值:" + boundValueOperations.get());

        //重新设置值
        boundValueOperations.set("f");
        System.out.println("重新设置值:" + boundValueOperations.get());

        //在指定时间后重新设置
        boundValueOperations.set("wwww", 5, TimeUnit.SECONDS);
        System.out.println("在指定时间后重新设置:" + boundValueOperations.get());

        //截取原有值的指定长度后添加新值在后面
        boundValueOperations.set("nnnnnn", 3);
        System.out.println("截取原有值的指定长度后添加新值在后面:" + boundValueOperations.get());

        //没有值存在则添加
        boundValueOperations.setIfAbsent("mmm");
        System.out.println("没有值存在则添加:" + boundValueOperations.get());

        //获取原来的值，并覆盖为新值
        Object object = boundValueOperations.getAndSet("yyy");
        System.out.print("获取原来的值" + object);
        System.out.println("，覆盖为新值:" + boundValueOperations.get());

        //自增长只能在为数字类型的时候才可以
        //boundValueOperations.increment(1);
        //System.out.println("自增长只能在为数字类型的时候才可以:" + boundValueOperations.get());

        System.out.println("value值的长度:" + boundValueOperations.size());
    }

}
