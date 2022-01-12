package com.meishubao.alibaba;

import com.google.common.base.Stopwatch;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 为什么阿里巴巴建议初始化HashMap的容量大小？
 *
 * @author lilu
 */
public class Problem02 {

    /**
     * HashMap设置一个合理的初始化容量可以有效的提高性能
     *
     * HashMap 有扩容机制，就是当达到扩容条件时会进行扩容。HashMap 的扩容条件就是当HashMap中的元素个数（size）超过临界值（threshold）时就会自动扩容。
     * 在HashMap 中，threshold = loadFactor * capacity。
     *
     * 所以，如果我们没有设置初始容量大小，随着元素的不断增加，HashMap 会发 生多次扩容，而HashMap 中的扩容机制决定了每次扩容都需要重建hash 表，是非 常影响性能的。
     */
    public static void main(String[] args) {
        // 我们创建了3个HashMap，分别使用默认的容量（16）、使用元素个数的一半（5百万）作为初始容量、使用元素个数（一千万）作为初始容量进 行初始化。然后分别向其中put一千万个KV。
        mapInit1();
        mapInit2();
        mapInit3();
    }

    public static void mapInit1() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Map<String, Integer> map = new HashMap<>();
        init(map);
        System.out.printf("未初始化容量，耗时:%d毫秒\n", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    public static void mapInit2() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Map<String, Integer> map = new HashMap<>(5000000);
        init(map);
        System.out.printf("初始化容量:50000000，耗时:%d毫秒\n", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    public static void mapInit3() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        Map<String, Integer> map = new HashMap<>(10000000);
        init(map);
        System.out.printf("初始化容量:100000000，耗时:%d毫秒\n", stopwatch.elapsed(TimeUnit.MILLISECONDS));
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
