package com.meishubao.java8.stream;

import cn.hutool.core.util.RandomUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author lilu
 */
public class StreamTest2 {

    public static void main(String[] args) {
        // 返回一个顺序的流，其元素是指定的值。
        List<Integer> list = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9).collect(Collectors.toList());
        print(list);

        // 生成指定种子、指定递增规则、指定个数的List
        list = Stream.iterate(0, x -> x + 1).limit(10).collect(Collectors.toList());
        print(list);

        // 返回一个无限连续的无序流，其中每个元素都是由提供的供应商生成的。这适用于生成常量流、随机元素流等。
        list = Stream.generate(() -> RandomUtil.randomInt(1, 10)).limit(10).collect(Collectors.toList());
        print(list);

        // 返回一个顺序IntStream，从startInclusive(包括)到endexexclusive(排除)，递增1步。
        list = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        print(list);
    }

    private static void print(List<Integer> list) {
        String result = list.stream().map(String::valueOf).collect(Collectors.joining(", "));
        System.out.println(result);
    }

}
