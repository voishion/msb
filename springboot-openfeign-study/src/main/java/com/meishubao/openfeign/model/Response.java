package com.meishubao.openfeign.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author zhoufei
 */
@Slf4j
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {
    private Status status;
    private Integer code;
    private T payload;
    private Object errors;
    private Object metadata;

    public static <T> Response<T> create(Status status, T payload) {
        Response<T> response = new Response<T>();
        response.setStatus(status);
        response.setCode(status.ordinal());
        response.setPayload(payload);
        return response;
    }

    public static <T> Response<T> customStatus(Status status, T payload) {
        return customStatus(status, null, payload);
    }

    public static <T> Response<T> customStatus(Status status, String message, T payload) {
        return new Response<T>()
                .setStatus(status)
                .setCode(status.getErrorCode())
                .setErrors(StringUtils.isEmpty(message) ? status.getErrorMsg() : message)
                .setPayload(payload);
    }

    public static <T> Response<T> customStatus(Status status, String message) {
        return customStatus(status, message, null);
    }

    public static <T> Response<T> customStatus(Status status) {
        return customStatus(status, null);
    }
    public static Response badRequest() {
        return create(Status.BAD_REQUEST, null);
    }

    public static Response ok() {
        return create(Status.OK, null);
    }

    @Deprecated
    public static <T> Response<T> ok(T payload) {
        return create(Status.OK, payload);
    }

    public static <T> Response<T> ok(Optional<T> payload) {
        return ok(payload.get());
    }

    public enum Status {
        OK,
        BAD_REQUEST,
        UNAUTHORIZED,
        VALIDATION_EXCEPTION,
        EXCEPTION,
        WRONG_CREDENTIALS,
        ACCESS_DENIED,
        NOT_FOUND,
        DUPLICATE_ENTITY,
        /**
         * 拼团状态
         */
        GROUPON,
        /**
         * 拼团完成，错误
         */
        GROUP_COMPLETED_EXCEPTION,

        /**
         * 用户已注销
         */
        USER_LOGOFF(100001, "用户已注销");

        private Integer errorCode;
        private String errorMsg;
        Status(){
        }
        Status(Integer errorCode, String errorMsg) {
            this.errorCode = errorCode;
            this.errorMsg = errorMsg;
        }

        public Integer getErrorCode() {
            return errorCode;
        }

        public String getErrorMsg() {
            return errorMsg;
        }
    }
}
