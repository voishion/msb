package com.meishubao.openfeign.feign;

import com.meishubao.openfeign.common.Resilience4jHelper;
import com.meishubao.openfeign.config.FeignConfig;
import com.meishubao.openfeign.feign.config.FeignTestClientConfig;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

/**
 * @author admin
 */
@FeignClient(name = "FeignTestClient", url = "http://ai-app-service-user-api:8888", configuration = {FeignConfig.class, FeignTestClientConfig.class})
public interface FeignTestClient {

    @Retry(name = Resilience4jHelper.DEFAULT)
    @CircuitBreaker(name = Resilience4jHelper.DEFAULT)
    @GetMapping(value = "/feign/test/circuitBreak")
    Optional<String> circuitBreak(@RequestParam(value = "number") Integer number);

    @Retry(name = Resilience4jHelper.DEFAULT)
    @GetMapping(value = "/feign/test/retry")
    Optional<String> retry(@RequestParam(value = "number") Integer number);

    @Bulkhead(name = Resilience4jHelper.DEFAULT, type = Bulkhead.Type.SEMAPHORE)
    @GetMapping(value = "/feign/test/bulkhead")
    Optional<String> bulkhead(@RequestParam(value = "number") Integer number);

    @RateLimiter(name = Resilience4jHelper.DEFAULT)
    @GetMapping(value = "/feign/test/rateLimiter")
    Optional<String> rateLimiter(@RequestParam(value = "number") Integer number);

    @TimeLimiter(name = Resilience4jHelper.DEFAULT)
    @GetMapping(value = "/feign/test/timeout")
    CompletionStage<String> timeout(@RequestParam(value = "number") Integer number);

}
