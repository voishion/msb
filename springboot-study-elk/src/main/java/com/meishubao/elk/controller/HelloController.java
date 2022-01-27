package com.meishubao.elk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@Slf4j
@RestController
public class HelloController {

    @Value("${elk.author:lilu}")
    private String author;

    @GetMapping("/collect")
    public String collect(@RequestParam String keyword) {
        log.info("日志记录, author:{}, keyword:{}", author, keyword);
        return String.format("系统时间：%s，名称：%s", Instant.now(), keyword);
    }

}
