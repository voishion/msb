package com.meishubao.java8.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 线程响应中断
 *
 * @author lilu
 */
public class StopThread implements Runnable {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            // 线程执行具体逻辑
            System.out.println(Thread.currentThread().getName() + "运行中。。");
        }
        System.out.println(Thread.currentThread().getName() + "退出。。");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new StopThread(), "Thread A");
        thread.start();

        System.out.println("Main线程正在运行");

        TimeUnit.MILLISECONDS.sleep(10);
        // 中断线程
        thread.interrupt();
    }

}
