package com.meishubao.sample.service;

import com.meishubao.sample.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void batchInsert() {
        List<User> list = new ArrayList<>();
        list.add(User.builder().name("zhangsan").age(23).email("zhangsan@fox.com").build());
        list.add(User.builder().name("lisi").age(32).email("lisi@fox.com").build());
        int rows = userService.batchInsert(list);
        System.out.println(rows);
        list.forEach(System.out::println);
    }

}