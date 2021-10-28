package com.meishubao.openfeign.feign;

import com.meishubao.openfeign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * @author admin
 */
//@FeignClient(name = "FeignTimeoutTestClient", url = "http://ai-app-service-user-api:8888", configuration = {FeignConfig.class, FeignTimeoutTestClientConfig.class})
@FeignClient(name = "FeignTimeoutTestClient", url = "http://ai-app-service-user-api:8888", configuration = FeignConfig.class)
public interface FeignTimeoutTestClient {

    @GetMapping(value = "/feign/test/timeout")
    Optional<String> timeout(@RequestParam(value = "number") Integer number);

}
