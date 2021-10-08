package com.meishubao.mongodb.controller;

import com.meishubao.mongodb.service.ViewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MongoDB 视图操作
 *
 * @author lilu
 */
@Api(tags = "MongoDB 视图操作")
@RestController
@RequestMapping("/view")
public class ViewController {

    @Autowired
    private ViewService viewService;

    @ApiOperation("创建视图")
    @PostMapping(value = "/createView")
    public Object createView() {
       return viewService.createView();
    }

    @ApiOperation("删除视图")
    @PostMapping(value = "/dropView")
    public Object dropView() {
        return viewService.dropView();
    }

}
