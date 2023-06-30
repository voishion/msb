package com.meishubao.java8.oom;

import java.util.LinkedList;
import java.util.List;

/**
 * MaxMetaspaceSize is too small
 * jvm参数：-XX:+PrintGCDetails -XX:MaxMetaspaceSize=3M
 *
 * @author lilu
 */
public class OomTestMaxMetaspaceSizeIsTooSmall {

    public static void main(String[] args) {
        metaspaceOom();
    }

    /**
     * MaxMetaspaceSize is too small
     * -XX:MaxMetaspaceSize=5M
     */
    private static void metaspaceOom() {
        List<Object> list = new LinkedList<>();
        int temp = 0;
        while (true) {
            temp++;
            list.add(temp);
        }
    }

}
