package com.meishubao.mybatisflex.config;

import com.google.common.collect.Sets;
import com.mybatisflex.core.audit.AuditManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Set;

@Configuration
public class MyBatisFlexConfig {

    private static final Logger logger = LoggerFactory.getLogger("ASYNC_SQL_AUDIT_LOGGER");

    @Value("${spring.profiles.active:default}")
    private String active;

    /**
     * 设置显示的swagger环境信息
     **/
    private final Set<String> profiles = Sets.newHashSet("local", "default", "dev", "test");

    @PostConstruct
    public void init() {
        if (this.profiles.contains(active)) {
            //开启审计功能
            AuditManager.setAuditEnable(true);

            //设置 SQL 审计收集器
            AuditManager.setMessageCollector(auditMessage ->
                    logger.info("\n==>    FullSql: {}\n==>    Elapsed: {}ms\n", auditMessage.getFullSql()
                            , auditMessage.getElapsedTime())
            );
        }
    }
}