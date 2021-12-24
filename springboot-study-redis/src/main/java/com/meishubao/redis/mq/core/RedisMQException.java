package com.meishubao.redis.mq.core;

/**
 * Redis消息队列异常
 *
 * @author lilu
 */
public class RedisMQException extends RuntimeException {

    public RedisMQException() {
        super();
    }

    public RedisMQException(String message) {
        super(message);
    }

    public RedisMQException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisMQException(Throwable cause) {
        super(cause);
    }

    /**
     * for better performance
     *
     * @return Throwable
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

}
