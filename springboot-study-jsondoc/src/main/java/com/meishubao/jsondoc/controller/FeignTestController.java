package com.meishubao.jsondoc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author lilu
 */
@Slf4j
@RestController
@RequestMapping(value = "/feign/test")
public class FeignTestController {

    // 熔断 CircuitBreak
    @GetMapping("/circuitBreak")
    public ResponseEntity<String> circuitBreak(@RequestParam Integer number) throws Exception {
        log.info("[服务端模拟熔断场景，数字] -> {}", number);
        // 模拟响应延迟
        TimeUnit.MILLISECONDS.sleep(100L);
        // 模拟响应失败
        int random = ThreadLocalRandom.current().nextInt(1, 4);
        if(random < 3) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok("调用成功");
    }

    // 重试 Retry
    @GetMapping("/retry")
    public ResponseEntity<String> retry(@RequestParam(value = "number") Integer number) {
        int random = ThreadLocalRandom.current().nextInt(1, 4);
        log.info("[服务端模拟重试场景，数字] -> {}", random);
        if (random < 2) {
            return ResponseEntity.status(500).build();
        } else if (random < 3) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("调用成功");
    }

    // 并发隔板 Bulkhead
    @GetMapping("/bulkhead")
    public ResponseEntity<String> bulkhead(@RequestParam(value = "number") Integer number) throws InterruptedException {
        log.info("[模拟服务端处理缓慢] -> {}", number);
        TimeUnit.SECONDS.sleep(10L);
        return ResponseEntity.ok("调用成功");
    }

    // 流控 RateLimiter
    @GetMapping("/rateLimiter")
    public ResponseEntity<String> rateLimiter(@RequestParam(value = "number") Integer number) throws InterruptedException {
        log.info("[模拟服务端快速处理] -> {}", number);
        return ResponseEntity.ok("调用成功");
    }

    // 超时 Timeout
    @GetMapping("/timeout")
    public ResponseEntity<String> timeout(@RequestParam(value = "number") Integer number) throws InterruptedException {
        log.info("[模拟服务端网络抖动] -> {}", number);
        int second = ThreadLocalRandom.current().nextInt(1, 5);
        log.info("[服务端模拟超时场景，超时 {} 秒]", second);
        TimeUnit.SECONDS.sleep(second);
        return ResponseEntity.ok("调用成功");
    }

}
