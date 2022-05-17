package com.meishubao.sample.exception;

/**
 * Redis分布式锁回调执行异常
 *
 * @author lilu
 */
public class RedisLockCallbackException extends RuntimeException {

    public RedisLockCallbackException() {
        super();
    }

    public RedisLockCallbackException(String message) {
        super(message);
    }

    public RedisLockCallbackException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisLockCallbackException(Throwable cause) {
        super(cause);
    }

    protected RedisLockCallbackException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
