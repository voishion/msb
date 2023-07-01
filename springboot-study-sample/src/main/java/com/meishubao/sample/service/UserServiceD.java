package com.meishubao.sample.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.meishubao.sample.model.entity.User;

import java.io.IOException;

public interface UserServiceD extends IService<User> {

    void saveUser(User user) throws IOException;

    void saveUser1(User user);
}