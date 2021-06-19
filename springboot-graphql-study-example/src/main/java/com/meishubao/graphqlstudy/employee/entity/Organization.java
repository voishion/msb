package com.meishubao.graphqlstudy.employee.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author lilu
 */
@Data
@Builder
public class Organization {
    private Integer id;
    private String name;
}
