package com.meishubao.java8.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * java.lang.OutOfMemoryError: Java heap space
 * jvm参数：-Xmx10M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author lilu
 */
public class OomTestJavaHeapSpace {

    public static void main(String[] args) {
        heapOom();
    }

    /**
     * java.lang.OutOfMemoryError: Java heap space
     */
    private static void heapOom() {
        List<OomTestJavaHeapSpace> list = new ArrayList<>();
        while (true) {
            list.add(new OomTestJavaHeapSpace());
        }
    }

}
