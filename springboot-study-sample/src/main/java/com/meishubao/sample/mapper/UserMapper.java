package com.meishubao.sample.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.meishubao.sample.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    int batchInsert(List<User> list);

}