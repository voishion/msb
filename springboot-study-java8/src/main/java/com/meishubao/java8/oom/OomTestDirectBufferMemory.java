package com.meishubao.java8.oom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * java.lang.OutOfMemoryError: Direct buffer memory
 * jvm参数：-XX:+PrintGCDetails -Xmx10M
 *
 * @author lilu
 */
public class OomTestDirectBufferMemory {

    public static void main(String[] args) {
        directBuffer();
    }

    /**
     * java.lang.OutOfMemoryError: Direct buffer memory
     */
    private static void directBuffer() {
        final int _1M = 1024 * 1024 * 1;
        List<ByteBuffer> buffers = new ArrayList<>();
        int count = 1;
        while (true) {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_1M);
            buffers.add(byteBuffer);
            System.out.println(count++);
        }
    }

}
