package com.meishubao.youzan.config;

import com.google.common.base.Throwables;
import com.youzan.cloud.open.sdk.core.HttpConfig;
import com.youzan.cloud.open.sdk.core.client.core.DefaultYZClient;
import com.youzan.cloud.open.security.SecretClient;
import com.youzan.cloud.open.security.exception.DataSecurityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author lilu
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final YouZanConfig youZanConfig;

    @Bean
    public DefaultYZClient youZanClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder().readTimeout(5, TimeUnit.HOURS);
        HttpConfig httpConfig = HttpConfig.builder().OkHttpClientBuilder(builder).build();
        return new DefaultYZClient(httpConfig);
    }

    @Bean
    public SecretClient youZanSecretClient() {
        try {
            YouZanConfig.YzConfig config1 = youZanConfig.getMap().get("config1");
            return new SecretClient(config1.getClientId(), config1.getClientSecret());
        } catch (DataSecurityException e) {
            log.error("初始化SecretClient异常, error=>{}", Throwables.getStackTraceAsString(e));
            return null;
        }
    }

}
