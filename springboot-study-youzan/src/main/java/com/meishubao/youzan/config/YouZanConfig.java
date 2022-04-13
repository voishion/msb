package com.meishubao.youzan.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lilu
 */
@Data
@Component
@ConfigurationProperties(value = "youzan")
public class YouZanConfig {

    /**
     * 配置多店铺接入
     **/
    private Map<String, YzConfig> map;

    @Data
    public static class YzConfig {
        private String clientId;
        private String clientSecret;
        private Long shopId;
    }

}
