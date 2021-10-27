package com.meishubao.graphqlstudy.employee.service;

import com.meishubao.graphqlstudy.employee.entity.Employee;
import org.springframework.stereotype.Service;

/**
 * @author lilu
 */
@Service
public class EmployeeService {

    public Employee get(Integer id) {
        return Employee.builder()
                .id(id)
                .firstName("LI")
                .lastName("LU")
                .position("CN")
                .salary(10)
                .age(30)
                .departmentId(1001)
                .build();
    }

}
