package com.meishubao.admin.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@Slf4j
@RestController
public class HelloController {

    @GetMapping("/collect")
    public String collect(@RequestParam String keyword) {
        log.info("日志记录, keyword:{}", keyword);
        log.error("日志记录, keyword:{}", keyword);
        log.debug("日志记录, keyword:{}", keyword);
        log.trace("日志记录, keyword:{}", keyword);
        log.warn("日志记录, keyword:{}", keyword);
        return String.format("系统时间：%s，名称：%s", Instant.now(), keyword);
    }

}
