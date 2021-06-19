package com.meishubao.graphqlstudy.employee.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author lilu
 */
@Data
@Builder
public class Department {
    private Integer id;
    private String name;
    private Integer organizationId;
    private Organization organization;
}
