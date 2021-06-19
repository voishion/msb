package com.meishubao.graphqlstudy.employee.controller;

import com.meishubao.graphqlstudy.employee.entity.Employee;
import com.meishubao.graphqlstudy.employee.service.EmployeeService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lilu
 */
@Slf4j
@Component
public class EmployeeQueryController implements GraphQLQueryResolver {

    @Autowired
    EmployeeService employeeService;

    public Employee employee(Integer id) {
        return this.employeeService.get(id);
    }

}
