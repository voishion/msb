package com.meishubao.java8.oom;

/**
 * java.lang.StackOverflowError
 * jvm参数：-XX:+PrintGCDetails -Xmx10M -Xss144K
 *
 * @author lilu
 */
public class OomTestStackOverflowError {

    public static void main(String[] args) {
        stackOver();
    }

    private static int stackDep = 1;

    public static void stackLeak() {
        stackDep++;
        stackLeak();
    }

    /**
     * java.lang.StackOverflowError
     */
    private static void stackOver() {
        try {
            stackLeak();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println(OomTestStackOverflowError.stackDep);
        }
    }

}
