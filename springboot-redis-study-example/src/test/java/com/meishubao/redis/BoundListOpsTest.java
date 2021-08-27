package com.meishubao.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author lilu
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class BoundListOpsTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test() throws InterruptedException {
        //定义绑定的键
        BoundListOperations boundListOperations = redisTemplate.boundListOps("lk");

        //在绑定的键中添加值
        boundListOperations.leftPush("h");
        boundListOperations.leftPush("i");
        boundListOperations.rightPush("a");
        boundListOperations.rightPush("b");

        //获取绑定的键中的值
        boundListOperations.range(0, -1).forEach(v -> System.out.println("获取绑定的键中的值" + v));

        //获取特定位置的值
        System.out.println("获取特定位置的值：" + boundListOperations.index(0));

        //弹出左边的值
        System.out.println("弹出左边的值:" + boundListOperations.leftPop());

        //弹出右边的值
        System.out.println("弹出右边的值:" + boundListOperations.rightPop());

        //在指定的出现第一个值左边添加值
        boundListOperations.leftPush("i", "w");
        boundListOperations.range(0, -1).forEach(v -> System.out.println("在指定的出现第一个值左边添加值：" + v));

        //在指定的出现第一个值右边添加值
        boundListOperations.rightPush("i", "x");
        boundListOperations.range(0, -1).forEach(v -> System.out.println("在指定的出现第一个值左边添加值：" + v));

        //在指定的时间过后弹出左边的值
        boundListOperations.leftPop(1, TimeUnit.SECONDS);

        //在指定的时间过后弹出右边的值
        boundListOperations.rightPop(1, TimeUnit.SECONDS);

        //在左边批量添加值
        boundListOperations.leftPushAll("y", "g");
        boundListOperations.range(0, -1).forEach(v -> System.out.println("在左边批量添加值：" + v));

        //在右边批量添加值
        boundListOperations.rightPushAll("b", "r");
        boundListOperations.range(0, -1).forEach(v -> System.out.println("在右边批量添加值：" + v));

        //向左边添加不存在的值
        boundListOperations.leftPushIfPresent("k");
        boundListOperations.leftPushIfPresent(";");
        boundListOperations.range(0, -1).forEach(v -> System.out.println("向左边添加不存在的值：" + v));

        //向右边添加不存在的值
        boundListOperations.rightPushIfPresent("k");
        boundListOperations.rightPushIfPresent(";");
        System.out.println("向右边添加不存在的值：" + boundListOperations.range(0, -1).toString());

        //移除指定值的个数
        long removeCount = boundListOperations.remove(2, "i");
        System.out.println("移除指定值的个数:" + removeCount);
        System.out.println("移除指定值的个数后剩余的值：" + boundListOperations.range(0, -1).toString());

        //在指定位置设置值
        boundListOperations.set(0, "j");
        System.out.println("在指定位置设置值：" + boundListOperations.range(0, -1).toString());

        //截取指定区间位置的值
        boundListOperations.trim(1, 3);
        System.out.println("截取指定区间位置的值：" + boundListOperations.range(0, -1).toString());
    }

}
