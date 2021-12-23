package com.meishubao.study;

/**
 * CharSequence新增的方法
 *
 * @author lilu
 * @since jdk14
 */
public class CharSequenceTest {

    public static void main(String[] args) {
        //该接口中新增了default方法isEmpty()，作用是判断CharSequence是否为空
        CharSequence str = "Hello World";
        str = null;
        str = "null";
        str = "";
        System.out.println(str.isEmpty());
    }

}
