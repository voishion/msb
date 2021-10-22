package com.meishubao.openfeign.controller;

import com.meishubao.openfeign.feign.FeignTestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletionStage;

/**
 * @author lilu
 */
@RestController
@RequestMapping(value = "/v1/feign/test")
public class FeignTestController {

    @Autowired
    FeignTestClient feignTestClient;

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
    public CompletionStage<String> timeout(@RequestParam Integer number) {
        return feignTestClient.timeout(number);
    }

}
