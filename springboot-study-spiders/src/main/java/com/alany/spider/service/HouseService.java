package com.alany.spider.service;

import com.alany.spider.entity.House;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface HouseService extends IService<House> {

    int batchInsert(List<House> list);

}
