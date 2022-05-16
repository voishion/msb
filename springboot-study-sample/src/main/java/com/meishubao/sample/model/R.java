package com.meishubao.sample.model;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.util.Objects;

/**
 * 通用返回对象
 *
 * @param <T>
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "通用返回对象")
public class R<T> {

    private static final String MESSAGE_BASE_EXCEPTION = "服务升级中，请稍后重试";
    private static final String MESSAGE_BASE_SUCCESS = "处理成功";

    @ApiModelProperty(value = "业务状态", example = "SUCCESS")
    private Status status;

    @ApiModelProperty(value = "业务状态码", example = "200")
    private Integer code;

    @ApiModelProperty(value = "响应信息", example = MESSAGE_BASE_SUCCESS)
    private String message;

    @ApiModelProperty(value = "数据载体")
    private T payload;

    public R(int code, String msg, T obj) {
        setStatus(Status.SUCCESS);
        setCode(code);
        setMessage(msg);
        setPayload(obj);
    }

    public static <T> R<T> create(Status status, T payload) {
        R<T> response = new R<>();
        response.setStatus(status);
        response.setCode(status.getCode());
        response.setMessage(status.getMessage());

        if (Objects.nonNull(payload)) {
            response.setPayload(payload);
        }
        return response;
    }

    public static <T> R<T> create(int code, String message) {
        return new R<T>()
                .setCode(code)
                .setMessage(StrUtil.isBlank(message) ? StrUtil.EMPTY : message);
    }

    public static <T> R<T> customStatus(Status status, String message, T payload) {
        return new R<T>()
                .setStatus(status)
                .setCode(status.getCode())
                .setMessage(StrUtil.isBlank(message) ? status.getMessage() : message)
                .setPayload(payload);
    }

    public static R ok() {
        return create(Status.SUCCESS, null);
    }

    public static <T> R<T> ok(T payload) {
        return create(Status.SUCCESS, payload);
    }

    public static <T> R<T> failure(Integer code, String message) {
        R<T> response = create(Status.INTERNAL_SERVER_ERROR, null);
        for (Status value : Status.values()) {
            if (code.equals(value.getCode())) {
                response = create(value, null);
            }
        }
        response.setMessage(message);
        return response;
    }

    public static <T> R<T> exception(Status status, String message, Throwable exception) {
        R<T> response = new R<T>();
        response.setStatus(status);
        response.setCode(status.getCode());
        response.setMessage(StrUtil.isBlank(message) ? status.getMessage() : message);
        return response;
    }

    public static <T> R<T> exceptionByCustom(int code, String message, Throwable exception) {
        R<T> response = new R<T>();

        response.setStatus(Status.INTERNAL_SERVER_ERROR);
        response.setCode(code);
        response.setMessage(StrUtil.isBlank(message) ? MESSAGE_BASE_EXCEPTION : message);
        return response;
    }

    @Getter
    @AllArgsConstructor
    public enum Status {
        SUCCESS(HttpStatus.OK.value(), MESSAGE_BASE_SUCCESS),

        UN_AUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "未认证授权,请重新登陆"),

        REQ_REJECT(HttpStatus.FORBIDDEN.value(), "无权访问"),

        NOT_FOUND(HttpStatus.NOT_FOUND.value(), "不存在"),

        METHOD_NOT_SUPPORTED(HttpStatus.METHOD_NOT_ALLOWED.value(), "请匹配正确的请求方式"),

        MEDIA_TYPE_NOT_SUPPORTED(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), "媒体类型不匹配"),

        PARAM_VALID_ERROR(HttpStatus.BAD_REQUEST.value(), "客户端请求不合法"),

        INTERNAL_SERVER_UNAVAILABLE(499, "服务繁忙"),

        INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), MESSAGE_BASE_EXCEPTION);

        final int code;
        final String message;
    }

}

