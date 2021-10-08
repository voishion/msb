package com.meishubao.mongodb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

/**
 * SpringBoot 引入 MongoDB 中的事务，配置事务管理器<br>
 * 注意：单节点 mongodb 不支持事务，需要搭建 MongoDB 复制集。
 *
 * @author lilu
 */
@Configuration
public class TransactionConfiguration {

    @Bean
    public MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

}
