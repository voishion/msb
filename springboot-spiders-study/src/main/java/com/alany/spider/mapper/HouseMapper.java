package com.alany.spider.mapper;

import com.alany.spider.entity.House;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HouseMapper extends BaseMapper<House> {

    int batchInsert(List<House> tasks);

}




