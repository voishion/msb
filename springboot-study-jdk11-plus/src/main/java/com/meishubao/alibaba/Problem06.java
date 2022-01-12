package com.meishubao.alibaba;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * 为什么阿里巴巴不建议在for循环中使用“+”进行字符串拼接？
 *
 * 我们都知道，String是Java中一个不可变的类，所以他一旦被实例化就无法被修改。
 *
 * @author lilu
 */
public class Problem06 {

    public static void main(String[] args) {
        normalChange();
    }

    /**
     * 如果需要对subList 作出修改，又不想动原list。那么可以创建subList 的一个拷贝
     */
    public static void normalChange() {
        List<String> sourceList = new ArrayList<>() {{
            add("H");
            add("O");
            add("L");
            add("L");
            add("I");
            add("S");
        }};

        List subList = Lists.newArrayList(sourceList.subList(2, 5));

        System.out.println("sourceList ： " + sourceList);
        System.out.println("sourceList.subList(2, 5) 得到List ：");
        System.out.println("subList ： " + subList);

        subList.add("666");

        System.out.println("subList.add(666) 得到List ：");
        System.out.println("subList ： " + subList);
        System.out.println("sourceList ： " + sourceList);
    }

}
