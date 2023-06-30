package com.meishubao.java8.oom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * java.lang.OutOfMemoryError: GC overhead limit exceeded
 * jvm参数：-XX:+PrintGCDetails -Xmx10M
 *
 * @author lilu
 */
public class OomTestGCOverheadLimitExceeded {

    public static void main(String[] args) {
        gcLimit();
    }

    /**
     * java.lang.OutOfMemoryError: GC overhead limit exceeded
     * -Xmx10M
     */
    private static void gcLimit() {
        ExecutorService executor = Executors.newFixedThreadPool(100);
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
