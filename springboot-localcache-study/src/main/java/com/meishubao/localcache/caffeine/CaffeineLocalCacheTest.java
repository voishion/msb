package com.meishubao.localcache.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

/**
 * 推荐使用这个库来做本地缓存
 * https://segmentfault.com/a/1190000039149070
 *
 * @author lilu
 */
public class CaffeineLocalCacheTest {

    public static void main(String[] args) {
//        caffeineLocalCacheTest1();
        caffeineLocalCacheTest2();
    }

    public static void caffeineLocalCacheTest1() {
        /**
         * expireAfterWrite：写入间隔多久淘汰；
         * expireAfterAccess：最后访问后间隔多久淘汰；
         * refreshAfterWrite：写入后间隔多久刷新，该刷新是基于访问被动触发的，支持异步刷新和同步刷新，如果和 expireAfterWrite 组合使用，能够保证即使该缓存访问不到、也能在固定时间间隔后被淘汰，否则如果单独使用容易造成OOM；
         * expireAfter：自定义淘汰策略，该策略下 Caffeine 通过时间轮算法来实现不同key 的不同过期时间；
         * maximumSize：缓存 key 的最大个数；
         * weakKeys：key设置为弱引用，在 GC 时可以直接淘汰；
         * weakValues：value设置为弱引用，在 GC 时可以直接淘汰；
         * softValues：value设置为软引用，在内存溢出前可以直接淘汰；
         * executor：选择自定义的线程池，默认的线程池实现是 ForkJoinPool.commonPool()；
         * maximumWeight：设置缓存最大权重；
         * weigher：设置具体key权重；
         * recordStats：缓存的统计数据，比如命中率等；
         * removalListener：缓存淘汰监听器；
         * writer：缓存写入、更新、淘汰的监听器。
         */
        Cache<String, Object> cache = Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(6, TimeUnit.MINUTES)
                .softValues()
                //是否需要统计缓存情况,该操作消耗一定的性能,生产环境应该去除
                .recordStats()
                .build();
        cache.put("1", "v1");
        cache.put("2", "v2");
        cache.put("3", "v3");
        System.out.println(cache.getIfPresent("4"));
        cache.put("4", "v4");
        System.out.println(cache.getIfPresent("4"));
        cache.getIfPresent("1");
        cache.getIfPresent("2");
        cache.getIfPresent("3");
        cache.getIfPresent("4");
        cache.getIfPresent("5");
        cache.getIfPresent("6");

        System.out.println(cache.stats());
    }

    public static void caffeineLocalCacheTest2() {
        Cache<String, Object> cache = Caffeine.newBuilder()
                .initialCapacity(100)//初始大小
                .maximumSize(200)//最大数量
                .expireAfterWrite(3, TimeUnit.SECONDS)//过期时间
                .softValues()
                .build();
        cache.put("1", "java金融");
        System.out.println(cache.getIfPresent("1"));
        // 1.如果缓存中能查到，则直接返回
        // 2.如果查不到，则从我们自定义的getValue方法获取数据，并加入到缓存中
        String val = cache.get("2", k -> getValue(k)).toString();
        System.out.println(val);
    }

    /**
     * 缓存中找不到，则会进入这个方法。一般是从数据库获取内容
     * @param k
     * @return
     */
    private static Object getValue(String k) {
        return k + ":value";
    }

}
