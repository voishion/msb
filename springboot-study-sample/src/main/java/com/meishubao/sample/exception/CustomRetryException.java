package com.meishubao.sample.exception;

import org.springframework.retry.RetryException;

/**
 * 自定义重试异常
 *
 * @author lilu
 */
public class CustomRetryException extends RetryException {

    public CustomRetryException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public CustomRetryException(String msg) {
        super(msg);
    }

}
