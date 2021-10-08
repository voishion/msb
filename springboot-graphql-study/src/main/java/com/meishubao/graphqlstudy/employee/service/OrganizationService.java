package com.meishubao.graphqlstudy.employee.service;

import com.meishubao.graphqlstudy.employee.entity.Organization;
import org.springframework.stereotype.Service;

/**
 * @author lilu
 */
@Service
public class OrganizationService {

    public Organization get(Integer organizationId) {
        return Organization.builder().id(organizationId).name("音乐宝").build();
    }

}
