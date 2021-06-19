package com.meishubao.graphqlstudy.user.service;

import cn.hutool.core.collection.CollUtil;
import com.meishubao.graphqlstudy.user.entity.User;
import com.meishubao.graphqlstudy.user.dto.AddUserInput;
import com.meishubao.graphqlstudy.common.enums.Sex;
import com.meishubao.graphqlstudy.common.response.ResponseBuilder;
import com.meishubao.graphqlstudy.common.response.Result;
import graphql.com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lilu
 */
@Slf4j
@Service
public class UserService {

    List<User> users = Lists.newArrayList();

    @PostConstruct
    public void init() {
        Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o").forEach(c -> {
            users.add(User.builder()
                    .id(c + "id")
                    .sex(Sex.MALE)
                    .nickname(c + "1")
                    .mail(c + "@f.com")
                    .password(c)
                    .description("desc-" + c)
                    .updateTime("2021-06-19 12:58:02")
                    .createTime("2021-06-19 12:58:02")
                    .build());
        });
    }

    public User getUserByNickname(String nickname) {
        log.info("Service ==> getUserByNickname");
        User user = null;
        List<User> collect = users.stream().filter(u -> nickname.equals(u.getNickname())).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(collect)) {
            user = collect.get(0);
        }
        return user;
    }

    public List<User> listUsers() {
        log.info("Service ==> listUsers");
        return users;
    }

    public Result addUser(String mail, String nickname, String password, String description) {
        log.info("Service ==> getUser【{}】、【{}】、【{}】、【{}】", mail, nickname, password, description);
        return ResponseBuilder.ok(100, "Success");
    }

    public Result deleteUser(String id) {
        log.info("Service ==> deleteUser【{}】", id);
        return ResponseBuilder.ok(100, "Success");
    }

    public User updateUser(String id, String mail, String nickname, String description) {
        log.info("Service ==> updateUser【{}】、【{}】、【{}】、【{}】", id, mail, nickname, description);
        return users.get(0);
    }

    public User addUserInput(AddUserInput addUserInput) {
        log.info("Service ==> addUserInput【{}】", addUserInput.toString());
        return users.get(0);
    }

}
