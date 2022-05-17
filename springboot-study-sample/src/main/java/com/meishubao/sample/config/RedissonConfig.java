package com.meishubao.sample.config;

import cn.hutool.core.util.StrUtil;
import com.meishubao.sample.config.redis.RedisLockTemplate;
import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lilu
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedissonConfig {

    private String url;
    private String password;
    private Integer database;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress(url);
        singleServerConfig.setDatabase(database);
        if (StrUtil.isNotBlank(password)) {
            singleServerConfig.setPassword(password);
        }
        return Redisson.create(config);
    }

    @Bean
    public RedisLockTemplate redisLockTemplate(RedissonClient redissonClient) {
        return new RedisLockTemplate(redissonClient);
    }

}
