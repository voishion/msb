package com.meishubao.java8.map.tree;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class TreeMapTest {

    public static void main(String[] agrs) {
        //使用测试
        //use();

        //自然顺序比较
        naturalSort();

        //自定义顺序比较
        customSort();

        //自定义顺序比较（推荐该种方式）
        customSort2();
    }

    //使用测试
    public static void use() {
        //创建TreeMap对象：
        TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();
        System.out.println("初始化后,TreeMap元素个数为：" + treeMap.size());

        //新增元素:
        treeMap.put("hello", 1);
        treeMap.put("world", 2);
        treeMap.put("my", 3);
        treeMap.put("name", 4);
        treeMap.put("is", 5);
        treeMap.put("jiaboyan", 6);
        treeMap.put("i", 6);
        treeMap.put("am", 6);
        treeMap.put("a", 6);
        treeMap.put("developer", 6);
        System.out.println("添加元素后,TreeMap元素个数为：" + treeMap.size());

        //遍历元素：
        Set<Map.Entry<String, Integer>> entrySet = treeMap.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("TreeMap元素的key:" + key + ",value:" + value);
        }

        //获取所有的key：
        Set<String> keySet = treeMap.keySet();
        for (String strKey : keySet) {
            System.out.println("TreeMap集合中的key:" + strKey);
        }

        //获取所有的value:
        Collection<Integer> valueList = treeMap.values();
        for (Integer intValue : valueList) {
            System.out.println("TreeMap集合中的value:" + intValue);
        }

        //获取元素：
        Integer getValue = treeMap.get("jiaboyan");//获取集合内元素key为"jiaboyan"的值
        String firstKey = treeMap.firstKey();//获取集合内第一个元素
        String lastKey = treeMap.lastKey();//获取集合内最后一个元素
        String lowerKey = treeMap.lowerKey("jiaboyan");//获取集合内的key小于"jiaboyan"的key
        String ceilingKey = treeMap.ceilingKey("jiaboyan");//获取集合内的key大于等于"jiaboyan"的key
        SortedMap<String, Integer> sortedMap = treeMap.subMap("a", "my");//获取集合的key从"a"到"jiaboyan"的元素

        //删除元素：
        Integer removeValue = treeMap.remove("jiaboyan");//删除集合中key为"jiaboyan"的元素
        treeMap.clear(); //清空集合元素：

        //判断方法：
        boolean isEmpty = treeMap.isEmpty();//判断集合是否为空
        System.out.println("isEmpty:" + isEmpty);
        boolean isContain = treeMap.containsKey("jiaboyan");//判断集合的key中是否包含"jiaboyan"
        System.out.println("isContain:" + isContain);
    }

    //自然排序顺序：
    public static void naturalSort() {
        //第一种情况：Integer对象
        TreeMap<Integer, String> treeMapFirst = new TreeMap<>();
        treeMapFirst.put(1, "jiaboyan");
        treeMapFirst.put(6, "jiaboyan");
        treeMapFirst.put(3, "jiaboyan");
        treeMapFirst.put(10, "jiaboyan");
        treeMapFirst.put(7, "jiaboyan");
        treeMapFirst.put(13, "jiaboyan");
        System.out.println(treeMapFirst);

        //第二种情况:SortedTest对象
        TreeMap<SortedTest, String> treeMapSecond = new TreeMap<>();
        treeMapSecond.put(new SortedTest(10), "jiaboyan");
        treeMapSecond.put(new SortedTest(1), "jiaboyan");
        treeMapSecond.put(new SortedTest(13), "jiaboyan");
        treeMapSecond.put(new SortedTest(4), "jiaboyan");
        treeMapSecond.put(new SortedTest(0), "jiaboyan");
        treeMapSecond.put(new SortedTest(9), "jiaboyan");
        System.out.println(treeMapSecond);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    static class SortedTest implements Comparable<SortedTest> {
        private int age;

        //自定义对象，实现compareTo(T o)方法：
        public int compareTo(SortedTest sortedTest) {
            int num = this.age - sortedTest.getAge();
            //为0时候，两者相同：
            if (num == 0) {
                return 0;
                //大于0时，传入的参数小：
            } else if (num > 0) {
                return 1;
                //小于0时，传入的参数大：
            } else {
                return -1;
            }
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    static class SortedTest2 {
        private int age;
    }

    static class SortedTest2Comparator implements Comparator<SortedTest2> {
        //自定义比较器：实现compare(T o1,T o2)方法：
        public int compare(SortedTest2 sortedTest1, SortedTest2 sortedTest2) {
            int num = sortedTest1.getAge() - sortedTest2.getAge();
            if (num == 0) {//为0时候，两者相同：
                return 0;
            } else if (num > 0) {//大于0时，后面的参数小：
                return 1;
            } else {//小于0时，前面的参数小：
                return -1;
            }
        }
    }

    //自定义排序顺序:
    public static void customSort() {
        TreeMap<SortedTest2, String> treeMap = new TreeMap<>(new SortedTest2Comparator());

        treeMap.put(new SortedTest2(10), "hello");
        treeMap.put(new SortedTest2(21), "my");
        treeMap.put(new SortedTest2(15), "name");
        treeMap.put(new SortedTest2(2), "is");
        treeMap.put(new SortedTest2(1), "jiaboyan");
        treeMap.put(new SortedTest2(7), "world");
        System.out.println(treeMap);
    }

    public static void customSort2() {
        TreeMap<SortedTest2, String> treeMap = new TreeMap<>(Comparator.comparing(SortedTest2::getAge).reversed());

        treeMap.put(new SortedTest2(10), "hello");
        treeMap.put(new SortedTest2(21), "my");
        treeMap.put(new SortedTest2(15), "name");
        treeMap.put(new SortedTest2(2), "is");
        treeMap.put(new SortedTest2(1), "jiaboyan");
        treeMap.put(new SortedTest2(7), "world");
        System.out.println(treeMap);
    }

}