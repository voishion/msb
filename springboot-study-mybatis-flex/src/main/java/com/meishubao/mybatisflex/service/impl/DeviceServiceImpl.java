package com.meishubao.mybatisflex.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.meishubao.mybatisflex.domain.entity.Device;
import com.meishubao.mybatisflex.mapper.DeviceMapper;
import com.meishubao.mybatisflex.service.DeviceService;
import org.springframework.stereotype.Service;

/**
 * 设备表 服务层实现。
 *
 * @author lilu
 * @since 2023-08-28
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {

}
