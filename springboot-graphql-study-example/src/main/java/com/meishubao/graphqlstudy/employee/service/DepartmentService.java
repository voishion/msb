package com.meishubao.graphqlstudy.employee.service;

import cn.hutool.core.collection.CollUtil;
import com.meishubao.graphqlstudy.employee.entity.Department;
import graphql.com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author lilu
 */
@Slf4j
@Service
public class DepartmentService {

    public Department get(Integer departmentId) {
        log.info("get:{}", departmentId);
        return Department.builder().id(departmentId).name("研发部" + departmentId).organizationId(45275966 + departmentId).build();
    }

    public List<Department> list(Set<Integer> departmentIds) {
        // SELECT * FROM `department` WHERE `id` IN (1, 2, 3, ..., N)
        log.info("list:{}", departmentIds);
        List<Department> list = Lists.newArrayList();
        if (CollUtil.isNotEmpty(departmentIds)) {
            for (Integer departmentId : departmentIds) {
                list.add(this.get(departmentId));
            }
        }
        return list;
    }
}
