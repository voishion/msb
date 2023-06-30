package com.meishubao.java8.oom;

import java.util.concurrent.TimeUnit;

/**
 * java.lang.OutOfMemoryError: unable to create new native thread
 * jvm参数：-XX:+PrintGCDetails
 *
 * @author lilu
 */
public class OomTestUnableToCreateNewNativeThread {

    public static void main(String[] args) {
        threadOom();
    }

    /**
     * java.lang.OutOfMemoryError: unable to create new native thread
     */
    private static void threadOom() {
        while (true) {
            Thread thread = new Thread(() -> {
                while (true) {
                    try {
                        TimeUnit.HOURS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            });
            thread.start();
        }
    }

}
