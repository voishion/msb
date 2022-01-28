package com.meishubao.redis.constant;

/**
 * Redis键常量
 *
 * @author lilu
 */
public interface RedisKeyConstant {

    /**
     * 根键
     */
    String ROOT_KEY = "springboot-study-redis:";
    /**
     * 提交令牌键
     */
    String SUBMIT_TOKEN_KEY = ROOT_KEY + "submit-token:{}";

}
