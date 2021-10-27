package com.meishubao.log4j2.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class HelloController {

    @GetMapping("hello/{name}")
    public String hello(@PathVariable String name){
        log.debug("name:{}, author:{}", () -> name, () -> "name");
        log.trace("name:{}, author:{}", () -> name, () -> "name");
        log.error("name:{}, author:{}", () -> name, () -> "name");
        log.warn("name:{}, author:{}", () -> name, () -> "name");
        log.info("name:{}, author:{}", () -> name, () -> "name");
        return StrUtil.format("系统时间：{}，名称：{}", DateUtil.now(), name);
    }

}
