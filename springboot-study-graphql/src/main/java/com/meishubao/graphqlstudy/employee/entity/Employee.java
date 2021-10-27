package com.meishubao.graphqlstudy.employee.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author lilu
 */
@Data
@Builder
public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private String position;
    private int salary;
    private int age;
    private Integer departmentId;
    private Department department;
}
