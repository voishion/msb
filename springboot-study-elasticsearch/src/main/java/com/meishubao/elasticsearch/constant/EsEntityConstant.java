package com.meishubao.elasticsearch.constant;

/**
 * Redis键常量
 *
 * @author lilu
 */
public interface EsEntityConstant {

    /**
     * 索引前缀
     */
    String INDEX_PREFIX = "springboot_study_elasticsearch_";
    /**
     * 日期时间格式
     */
    String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * ES日期时间格式
     */
    String ES_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss || yyyy-MM-dd || yyyy/MM/dd HH:mm:ss|| yyyy/MM/dd ||epoch_millis";

}
