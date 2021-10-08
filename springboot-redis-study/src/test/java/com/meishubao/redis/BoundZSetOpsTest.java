package com.meishubao.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author lilu
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class BoundZSetOpsTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void test() throws InterruptedException {
        BoundZSetOperations boundZSetOperations = redisTemplate.boundZSetOps("bzso");

        boundZSetOperations.add("a", 1);
        boundZSetOperations.add("b", 2);

        //集合中的值
        boundZSetOperations.range(0, -1).forEach(v -> System.out.println("集合中的值:" + v));

        //获取从指定位置开始(下标起始坐标为1)到结束位置为止的值个数
        System.out.println("获取从指定位置开始(下标起始坐标为1)到结束位置为止的值个数:" + boundZSetOperations.count(1, 2));

        //通过TypedTuple方式新增数据
        ZSetOperations.TypedTuple<Object> typedTuple1 = new DefaultTypedTuple<Object>("E", 6.0);
        ZSetOperations.TypedTuple<Object> typedTuple2 = new DefaultTypedTuple<Object>("F", 7.0);
        ZSetOperations.TypedTuple<Object> typedTuple3 = new DefaultTypedTuple<Object>("G", 5.0);
        Set<ZSetOperations.TypedTuple<Object>> typedTupleSet = new HashSet<ZSetOperations.TypedTuple<Object>>();
        typedTupleSet.add(typedTuple1);
        typedTupleSet.add(typedTuple2);
        typedTupleSet.add(typedTuple3);
        boundZSetOperations.add(typedTupleSet);
        boundZSetOperations.range(0, -1).forEach(v -> System.out.println("通过TypedTuple方式新增数据:" + v));

        //自增长后的数据
        boundZSetOperations.incrementScore("a", 1);
        boundZSetOperations.range(0, -1).forEach(v -> System.out.println("自增长后的数据:" + v));

        //获取相同值，并存储
        boundZSetOperations.intersectAndStore("bzso1", "bzso2");
        redisTemplate.opsForZSet().range("bzso2", 0, -1).forEach(v -> System.out.println("获取相同值，并存储" + v));

        Cursor<ZSetOperations.TypedTuple> cursor = boundZSetOperations.scan(ScanOptions.NONE);
        while (cursor.hasNext()) {
            ZSetOperations.TypedTuple typedTuple = cursor.next();
            System.out.println("扫描绑定数据:" + typedTuple.getValue() + "--->" + typedTuple.getScore());
        }

        //按照值来排序的取值,这个排序只有在有相同分数的情况下才能使用，如果有不同的分数则返回值不确定
        RedisZSetCommands.Range range = new RedisZSetCommands.Range();
        range.lte("b");
        //range.gte("F");
        boundZSetOperations.rangeByLex(range).forEach(v -> System.out.println("按照值来排序的取值:" + v));

        //获取从设置下标开始的设置长度的元素值
        RedisZSetCommands.Limit limit = new RedisZSetCommands.Limit();
        limit.count(2);
        //起始下标为0
        limit.offset(1);
        boundZSetOperations.rangeByLex(range, limit).forEach(v -> System.out.println("按照值来排序的限定取值:" + v));

        //按照分数排序
        boundZSetOperations.rangeByScore(3, 7).forEach(v -> System.out.println("按照分数排序:" + v));

        //按照位置排序取值和分数
        Set<ZSetOperations.TypedTuple> tupleSet = boundZSetOperations.rangeWithScores(3, 5);
        tupleSet.forEach(v -> System.out.printf("按照位置排序取值和分数:%s-->%s\n", v.getValue(), v.getScore()));

        //按照分数位置排序取值和分数
        Set<ZSetOperations.TypedTuple> scoreSet = boundZSetOperations.rangeByScoreWithScores(1, 5);
        scoreSet.forEach(v -> System.out.printf("按照分数位置排序取值和分数:%s-->%s\n", v.getValue(), v.getScore()));

        //按照值来倒序取值
        boundZSetOperations.reverseRange(0, 3).forEach(v -> System.out.println("按照值来倒序取值:" + v));

        //按照分数来倒序取值
        boundZSetOperations.reverseRangeByScore(2, 5).forEach(v -> System.out.println("按照分数来倒序取值:" + v));

        //按照位置倒序取值和分数
        tupleSet = boundZSetOperations.reverseRangeWithScores(2, 5);
        tupleSet.forEach(v -> System.out.printf("按照位置倒序取值和分数:%s-->%s\n", v.getValue(), v.getScore()));

        //按照分数位置倒序取值和分数
        scoreSet = boundZSetOperations.reverseRangeByScoreWithScores(2, 5);
        scoreSet.forEach(v -> System.out.printf("按照分数位置倒序取值和分数:%s-->%s\n", v.getValue(), v.getScore()));

        //统计分数在某个区间的个数
        System.out.println("统计分数在某个区间的个数:" + boundZSetOperations.count(2, 5));


        //获取变量中元素的索引,下标开始位置为0
        System.out.println("获取变量中元素的索引:" + boundZSetOperations.rank("b"));

        System.out.println("获取变量中元素的分数:" + boundZSetOperations.score("b"));

        //获取变量中元素的个数
        System.out.println("获取变量中元素的个数:" + boundZSetOperations.zCard());

        //intersectAndStore后的数据
        boundZSetOperations.intersectAndStore("abc", "bac");
        redisTemplate.opsForSet().members("bac").forEach(v -> System.out.println("intersectAndStore后的数据:" + v));

        //intersectAndStore集合后的数据
        List list = new ArrayList<>(Arrays.asList("abc", "acb"));
        boundZSetOperations.intersectAndStore(list, "bac");
        redisTemplate.opsForSet().members("bac").forEach(v -> System.out.println("intersectAndStore集合后的数据:" + v));

        //unionAndStore后的数据
        boundZSetOperations.unionAndStore("abc", "bca");
        redisTemplate.opsForZSet().range("bca", 0, -1).forEach(v -> System.out.println("unionAndStore后的数据:" + v));

        //unionAndStore集合后的数据
        boundZSetOperations.unionAndStore(list, "bca");
        redisTemplate.opsForZSet().range("bca", 0, -1).forEach(v -> System.out.println("unionAndStore集合后的数据:" + v));

        //移除给定值中的变量
        long removeCount = boundZSetOperations.remove("a", "b");
        System.out.println("移除给定值中的变量个数:" + removeCount);
        boundZSetOperations.range(0, -1).forEach(v -> System.out.println("移除给定值中的变量后剩余的值:" + v));

        //移除区间值的变量
        boundZSetOperations.removeRange(2, 3);
        boundZSetOperations.range(0, -1).forEach(v -> System.out.println("移除区间值的变量后剩余的值:" + v));

        //移除分数区间值的变量
        boundZSetOperations.removeRangeByScore(3, 5);
        boundZSetOperations.range(0, -1).forEach(v -> System.out.println("移除分数区间值的变量后剩余的值:" + v));
    }

}
