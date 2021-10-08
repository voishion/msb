package com.meishubao.mongodb.controller.docment;

import com.meishubao.mongodb.service.docment.SaveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文档存储
 *
 * @author lilu
 */
@Api(tags = "文档存储")
@RestController
@RequestMapping("/save")
public class SaveController {

    @Autowired
    private SaveService saveService;

    @ApiOperation("存储【一条】用户信息，如果文档信息已经【存在就执行更新】")
    @PostMapping(value = "/save")
    public Object save() {
        return saveService.save();
    }

}
