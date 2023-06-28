package com.meishubao.java8.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * volatile 共享内存
 * 因为 Java 是采用共享内存的方式进行线程通信的，所以可以采用以下方式用主线程关闭 A 线程:
 *
 * 这里的 flag 存放于主内存中，所以主线程和线程 A 都可以看到。flag 采用 volatile 修饰主要是为了内存可见性
 *
 * @author lilu
 */
public class Volatile1 implements Runnable {

    private static volatile boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            System.out.println(Thread.currentThread().getName() + "正在运行。。。");
        }
        System.out.println(Thread.currentThread().getName() + "执行完毕");
    }

    /**
     * 停止线程
     */
    private void stopThread() {
        flag = false;
    }

    public static void main(String[] args) throws InterruptedException {
        Volatile1 volatile1 = new Volatile1();
        new Thread(volatile1, "thread A").start();

        System.out.println("main 线程正在运行");

        TimeUnit.MILLISECONDS.sleep(100);

        volatile1.stopThread();
    }

}
