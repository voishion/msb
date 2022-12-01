package com.meishubao.java8.multitask;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * CountDownLatch多任务
 *
 * @author lilu
 */
@Slf4j
public class CountDownLatchMultiTask {

    //定长10线程池
    static ExecutorService executor;

    static {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("多任务线程-%d").build();
        executor = new ThreadPoolExecutor(3, 100, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000), threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());
    }

    static class TestTask implements Runnable {

        private final String accountId;
        private final CountDownLatch latch;

        TestTask(String accountId, CountDownLatch latch) {
            this.accountId = accountId;
            this.latch = latch;
        }

        @Override
        public void run() {
            // mock finding account from database
            String startTime = DateTime.now().toString(DatePattern.NORM_DATETIME_MS_PATTERN);
            Stopwatch stopwatch = Stopwatch.createStarted();
            try {
                if (accountId.startsWith("1")) {
                    //任务1耗时3秒
                    Thread.sleep(4000);
                } else if (accountId.startsWith("2")) {
                    //任务2耗时1秒,还出错
                    Thread.sleep(2000);
                    throw new RuntimeException("出异常了");
                } else {
                    //其它任务耗时1秒
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
                log.info("任务:{}, start time:{}, exec time:{}", accountId, startTime, stopwatch.stop());
            }
        }
    }

    public static void batchProcess(List<String> list) {
        Stopwatch stopwatch = Stopwatch.createStarted();

        try {
            CountDownLatch latch = new CountDownLatch(list.size());
            list.forEach(x -> executor.execute(new TestTask(x, latch)));
            boolean await = latch.await(10, TimeUnit.SECONDS);
            log.info("await:{}", await);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("exec time:" + stopwatch.stop());
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("11111");
        list.add("22222");
        list.add("33333");
        batchProcess(list);
    }

}
