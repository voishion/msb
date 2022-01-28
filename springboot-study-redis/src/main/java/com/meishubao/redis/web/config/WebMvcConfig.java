package com.meishubao.redis.web.config;

import com.meishubao.redis.constant.RequestAttributeConstant;
import com.meishubao.redis.web.interceptor.CheckRepeatSubmitInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lilu
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    public final CheckRepeatSubmitInterceptor feignRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(feignRequestInterceptor).addPathPatterns(RequestAttributeConstant.ALL_REQUEST_PATTERN);
    }

}
