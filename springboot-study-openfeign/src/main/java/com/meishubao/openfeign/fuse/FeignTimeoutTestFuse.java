package com.meishubao.openfeign.fuse;

import com.meishubao.openfeign.common.Resilience4jHelper;
import com.meishubao.openfeign.feign.FeignTimeoutTestClient;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author lilu
 */
@Slf4j
@Component
public class FeignTimeoutTestFuse implements Resilience4jHelper {

    @Autowired
    FeignTimeoutTestClient feignTimeoutTestClient;

    /**
     * TEST
     * @param number
     *        1-TimeLimiter
     *        2-RateLimiter
     *        3-Bulkhead
     *        4-Retry
     *        5-CircuitBreaker
     * @return
     */
    @CircuitBreaker(name = DEFAULT, fallbackMethod = TIMEOUT2_FM)
    @Bulkhead(name = TIMEOUT2_MN, fallbackMethod = TIMEOUT2_FM, type = Bulkhead.Type.SEMAPHORE)
    @Retry(name = DEFAULT, fallbackMethod = TIMEOUT2_FM)
    @RateLimiter(name = TIMEOUT2_MN, fallbackMethod = TIMEOUT2_FM)
    @TimeLimiter(name = TIMEOUT2_MN, fallbackMethod = TIMEOUT2_FM)
    public CompletableFuture<Optional<String>> timeout2(Integer number) {
        return CompletableFuture.supplyAsync(() -> feignTimeoutTestClient.timeout(number));
    }
    private CompletableFuture<Optional<String>> timeout2Fallback(Integer number, Throwable throwable) {
        handleException(throwable, log);
        return CompletableFuture.supplyAsync(() -> Optional.of("Fuse层降级"));
    }

}
