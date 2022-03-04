package com.meishubao.reflect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.meishubao.utils.JsonUtils;

import java.math.BigDecimal;

/**
 * @author lilu
 */
public class AppleTest {

    public static void main(String[] args) throws JsonProcessingException {
        AppleDTO dto1 = new AppleDTO()
                //.setId(193284633L)
                .setColor("red")
                .setPrice(new BigDecimal("23.45"))
                .setSize(12)
                ;
        Apple apple1 = new Apple().append(dto1);

        String json1 = JsonUtils.toJson(apple1);
        System.out.println(json1);
        AppleDTO _dto1 = JsonUtils.safeRead(json1, AppleDTO.class);
        System.out.println(_dto1);

        apple1.setCreateBy("LILU");
        apple1.setUpdateBy("LILU");
        AppleDTO dto2 = apple1.insert().update().clone(AppleDTO.class);

        String json2 = JsonUtils.toJson(dto2);
        System.out.println(json2);
        Apple _apple2 = JsonUtils.safeRead(json2, Apple.class);
        System.out.println(_apple2);
    }

}
