package com.meishubao.graphqlstudy.user.controller;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.PageUtil;
import com.meishubao.graphqlstudy.common.response.Page;
import com.meishubao.graphqlstudy.user.entity.User;
import com.meishubao.graphqlstudy.user.service.UserService;
import com.meishubao.graphqlstudy.user.vo.UserPage;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lilu
 */
@Slf4j
@Component
public class UserQueryController implements GraphQLQueryResolver {

    @Autowired
    private UserService userService;

    public User getUser(String nickname) {
        log.info("Query Resolver ==> user");
        log.info("params: nickname:{}", nickname);
        return userService.getUserByNickname(nickname);
    }

    public List<User> users() {
        log.info("Query Resolver ==> users");
        return userService.listUsers();
    }

    public UserPage userPage(String query, Integer page, Integer size) {
        List<User> users = this.userService.listUsers();
        return new UserPage().build(page, size, users.size(), ListUtil.page(page - 1, size, users));
    }

}
