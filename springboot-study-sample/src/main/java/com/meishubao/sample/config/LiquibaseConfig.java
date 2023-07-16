package com.meishubao.sample.config;

import liquibase.integration.spring.SpringLiquibase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * @author lilu
 */
@Slf4j
@Configuration
@AutoConfigureBefore({LiquibaseAutoConfiguration.class, LiquibaseAutoConfiguration.LiquibaseConfiguration.class})
@RequiredArgsConstructor
public class LiquibaseConfig {

    @Value("${spring.liquibase.enabled:false}")
    private Boolean enabled;

    private final DataSourceProperties dataSourceProperties;

    @Bean
    public SpringLiquibase springLiquibase() {
        SpringLiquibase springLiquibase = new SpringLiquibase();

        DataSource dataSource = DataSourceBuilder.create()
                .type(determineDataSourceType())
                .url(this.dataSourceProperties.getUrl())
                .username(this.dataSourceProperties.getUsername())
                .password(this.dataSourceProperties.getPassword())
                .build();

        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog("classpath:/changelog/db.changelog-master.xml");
        springLiquibase.setShouldRun(enabled);
        springLiquibase.setResourceLoader(new DefaultResourceLoader());

        if (enabled) {
            log.info("启动Liquibase...");
        } else {
            log.info("不启动Liquibase...");
        }

        return springLiquibase;
    }

    /**
     * 获取数据源类型
     *
     * @return 数据源类型
     */
    private Class<? extends DataSource> determineDataSourceType() {
        Class<? extends DataSource> type = DataSourceBuilder.findType(null);
        return Objects.nonNull(type) ? type : SimpleDriverDataSource.class;
    }

}
