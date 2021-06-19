package com.meishubao.graphqlstudy.employee.service;

import com.meishubao.graphqlstudy.employee.entity.Department;
import org.springframework.stereotype.Service;

/**
 * @author lilu
 */
@Service
public class DepartmentService {

    public Department get(Integer departmentId) {
        return Department.builder().id(departmentId).name("研发部").organizationId(45275966).build();
    }

}
