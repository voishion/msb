package com.meishubao.mongodb.controller.docment;

import com.meishubao.mongodb.service.docment.UpdateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文档更新
 *
 * @author lilu
 */
@Api(tags = "文档更新")
@RestController
@RequestMapping("/update")
public class UpdateController {

    @Autowired
    private UpdateService updateService;

    @ApiOperation("更新集合中【匹配】查询到的第一条文档数据，如果没有找到就【创建并插入一个新文档】")
    @PostMapping(value = "/update")
    public Object update() {
        return updateService.update();
    }

    @ApiOperation("更新集合中【匹配】查询到的【文档数据集合】中的【第一条数据】")
    @PostMapping(value = "/updateFirst")
    public Object updateFirst() {
        return updateService.updateFirst();
    }

    @ApiOperation("更新【匹配查询】到的【文档数据集合】中的【所有数据】")
    @PostMapping(value = "/updateMany")
    public Object updateMany() {
        return updateService.updateMany();
    }

}
