package com.meishubao.graphqlstudy.user.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author lilu
 */
@Data
@Builder
public class Car {

    private String id;
    private String name;
    private String color;

}
