package com.meishubao.graphqlstudy.employee.resolvers;

import com.meishubao.graphqlstudy.employee.entity.Department;
import com.meishubao.graphqlstudy.employee.entity.Employee;
import com.meishubao.graphqlstudy.employee.service.DepartmentService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lilu
 */
@Slf4j
@Component
public class EmployeeResolver implements GraphQLResolver<Employee> {

    @Autowired
    private DepartmentService departmentService;

    public Department department(Employee employee) {
        log.info("department-------");
        return departmentService.get(employee.getDepartmentId());
    }

}
