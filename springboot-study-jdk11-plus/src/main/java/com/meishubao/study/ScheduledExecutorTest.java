package com.meishubao.study;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author lilu
 */
public class ScheduledExecutorTest {

    public static void main(String[] args) throws InterruptedException {

        /**使用Executors工具快速构建对象*/
        ScheduledExecutorService scheduledExecutorService =
                Executors.newSingleThreadScheduledExecutor();
//        System.out.println("3秒后开始执行计划线程池服务..." + new Date());
        /**每间隔4秒执行一次任务*/
        // 循环任务，以上一次任务的结束时间计算下一次任务的开始时间
//        scheduledExecutorService.scheduleWithFixedDelay(() -> {
//                    System.out.println(new Date());
//                },
//                0, 5, TimeUnit.SECONDS);

        System.out.println("3秒后开始执行计划线程池服务..." + new Date());
        /**每间隔4秒执行一次任务*/

        // 循环任务，以上一次任务的发起时间计算下一次任务的开始时间
        scheduledExecutorService.scheduleAtFixedRate(() -> {
                    System.out.println(new Date());
                },
                0, 1, TimeUnit.SECONDS);

        Thread.sleep(5000);
        scheduledExecutorService.shutdown();
        Thread.sleep(5000);
        System.out.println("3秒后开始执行计划线程池服务..." + new Date());
    }

}
