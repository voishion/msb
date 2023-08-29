package com.meishubao.mybatisflex.service.impl;

import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.meishubao.mybatisflex.domain.entity.Device;
import com.meishubao.mybatisflex.mapper.DeviceMapper;
import com.meishubao.mybatisflex.service.DeviceService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 设备表 服务接口实现
 *
 * @author lilu
 * @since 2023-08-29
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {

}
