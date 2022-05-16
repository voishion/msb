package com.meishubao.sample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

/**
 * 应用程序配置
 *
 * @author lilu
 */
// 启用Spring重试
@EnableRetry
@Configuration
public class AppConfig {
}
