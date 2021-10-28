package com.meishubao.openfeign.feign.config;

import com.meishubao.openfeign.common.Resilience4jHelper;
import com.meishubao.openfeign.feign.FeignTimeoutTestClient;
import feign.Feign;
import io.github.resilience4j.feign.FeignDecorators;
import io.github.resilience4j.feign.Resilience4jFeign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.Optional;

/**
 * FeignTimeoutTestClient接口熔断回退配置
 *
 * @author lilu
 */
//@Configuration
public class FeignTimeoutTestClientConfig {

    @Bean
    @Scope("prototype")
    public Feign.Builder feignTimeoutTestClientFeignBuilder() {
        return Resilience4jFeign.builder(FeignDecorators.builder().withFallbackFactory(FeignTimeoutTestClientFallback::new).build());
    }

    public class FeignTimeoutTestClientFallback implements FeignTimeoutTestClient, Resilience4jHelper {
        private final Logger log = LoggerFactory.getLogger(getClass());

        private Throwable throwable;

        public FeignTimeoutTestClientFallback(Throwable throwable) {
            this.throwable = throwable;
        }

        @Override
        public Optional<String> timeout(Integer number) {
            handleException(throwable, log);
            return Optional.of("Feign层降级");
        }
    }

}
