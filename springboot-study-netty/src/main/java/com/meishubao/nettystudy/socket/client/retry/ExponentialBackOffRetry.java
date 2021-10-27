package com.meishubao.nettystudy.socket.client.retry;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * 重试策略，随着重试之间的睡眠时间增加而重试设定的次数
 *
 * @author lilu
 */
@Slf4j
public class ExponentialBackOffRetry implements RetryPolicy {

    /** 最大重试限制 **/
    private static final int MAX_RETRIES_LIMIT = 29;
    /** 默认最大睡眠（毫秒） **/
    private static final int DEFAULT_MAX_SLEEP_MS = Integer.MAX_VALUE;

    private final Random random = new Random();
    private final long baseSleepTimeMs;
    private final int maxRetries;
    private final int maxSleepMs;

    /**
     * 构建重试策略
     *
     * @param baseSleepTimeMs 基本睡眠时间（毫秒）
     * @param maxRetries 最大重试次数
     */
    public ExponentialBackOffRetry(int baseSleepTimeMs, int maxRetries) {
        this(baseSleepTimeMs, maxRetries, DEFAULT_MAX_SLEEP_MS);
    }

    /**
     * 构建重试策略
     *
     * @param baseSleepTimeMs 基本睡眠时间（毫秒）
     * @param maxRetries 最大重试次数
     * @param maxSleepMs 最大睡眠时间（毫秒）
     */
    public ExponentialBackOffRetry(int baseSleepTimeMs, int maxRetries, int maxSleepMs) {
        this.maxRetries = maxRetries;
        this.baseSleepTimeMs = baseSleepTimeMs;
        this.maxSleepMs = maxSleepMs;
    }

    @Override
    public boolean allowRetry(int retryCount) {
        if (retryCount < maxRetries) {
            return true;
        }
        return false;
    }

    @Override
    public long getSleepTimeMs(int retryCount) {
        if (retryCount < 0) {
            throw new IllegalArgumentException("重试次数必须大于0");
        }
        if (retryCount > MAX_RETRIES_LIMIT) {
            log.info(String.format("重试次数太大(%d). 固定到%d", retryCount, MAX_RETRIES_LIMIT));
            retryCount = MAX_RETRIES_LIMIT;
        }
        long sleepMs = baseSleepTimeMs * Math.max(1, random.nextInt(1 << retryCount));
        if (sleepMs > maxSleepMs) {
            log.info(String.format("睡眠时间太大(%d)，固定到%d", sleepMs, maxSleepMs));
            sleepMs = maxSleepMs;
        }
        return sleepMs;
    }

}