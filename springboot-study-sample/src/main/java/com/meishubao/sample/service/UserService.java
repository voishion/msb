package com.meishubao.sample.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.meishubao.sample.model.entity.User;

import java.util.List;

public interface UserService extends IService<User> {

    int batchInsert(List<User> list);

}