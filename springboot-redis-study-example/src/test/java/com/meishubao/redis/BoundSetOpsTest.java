package com.meishubao.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lilu
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class BoundSetOpsTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test() throws InterruptedException {
        BoundSetOperations boundSetOperations = redisTemplate.boundSetOps("bso");

        //添加新值后查看所有的值
        boundSetOperations.add("a", "b", "c");
        boundSetOperations.members().forEach(v -> System.out.println("添加新值后查看所有的值:" + v));

        //遍历所有值
        Cursor<String> cursor = boundSetOperations.scan(ScanOptions.NONE);
        while (cursor.hasNext()) {
            System.out.println("遍历所有值:" + cursor.next());
        }

        System.out.println("随机获取一个值:" + boundSetOperations.randomMember());

        System.out.println("随机获取指定数量的值:" + boundSetOperations.randomMembers(2));

        System.out.println("获取唯一的随机数量值：" + boundSetOperations.distinctRandomMembers(2));

        //这里比较的也应该是特定的集合名称，且名称不能和已经在比较的set集合中存在，否则报错
        Set list = new HashSet<>();
        list.add("bso1");
        boundSetOperations.diff(list).forEach(v -> System.out.println("比较给定集合中的set的不同元素:" + v));

        boundSetOperations.diff("bso2").forEach(v -> System.out.println("比较给定set的不同元素:" + v));

        //比较不同集合并存储
        boundSetOperations.diffAndStore("bso2", "bso3");
        redisTemplate.opsForSet().members("bso3").forEach(v -> System.out.println("比较不同set并存储:" + v));
        boundSetOperations.diff("bso3").forEach(v -> System.out.println("比较给定set的不同元素:" + v));

        //比较给定集合中的相同值
        boundSetOperations.intersect("bso2").forEach(v -> System.out.println("比较给定集合中的相同值:" + v));
        boundSetOperations.intersect(list).forEach(v -> System.out.println("比较给定集合中的相同值:" + v));

        //比较给定集合中的相同值并存储
        boundSetOperations.intersectAndStore("bso3", "bso4");
        redisTemplate.opsForSet().members("bso4").forEach(v -> System.out.println("比较给定set的相同元素:" + v));

        //将给定集合中的所有值合并
        boundSetOperations.union("bso2").forEach(v -> System.out.println("将给定集合中的所有值合并:" + v));
        boundSetOperations.union(list).forEach(v -> System.out.println("将给定集合中的所有值合并:" + v));

        boundSetOperations.unionAndStore("bso3", "bso5");
        redisTemplate.opsForSet().members("bso5").forEach(v -> System.out.println("将给定集合中的所有值合并:" + v));

        //将集合中的value值移动到另外一个集合中
        boolean moveSuc = boundSetOperations.move("bso6", "a");
        System.out.println("将集合中的值移动到另外一个集合中是否成功:" + moveSuc);
        redisTemplate.opsForSet().members("bso6").forEach(v -> System.out.println("将集合中的值移动到另外一个集合中:" + v));
        boundSetOperations.members().forEach(v -> System.out.println("将集合中的值移动到另外一个集合中原集合剩余的值:" + v));

        //弹出集合中的值
        Object p = boundSetOperations.pop();
        System.out.println("弹出集合中的值:" + p);
        boundSetOperations.members().forEach(v -> System.out.println("弹出集合中的值原集合剩余的值:" + v));

        //移除特定元素
        long removeCount = boundSetOperations.remove("c");
        System.out.println("移除特定元素个数:" + removeCount);
        boundSetOperations.members().forEach(v -> System.out.println("移除特定元素个数后原集合剩余的值:" + v));
    }

}
