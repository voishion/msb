package com.meishubao.alibaba;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * 为什么阿里巴巴要求谨慎使用ArrayList中的subList方法？
 *
 * 【强制】ArrayList的subList结果不可强转成ArrayList，否则会抛出 ClassCastException异常：java.util.RandomAccessSubList cannot be cast to java.util.ArrayList。
 * 说明：subList()返回的是ArrayList的内部类SubList，并不是 ArrayList本身，而是ArrayList 的一个视图，对于SubList的所有操作最终会反映到原列表上。
 *
 * 我们不能把subList 方法返回的List 强制转换成ArrayList 等类，因为他们之间没有继承关系。
 *
 * 如何创建新的List？
 *
 * @author lilu
 */
public class Problem05 {

    public static void main(String[] args) {
        //classCastException();
        //unstructuredChange();
        //structuredChange();
        normalChange();
    }

    public static void classCastException() {
        List<String> names = new ArrayList<>() {{
            add("Hollis");
            add("hollischuang");
            add("H");
        }};
        //List subList = names.subList(0, 1);
        //Exception in thread "main" java.lang.ClassCastException: class java.util.ArrayList$SubList cannot be cast to class java.util.ArrayList (java.util.ArrayList$SubList and java.util.ArrayList are in module java.base of loader 'bootstrap')
        ArrayList subList = (ArrayList) names.subList(0, 1);
        System.out.println(subList);
    }

    /**
     * 非结构性改变SubList
     */
    public static void unstructuredChange() {
        List<String> sourceList = new ArrayList<>() {{
            add("H");
            add("O");
            add("L");
            add("L");
            add("I");
            add("S");
        }};

        List subList = sourceList.subList(2, 5);

        System.out.println("sourceList ： " + sourceList);
        System.out.println("sourceList.subList(2, 5) 得到List ：");
        System.out.println("subList ： " + subList);

        subList.set(1, "666");

        System.out.println("subList.set(3,666) 得到List ：");
        System.out.println("subList ： " + subList);
        System.out.println("sourceList ： " + sourceList);
    }

    /**
     * 结构性改变SubList
     */
    public static void structuredChange() {
        List<String> sourceList = new ArrayList<>() {{
            add("H");
            add("O");
            add("L");
            add("L");
            add("I");
            add("S");
        }};

        List subList = sourceList.subList(2, 5);

        System.out.println("sourceList ： " + sourceList);
        System.out.println("sourceList.subList(2, 5) 得到List ：");
        System.out.println("subList ： " + subList);

        subList.add("666");

        System.out.println("subList.add(666) 得到List ：");
        System.out.println("subList ： " + subList);
        System.out.println("sourceList ： " + sourceList);
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
