package com.meishubao.alibaba;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 为什么阿里巴巴禁止使用Executors创建线程池？
 *
 * @author lilu
 */
public class Problem04 {

    // OutOfMemory
    private static ExecutorService executorOOM = Executors.newFixedThreadPool(15);

    // The good choose
    private static ExecutorService executorGood = new ThreadPoolExecutor(1, 10, 60L,
            TimeUnit.SECONDS, new ArrayBlockingQueue(10));

    // The best choose
    private static final ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
    private static ExecutorService executorBest = new ThreadPoolExecutor(1, 10, 60L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(10), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    /**
     * 为什么JDK自身提供的构建线程池的方式并不建议使用？到底应该如何创建一个线程池呢？
     * Executors 创建的线程池存在OOM 的风险，那么到底是什么原因导致的呢？
     * <p>
     * 真正的导致OOM 的其实是LinkedBlockingQueue.offer方法，底层是通过LinkedBlockingQueue实现的，是一个用链表实现的有界阻塞队列，容量可
     * 以选择 进行设置，不设置的话，将是一个无边界的阻塞队列，最大长度为Integer.MAX_ VALUE。不设置的话，将是一个无边界的阻塞队列，最大长度为
     * Integer.MAX_VALUE。也就是说，如果我们不设置LinkedBlockingQueue的 容量的话，其默认容量将会是Integer.MAX_VALUE。对于一个无边界队
     * 列来说，是可以不断的向队列中加入任务的，这种情况下就有可能因为任务过多而导 致内存溢出问题。
     * <p>
     * 创建线程池的正确姿势
     * <p>
     * 避免使用Executors 创建线程池，主要是避免使用其中的默认实现，那么我们 可以自己直接调用ThreadPoolExecutor 的构造函数来自己创建线程池。
     * 在创建的 同时，给BlockQueue 指定容量就可以了。
     * <p>
     * 推荐使用guava 提供的ThreadFactoryBuilder 来创建线程池
     */
    public static void main(String[] args) {
        //executorOOMTest();
        //executorGoodTest();
        //executorBestTest();
    }

    // 通过指定JVM 参数：-Xmx8m -Xms8m 运行代码，会抛出OOM
    public static void executorOOMTest() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorOOM.execute(new SubThread());
        }
    }

    public static void executorGoodTest() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorGood.execute(new SubThread());
        }
    }

    public static void executorBestTest() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorBest.execute(new SubThread());
        }
    }

    static class SubThread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
            }
        }
    }

}
