package com.meishubao.sample.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meishubao.sample.model.entity.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    int batchInsert(List<User> list);

}