package com.meishubao.sample.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.meishubao.sample.mapper.UserMapper;
import com.meishubao.sample.model.entity.User;
import com.meishubao.sample.service.ServiceConstant;
import com.meishubao.sample.service.UserServiceD;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImplD extends ServiceImpl<UserMapper, User> implements UserServiceD {

    @Override
    //@Transactional
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveUser(User user) {
        user.setId(null);
        user.setName(ServiceConstant.SAVE_USER_NAME0 + "D");
        this.save(user);
        throw new RuntimeException("DB异常D");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveUser1(User user) {
        user.setId(null);
        user.setName(ServiceConstant.SAVE_USER_NAME1 + "D");
        this.save(user);
        //throw new RuntimeException("DB异常D");
    }

}