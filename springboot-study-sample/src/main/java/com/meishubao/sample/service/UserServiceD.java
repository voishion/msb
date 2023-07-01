package com.meishubao.sample.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.meishubao.sample.model.entity.User;

public interface UserServiceD extends IService<User> {

    void saveUser(User user);

}