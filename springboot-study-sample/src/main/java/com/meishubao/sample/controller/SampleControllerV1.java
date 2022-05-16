package com.meishubao.sample.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.meishubao.sample.model.R;
import com.meishubao.sample.service.TestRetryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "示例接口")
@ApiSupport(order = 1)
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/sample")
public class SampleControllerV1 {

    private final TestRetryService testRetryService;

    @ApiOperation("使用@Retryable注解优雅实现重处理")
    @ApiOperationSupport(order = 1)
    @GetMapping("/testRetry")
    public R<Integer> testRetry(@ApiParam(required = true, value = "代码") @RequestParam Integer code) {
        int test = testRetryService.test(code);
        return R.ok(test);
    }

}
