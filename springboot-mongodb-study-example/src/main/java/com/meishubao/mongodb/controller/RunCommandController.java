package com.meishubao.mongodb.controller;

import com.meishubao.mongodb.service.RunCommandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MongoDB RunCommand 命令操作
 *
 * @author lilu
 */
@Api(tags = "MongoDB RunCommand 命令操作")
@RestController
@RequestMapping("/runCommand")
public class RunCommandController {

    @Autowired
    private RunCommandService runCommandService;

    @ApiOperation("执行 mongoDB 自定义命令，详情可以查看：https://docs.mongodb.com/manual/reference/command/")
    @PostMapping(value = "/runCommand")
    public Object runCommand() {
        return runCommandService.runCommand();
    }

}
