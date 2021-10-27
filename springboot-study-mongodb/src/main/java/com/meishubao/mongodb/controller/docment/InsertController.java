package com.meishubao.mongodb.controller.docment;

import com.meishubao.mongodb.service.docment.InsertService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文档插入
 *
 * @author lilu
 */
@Api(tags = "文档插入")
@RestController
@RequestMapping("/insert")
public class InsertController {

    @Autowired
    private InsertService insertService;

    @ApiOperation("插入【一条】文档数据，如果文档信息已经【存在就抛出异常】")
    @PostMapping(value = "/insert")
    public Object insert() {
        return insertService.insert();
    }

    @ApiOperation("插入【多条】文档数据，如果文档信息已经【存在就抛出异常】")
    @PostMapping(value = "/insertMany")
    public Object insertMany() {
        return insertService.insertMany();
    }

}
