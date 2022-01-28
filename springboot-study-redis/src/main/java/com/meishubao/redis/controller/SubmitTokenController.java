package com.meishubao.redis.controller;

import cn.hutool.core.util.StrUtil;
import com.meishubao.redis.annotation.CheckRepeatSubmit;
import com.meishubao.redis.service.SubmitTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lilu
 */
@Log4j2
@RestController
@RequestMapping("/submit/token")
@RequiredArgsConstructor
public class SubmitTokenController {

    private final SubmitTokenService submitTokenService;

    @PostMapping("/generate")
    public String generate(@RequestBody String body) {
        if (StrUtil.isBlank(body)) {
            return "body不能为空";
        }
        return submitTokenService.generateToken(body);
    }

    @CheckRepeatSubmit
    @PostMapping("/order")
    public String order(@RequestBody String body) {
        if (StrUtil.isBlank(body)) {
            return "body不能为空";
        }
        log.info(body);
        return "成功";
    }

}
