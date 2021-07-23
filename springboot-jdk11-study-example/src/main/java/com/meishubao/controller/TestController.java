package com.meishubao.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lilu
 */
@RestController
@RequestMapping("/v1/t")
public class TestController {

    private Map<String, Object> ok() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("code", 200);
        result.put("msg", "操作成功");
        return result;
    }

    @PostMapping("/test01")
    public Map<String, Object> test01(@RequestBody Map<String, Object> params) {
        return ok();
    }

}
