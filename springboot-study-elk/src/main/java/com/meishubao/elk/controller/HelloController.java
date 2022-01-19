package com.meishubao.elk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable String name){
        log.debug("name:{}, author:{}", name, "voishion");
        log.trace("name:{}, author:{}", name, "voishion");
        log.error("name:{}, author:{}", name, "voishion");
        log.warn("name:{}, author:{}", name, "voishion");
        log.info("name:{}, author:{}", name, "voishion");
        return String.format("系统时间：%s，名称：%s", Instant.now(), name);
    }

}
