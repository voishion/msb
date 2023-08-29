package com.meishubao.mybatisflex.controller;

import com.mybatisflex.core.paginate.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.meishubao.mybatisflex.domain.entity.Device;
import com.meishubao.mybatisflex.service.DeviceService;
import org.springframework.web.bind.annotation.RestController;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.io.Serializable;
import java.util.List;

/**
 * 设备表 控制器
 *
 * @author lilu
 * @since 2023-08-29
 */
@Slf4j
@ApiSort(1)
@Api(tags = "设备表接口")
@RequiredArgsConstructor
@RestController
@RequestMapping("/device")
public class DeviceController {

    private final DeviceService deviceService;

    /**
     * 添加设备表
     *
     * @param device 设备表
     * @return {@code true} 添加成功，{@code false} 添加失败
     */
    @ApiOperationSupport(order = 1, author = "lilu")
    @ApiOperation("保存设备表")
    @PostMapping("save")
    public boolean save(@RequestBody @ApiParam("设备表") Device device) {
        return deviceService.save(device);
    }

    /**
     * 根据主键删除设备表
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @ApiOperationSupport(order = 2, author = "lilu")
    @ApiOperation("根据主键设备表")
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable @ApiParam("设备表主键") Serializable id) {
        return deviceService.removeById(id);
    }

    /**
     * 根据主键更新设备表
     *
     * @param device 设备表
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @ApiOperationSupport(order = 3, author = "lilu")
    @ApiOperation("根据主键更新设备表")
    @PutMapping("update")
    public boolean update(@RequestBody @ApiParam("设备表主键") Device device) {
        return deviceService.updateById(device);
    }

    /**
     * 查询所有设备表
     *
     * @return 所有数据
     */
    @ApiOperationSupport(order = 4, author = "lilu")
    @ApiOperation("查询所有设备表")
    @GetMapping("list")
    public List<Device> list() {
        return deviceService.list();
    }

    /**
     * 根据设备表主键获取详细信息
     *
     * @param id 设备表主键
     * @return 设备表详情
     */
    @ApiOperationSupport(order = 5, author = "lilu")
    @ApiOperation("根据主键获取设备表")
    @GetMapping("getInfo/{id}")
    public Device getInfo(@PathVariable @ApiParam("设备表主键") Serializable id) {
        return deviceService.getById(id);
    }

    /**
     * 分页查询设备表
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @ApiOperationSupport(order = 6, author = "lilu")
    @ApiOperation(value = "分页查询设备表", notes = "分页查询设备表接口")
    @GetMapping("page")
    public Page<Device> page(@ApiParam("分页信息") Page<Device> page) {
        return deviceService.page(page);
    }

}
