package com.meishubao.graphqlstudy.employee.resolvers;

import cn.hutool.core.util.StrUtil;
import com.meishubao.graphqlstudy.common.dataloader.DataLoaderKey;
import com.meishubao.graphqlstudy.employee.entity.Department;
import com.meishubao.graphqlstudy.employee.entity.Employee;
import com.meishubao.graphqlstudy.employee.service.DepartmentService;
import graphql.kickstart.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @author lilu
 */
@Slf4j
@Component
public class EmployeeResolver implements GraphQLResolver<Employee> {

    @Autowired
    private DepartmentService departmentService;

    public CompletableFuture<Department> department(Employee employee, DataFetchingEnvironment environment) {
        log.info("department-------");
        DataLoader<Integer, Department> loader = environment.getDataLoader(DataLoaderKey.Department.key());
        return loader.load(employee.getDepartmentId(), environment);
    }

    /**
     * 实现 graphqls 文件中有的属性，单实体类中没有的属性返回值
     *
     * @return
     */
    public String realName(Employee employee) {
        return StrUtil.format("{} {}:{}", employee.getFirstName(), employee.getLastName(), employee.getId());
    }

}
