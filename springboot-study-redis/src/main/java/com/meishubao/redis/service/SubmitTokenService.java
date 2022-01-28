package com.meishubao.redis.service;

/**
 * 提交令牌业务接口
 *
 * @author lilu
 */
public interface SubmitTokenService {

    /**
     * 创建Token存入Redis，并返回该Token
     *
     * @param value 用于辅助验证的value值
     * @return 生成的Token串
     */
    String generateToken(String value);

    /**
     * 验证Token正确性
     *
     * @param token token字符串
     * @param value value存储在Redis中的辅助验证信息
     * @return 验证结果
     */
    boolean validToken(String token, String value);

}
