package com.meishubao.openfeign.feign.config;

import com.meishubao.openfeign.common.Resilience4jHelper;
import com.meishubao.openfeign.feign.FeignTestClient;
import feign.Feign;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Optional;

/**
 * FeignTestClient接口熔断回退配置
 *
 * @author lilu
 */
@Configuration
public class FeignTestClientConfig {

    @Bean
    @Scope("prototype")
    public Feign.Builder feignTestClientFeignBuilder() {
        return Resilience4jFeign.builder(FeignDecorators.builder().withFallbackFactory(FeignTestClientFallback::new).build());
    }

    public class FeignTestClientFallback implements FeignTestClient, Resilience4jHelper {
        private final Logger log = LoggerFactory.getLogger(getClass());

        private Throwable throwable;

        public FeignTestClientFallback(Throwable throwable) {
            this.throwable = throwable;
        }

        @Override
        public Optional<String> circuitBreak(Integer number) {
            handleException(throwable, log);
            return Optional.of("反正降级了……circuitBreak");
        }

        @Override
        public Optional<String> retry(Integer number) {
            handleException(throwable, log);
            return Optional.of("反正降级了……retry");
        }

        @Override
        public Optional<String> bulkhead(Integer number) {
            handleException(throwable, log);
            return Optional.of("反正降级了……bulkhead");
        }

        @Override
        public Optional<String> rateLimiter(Integer number) {
            handleException(throwable, log);
            return Optional.of("反正降级了……rateLimiter");
        }

    }

}
