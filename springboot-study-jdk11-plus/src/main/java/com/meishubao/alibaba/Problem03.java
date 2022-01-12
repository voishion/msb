package com.meishubao.alibaba;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Java开发手册建议创建HashMap时设置初始化容量，但是多少合适呢？
 *
 * @author lilu
 */
public class Problem03 {

    /**
     * 们可以参考JDK8 中putAll 方法中的实现的，这个实现在guava（21.0版本）也被采用。
     *
     * 这个值的计算方法就是:
     * return (int) ((float) expectedSize / 0.75F + 1.0F);
     *
     * expectedSize:准备put元素数量
     *
     * 这个算法在guava中有实现，开发的时候，可以直接通过Maps 类创建一个
     * Map<String, String> map = Maps.newHashMapWithExpectedSize(7);
     */
    public static void main(String[] args) {
        // 我们创建了2个HashMap，分别使用元素个数（一千万）作为初始容量进和使用guava中的实现来初始化。然后分别向其中put一千万个KV。
        mapInit1();
        mapInit2();
    }

    public static void mapInit1() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Map<String, Integer> map = new HashMap<>(10000000);
        init(map);
        System.out.printf("初始化容量:100000000，耗时:%d毫秒\n", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    public static void mapInit2() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Map<String, Integer> map = Maps.newHashMapWithExpectedSize(10000000);
        init(map);
        System.out.printf("初始化容量:guava，耗时:%d毫秒\n", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    /**
     * put一千万个KV
     */
    private static void init(Map<String, Integer> map) {
        for (int i = 0; i < 10000000; i++) {
             map.put(String.valueOf(i), i);
        }
    }

}
