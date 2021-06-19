package com.meishubao.graphqlstudy.employee.resolvers;

import com.meishubao.graphqlstudy.employee.entity.Department;
import com.meishubao.graphqlstudy.employee.entity.Employee;
import com.meishubao.graphqlstudy.employee.entity.Organization;
import com.meishubao.graphqlstudy.employee.service.OrganizationService;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lilu
 */
@Slf4j
@Component
public class DepartmentResolver implements GraphQLResolver<Department> {

    @Autowired
    private OrganizationService organizationService;

    public Organization organization(Department department) {
        log.info("organization-------");
        return organizationService.get(department.getOrganizationId());
    }

}
