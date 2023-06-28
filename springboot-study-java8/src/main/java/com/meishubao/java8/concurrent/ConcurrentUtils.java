package com.meishubao.java8.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 并发工具类
 *
 * @author biezhi
 * @date 2018/3/11
 */
@Slf4j
public class ConcurrentUtils {

    public static void stop(ExecutorService executor) {
        try {
            // 停止线程池接受新任务，并且会平滑的关闭线程池中现有的任务
            executor.shutdown();
            // 让主线程等待线程池中所有任务执行完毕
            while (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                log.info("等待线程池中所有任务执行完毕...");
            }
            log.info("线程池停止完成.");
        } catch (InterruptedException e) {
            System.err.println("termination interrupted");
        } finally {
            if (!executor.isTerminated()) {
                System.err.println("killing non-finished tasks");
            }
            executor.shutdownNow();
        }
    }

    public static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }


}
