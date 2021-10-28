package com.meishubao.openfeign.controller;

import cn.hutool.core.util.StrUtil;
import com.meishubao.openfeign.common.Resilience4jHelper;
import com.meishubao.openfeign.feign.FeignTestClient;
import com.meishubao.openfeign.feign.FeignTimeoutTestClient;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

/**
 * @author lilu
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1/feign/test")
public class FeignTestController implements Resilience4jHelper {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    FeignTestClient feignTestClient;

    @Autowired
    FeignTimeoutTestClient feignTimeoutTestClient;

    @GetMapping("/circuitBreak")
    public String circuitBreak(@RequestParam Integer number) {
        return feignTestClient.circuitBreak(number).get();
    }

    @GetMapping("/retry")
    public String retry(@RequestParam Integer number) {
        return feignTestClient.retry(number).get();
    }

    @GetMapping("/bulkhead")
    public String bulkhead(@RequestParam Integer number) {
        return feignTestClient.bulkhead(number).get();
    }

    @GetMapping("/rateLimiter")
    public String rateLimiter(@RequestParam Integer number) {
        return feignTestClient.rateLimiter(number).get();
    }

    @GetMapping("/timeout")
    @Retry(name = DEFAULT, fallbackMethod = "timeoutFallback")
    @TimeLimiter(name = DEFAULT, fallbackMethod = "timeoutFallback")
    public CompletableFuture<String> timeout(@RequestParam Integer number) {
        return CompletableFuture.supplyAsync(() -> restTemplate.getForEntity(StrUtil.format("http://ai-app-service-user-api:8888/feign/test/timeout?number={}", number), String.class).getBody());
    }

    public CompletableFuture<String> timeoutFallback(Integer number, Throwable throwable) {
        handleException(throwable, log);
        return CompletableFuture.supplyAsync(() -> "反正降级了……timeout111");
    }

    @GetMapping("/timeout2")
    @Retry(name = DEFAULT, fallbackMethod = "timeout2Fallback")
    @TimeLimiter(name = "timeout2", fallbackMethod = "timeout2Fallback")
    public CompletableFuture<String> timeout2(@RequestParam Integer number) {
        return CompletableFuture.supplyAsync(() -> feignTimeoutTestClient.timeout(number).get());
    }

    public CompletableFuture<String> timeout2Fallback(Integer number, Throwable throwable) {
        handleException(throwable, log);
        return CompletableFuture.supplyAsync(() -> "Controller层降级");
    }

}
