package com.meishubao.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lilu
 */
public class MapTest {

    private final static Map<String, Object> MAP = new HashMap<String, Object>(){{
        put("name", "LILU");
        put("age", 10);
    }};

    public static void main(String[] args) {
        System.out.println(MAP);
    }

}
