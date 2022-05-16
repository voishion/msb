package com.meishubao.test.guava;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * BiMap 双向映射
 *
 * @author lilu
 */
public class BiMapTest {

    public static void main(String[] args) {
        BiMap<String, Integer> map = HashBiMap.create();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);
        System.out.println(map.get("A"));
        System.out.println(map.inverse().get(3));
    }

}
