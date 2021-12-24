package com.meishubao.okay.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.meishubao.okay.aop.annotation.RunTimeLog;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class HelloController {

    @RunTimeLog
    @GetMapping("hello1/{name}")
    public String hello1(@PathVariable String name){
        log.info("name:{}, author:{}", () -> name, () -> "name");
        return StrUtil.format("系统时间：{}，名称：{}", DateUtil.now(), name);
    }

    @RunTimeLog("你好")
    @GetMapping("hello2/{name}")
    public String hello2(@PathVariable String name){
        log.info("name:{}, author:{}", () -> name, () -> "name");
        return StrUtil.format("系统时间：{}，名称：{}", DateUtil.now(), name);
    }

}
