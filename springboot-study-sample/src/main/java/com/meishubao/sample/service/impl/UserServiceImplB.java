package com.meishubao.sample.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meishubao.sample.mapper.UserMapper;
import com.meishubao.sample.model.entity.User;
import com.meishubao.sample.service.ServiceConstant;
import com.meishubao.sample.service.UserServiceB;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImplB extends ServiceImpl<UserMapper, User> implements UserServiceB {

    private final UserServiceImplC userServiceImplC;

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setId(null);
        user.setName(ServiceConstant.SAVE_USER_NAME + "B");
        this.save(user);
        try {
            userServiceImplC.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}