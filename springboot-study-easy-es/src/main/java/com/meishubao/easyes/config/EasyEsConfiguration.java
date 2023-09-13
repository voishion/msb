package com.meishubao.easyes.config;

import org.dromara.easyes.starter.register.EsMapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * easy-es 配置
 *
 * @author Lion Li
 */
@Configuration
@ConditionalOnProperty(value = "easy-es.enable", havingValue = "true")
@EsMapperScan("com.meishubao.**.mapper")
public class EasyEsConfiguration {
}
