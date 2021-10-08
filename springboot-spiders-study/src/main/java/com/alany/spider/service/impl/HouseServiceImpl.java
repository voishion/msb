package com.alany.spider.service.impl;

import com.alany.spider.entity.House;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.alany.spider.service.HouseService;
import com.alany.spider.mapper.HouseMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl extends ServiceImpl<HouseMapper, House> implements HouseService {

    @Override
    public int batchInsert(List<House> list) {
        return this.baseMapper.batchInsert(list);
    }
}




