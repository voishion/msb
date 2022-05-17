package com.meishubao.sample.config.redis;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Throwables;
import com.meishubao.sample.exception.RedisLockCallbackException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

/**
 * Redis分布式锁，模板设计模式，封装锁的获得和释放逻辑
 */
@Slf4j
@RequiredArgsConstructor
public class RedisLockTemplate {

    private final RedissonClient redissonClient;

    /**
     * 分布式锁，等待锁，可重入锁
     *
     * @param key               分布锁redis KEY
     * @param redisLockCallback 回调对象
     * @return 回调结果
     */
    public Object executeWait(String key, RedisLockCallback redisLockCallback) {
        if (StrUtil.isBlank(key)) {
            log.info("{} try lock failure-->key is null", key);
            return redisLockCallback.doInRedisLock();
        }
        // 获取分布式锁
        RLock lock = redissonClient.getLock(key);
        // 加锁（阻塞等待），默认过期时间是30秒
        lock.lock();
        Object result = null;
        try {
            result = redisLockCallback.doInRedisLock();
        } catch (Exception e) {
            log.error("error=>{}", Throwables.getStackTraceAsString(e));
            throw new RedisLockCallbackException("Redis分布式锁回调执行异常", e);
        } finally {
            lock.unlock();
        }
        return result;
    }

    /**
     * 分布式锁，不等待锁，遇锁后退出
     *
     * @param key               分布锁redis KEY
     * @param redisLockCallback 回调对象
     * @return 回调结果
     */
    public Object executeNotWait(String key, RedisLockCallback redisLockCallback) {
        if (StrUtil.isBlank(key)) {
            log.info("{} try lock failure-->key is null", key);
            return redisLockCallback.doInRedisLock();
        }
        RLock lock = redissonClient.getLock(key);
        if (lock.isLocked()) {
            log.info("{} try lock failure-->key is locked", key);
            return null;
        }
        lock.lock();
        Object result = null;
        try {
            result = redisLockCallback.doInRedisLock();
        } catch (Exception e) {
            log.error("error=>{}", Throwables.getStackTraceAsString(e));
            throw new RedisLockCallbackException("Redis分布式锁回调执行异常", e);
        } finally {
            lock.unlock();
        }
        return result;
    }

}
