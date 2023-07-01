package com.meishubao.sample.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meishubao.sample.mapper.UserMapper;
import com.meishubao.sample.model.entity.User;
import com.meishubao.sample.service.ServiceConstant;
import com.meishubao.sample.service.UserServiceA;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImplA extends ServiceImpl<UserMapper, User> implements UserServiceA {

    private final UserServiceImplB userServiceImplB;

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setId(null);
        user.setName(ServiceConstant.SAVE_USER_NAME0 + "A");
        this.save(user);
        userServiceImplB.saveUser(user);
    }

    @Override
    @Transactional
    public void saveUser1(User user) {
        user.setId(null);
        user.setName(ServiceConstant.SAVE_USER_NAME1 + "A");
        this.save(user);
        userServiceImplB.saveUser1(user);
    }

}