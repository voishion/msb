package com.meishubao.sample.component.initialize;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 解决办法1：使用arrayList.stream().forEach通过内层抛出运行时异常，外层捕获异常的方式实现中止循环并退出
 * 解决办法2：使用arrayList.stream().anyMatch函数可实现中止循环并退出，anyMatch()里接收一个返回值为boolean类型的
 *          表达式，只要返回true就会终止循环，这样可以将业务逻辑写在返回判断结果前
 *
 * @author lilu
 */
public class ForeachTest {

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("a", "b", "c", "d");
        try {
            list.stream().forEach(x -> {
                if ("c".equals(x)) {
                    throw new RuntimeException("终端退出");
                }
                System.out.println(x);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Go1");

        list.stream().anyMatch(x -> {
            if ("c".equals(x)) {
                return true;
            }
            System.out.println(x);
            return false;
        });
        System.out.println("Go1");
    }

}
