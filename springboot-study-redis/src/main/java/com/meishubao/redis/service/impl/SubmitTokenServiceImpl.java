package com.meishubao.redis.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.HashUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.CharMatcher;
import com.meishubao.redis.constant.RedisKeyConstant;
import com.meishubao.redis.service.SubmitTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 提交令牌业务接口实现
 *
 * @author lilu
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class SubmitTokenServiceImpl implements SubmitTokenService {

    private final StringRedisTemplate redisTemplate;

    /**
     * 获取字符串Hash码
     *
     * @param value
     * @return
     */
    private String hashCode(String value) {
        value = CharMatcher.breakingWhitespace().removeFrom(value);
        return Convert.toStr(HashUtil.dekHash(value));
    }

    @Override
    public String generateToken(String value) {
        String token = IdUtil.simpleUUID();
        String key = StrUtil.format(RedisKeyConstant.SUBMIT_TOKEN_KEY, token);
        value = hashCode(value);
        log.info("key={}, value={}", key, value);
        redisTemplate.opsForValue().set(key, value, 5, TimeUnit.MINUTES);
        return token;
    }

    @Override
    public boolean validToken(String token, String value) {
        // 设置 Lua 脚本，其中 KEYS[1] 是 key，KEYS[2] 是 value
        String script = "if redis.call('get', KEYS[1]) == KEYS[2] then return redis.call('del', KEYS[1]) else return 0 end";
        RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
        String key = StrUtil.format(RedisKeyConstant.SUBMIT_TOKEN_KEY, token);
        value = hashCode(value);
        log.info("key={}, value={}", key, value);
        Long result = redisTemplate.execute(redisScript, Arrays.asList(key, value));
        // 根据返回结果判断是否成功成功匹配并删除 Redis 键值对，若果结果不为空和0，则验证通过
        if (result != null && result != 0L) {
            log.info("验证成功, token={}, key={}, value={}", token, key, value);
            return true;
        }
        log.info("验证失败, token={}, key={}, value={}", token, key, value);
        return false;
    }

}
