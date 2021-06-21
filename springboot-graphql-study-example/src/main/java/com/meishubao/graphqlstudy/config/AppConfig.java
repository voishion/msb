package com.meishubao.graphqlstudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author lilu
 */
@Configuration
public class AppConfig {

    @Bean
    public ThreadPoolTaskExecutor dataLoaderThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(4);
        executor.setMaxPoolSize(16);
        executor.setQueueCapacity(64);
        executor.setThreadNamePrefix("dataloader-async-");
        return executor;
    }

}
