package com.meishubao.study;

/**
 * instanceof模式匹配
 * 该特性可以减少强制类型转换的操作，简化了代码
 *
 * @author lilu
 * @since jdk14
 */
public class InstanceofTest {

    public static void main(String[] args) {
        //jdk14之前的写法
        Object obj = Integer.valueOf(1);
        if (obj instanceof Integer) {
            Integer i = (Integer) obj;
            System.out.println(i + 10);
        }

        //jdk14新特性  不用再强制转换了
        //这里相当于是将obj强制为Integer之后赋值给i了
        if (obj instanceof Integer i) {
            System.out.println(i + 10);
        } else {
            //作用域问题，这里是无法访问i的
        }
    }

}
