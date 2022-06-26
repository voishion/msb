package com.meishubao.sample.mockito.lesson04;

/**
 * @author lilu
 */
public class StubbingService {

    public int getI() {
        System.out.println("======getI======");
        return 10;
    }

    public String getS() {
        System.out.println("======getS======");
        throw new RuntimeException();
    }

}
