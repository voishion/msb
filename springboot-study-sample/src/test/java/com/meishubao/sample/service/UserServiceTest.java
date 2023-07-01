package com.meishubao.sample.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.meishubao.sample.model.entity.User;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserServiceA userServiceA;

    @Resource
    PlatformTransactionManager ptm;

    @PostConstruct
    public void init() {
        // 找到spring boot默认的事务管理器
        log.info("ptm:=" + ptm);
    }

    @Test
    void batchInsert() {
        List<User> list = new ArrayList<>();
        list.add(User.builder().name("Zhaosi").password("123456").age(43).email("zhaosi@fox.com").build());
        list.add(User.builder().name("Liuneng").password("123456").age(42).email("liuneng@fox.com").build());
        int rows = userService.batchInsert(list);
        System.out.println(rows);
        list.forEach(System.out::println);
    }

    @Test
    void saveUser() {
        String email = "xieGuangkun@fox.com";
        saveUserBefore(email);

        User user = User.builder().name(ServiceConstant.SAVE_USER_NAME).password("123456").age(43).email(email).build();
        userServiceA.saveUser(user);

        saveUserAfter(email);
    }

    private void saveUserBefore(String email) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getEmail, email);
        boolean remove = userService.remove(wrapper);
        System.out.println("remove:" + remove);
    }

    private void saveUserAfter(String email) {
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(User::getEmail, email);
        List<User> list = userService.list(wrapper);
        if (CollectionUtil.isEmpty(list)) {
            System.out.println("user size:0");
        } else {
            System.out.println("user size:" + list.size());
            list.forEach(x -> System.out.println(x.toString()));
        }
    }

}