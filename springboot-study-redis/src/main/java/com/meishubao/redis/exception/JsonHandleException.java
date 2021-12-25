package com.meishubao.redis.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author lilu
 */
public class JsonHandleException extends JsonProcessingException {

    public JsonHandleException(String msg) {
        super(msg, null, null);
    }

}
