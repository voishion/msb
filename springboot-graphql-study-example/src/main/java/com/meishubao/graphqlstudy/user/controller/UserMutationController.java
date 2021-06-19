package com.meishubao.graphqlstudy.user.controller;

import cn.hutool.core.util.StrUtil;
import com.meishubao.graphqlstudy.user.dto.AddUserInput;
import com.meishubao.graphqlstudy.user.entity.User;
import com.meishubao.graphqlstudy.common.response.ResponseBuilder;
import com.meishubao.graphqlstudy.common.response.Result;
import com.meishubao.graphqlstudy.user.service.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lilu
 */
@Slf4j
@Component
public class UserMutationController implements GraphQLMutationResolver {

    @Autowired
    private UserService userService;

    public Result addUser(String mail, String nickname, String password, String description) {
        log.info("Mutation Resolver ==> addUser");
        log.info("params: mail:{}, nickname:{}, password:{}, description:{}",
                mail, nickname, password, description);
        return userService.addUser(mail, nickname, password, description);
    }

    public Result deleteUser(String id) {
        if(StrUtil.isBlank(id)){
            return ResponseBuilder.ok(-101, "参数缺失");
        }
        log.info("Mutation Resolver ==> deleteUser");
        log.info("params: id:{}", id);
        return userService.deleteUser(id);
    }

    public User updateUser(String id, String mail, String nickname, String description) {
        log.info("Mutation Resolver ==> updateUser");
        log.info("params: id:{}, mail:{}, nickname:{}, description:{}",
                id, mail, nickname, description);
        return userService.updateUser(id, mail, nickname, description);
    }

    public User addUserByInput(AddUserInput addUserInput){
        log.info("Mutation Resolver ==> addUserByInput");
        log.info("params: {}", addUserInput);
        return userService.addUserInput(addUserInput);
    }

}
