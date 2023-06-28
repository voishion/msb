package com.meishubao.java8.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * CyclicBarrier 中文名叫做屏障或者是栅栏，也可以用于线程间通信。
 * 它可以等待 N 个线程都达到某个状态后继续运行的效果。
 *
 * 16:00:39.226 [main] INFO com.meishubao.java8.concurrent.CyclicBarrier1 - main thread end.
 * 16:00:39.226 [Thread-2] INFO com.meishubao.java8.concurrent.CyclicBarrier1 - thread3 start run.
 * 16:00:39.226 [Thread-0] INFO com.meishubao.java8.concurrent.CyclicBarrier1 - thread1 start run.
 * 16:00:39.226 [Thread-1] INFO com.meishubao.java8.concurrent.CyclicBarrier1 - thread2 start run.
 * 16:00:42.229 [Thread-2] INFO com.meishubao.java8.concurrent.CyclicBarrier1 - thread3 end run.
 * 16:00:42.229 [Thread-0] INFO com.meishubao.java8.concurrent.CyclicBarrier1 - thread1 end run.
 * 16:00:42.229 [Thread-1] INFO com.meishubao.java8.concurrent.CyclicBarrier1 - thread2 end run.
 *
 * 1、首先初始化线程参与者。
 * 2、调用 await() 将会在所有参与者线程都调用之前等待。
 * 3、直到所有参与者都调用了 await() 后，所有线程从 await() 返回继续后续逻辑。
 * 
 * @author lilu
 */
@Slf4j
public class CyclicBarrier1 {

    public static void main(String[] args) {
        doCyclicBarrier();
    }

    private static void doCyclicBarrier() {
        int threadNum = 3;
        CyclicBarrier barrier = new CyclicBarrier(threadNum);

        for (int i = 1; i <= threadNum; i++) {
            int finalI = i;
            new Thread(() -> {
                log.info("thread" + finalI + " start run.");
                try {
                    TimeUnit.SECONDS.sleep(finalI);
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                log.info("thread" + finalI + " end run.");
            }).start();
        }

        log.info("main thread end.");
    }

}
