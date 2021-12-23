package com.meishubao.study;

import java.util.List;

/**
 * 局部变量
 * var的使用是有限制的，仅适用于局部变量，增强for循环的索引，以及普通for循环的本地变量；它不能使用于方法形参，构造方法形参，方法返回类型等。
 *
 * @author lilu
 * @since jdk10
 */
public class LocalVariable {

    public static void main(String[] args) {
        // jdk10 before
        String oldName = "jack";
        int oldAge = 10;
        long oldMoney = 88888888L;
        Object oldObj = new Object();

        // jdk10 after
        var newName = "jack";
        var newAge = 10;
        var newMoney = 88888888L;
        var newObj = new Object();

        List<String> list = List.of("1", "2");
        list.forEach(System.out::println);

        for (var i = 0; i < list.size(); i++) {
            var item = list.get(i);
            System.out.println(item);
        }
    }

}
