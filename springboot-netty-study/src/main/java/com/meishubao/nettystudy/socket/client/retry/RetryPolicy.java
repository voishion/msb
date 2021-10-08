package com.meishubao.nettystudy.socket.client.retry;

/**
 * 重试策略
 *
 * @author lilu
 */
public interface RetryPolicy {

    /**
     * 判断是否进行下一次重连
     *
     * @param retryCount 到目前为止重试的次数（第一次为 0）
     * @return
     */
    boolean allowRetry(int retryCount);

    /**
     * 获取当前重试次数的睡眠时间（毫秒）
     *
     * @param retryCount 当前重试次数
     * @return 睡眠时间
     */
    long getSleepTimeMs(int retryCount);
}