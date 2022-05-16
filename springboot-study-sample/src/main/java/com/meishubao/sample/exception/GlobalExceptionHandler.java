package com.meishubao.sample.exception;

import cn.hutool.core.util.StrUtil;
import com.meishubao.sample.model.R;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.management.ReflectionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.nio.file.AccessDeniedException;
import java.util.Objects;
import java.util.Set;

/**
 * @author renyuanlong
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value("${spring.application.name}")
    private String application;

    @Value("${spring.profiles.active}")
    private String active;

    @ExceptionHandler(CustomRetryException.class)
    public R<?> exceptionHandler(HttpServletRequest request, CustomRetryException exception) {
        String content = ExceptionUtil.print(request, exception);
        log.error(content, exception);

        return R.exceptionByCustom(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "服务升级中，请稍后重试", exception);
    }

    @ExceptionHandler(BusinessException.class)
    public R<?> exceptionHandler(HttpServletRequest request, BusinessException exception) {
        String content = ExceptionUtil.print(request, exception);
        log.error(content, exception);

        return R.exceptionByCustom(HttpServletResponse.SC_SERVICE_UNAVAILABLE, exception.getMessage(), exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<?> exceptionHandler(HttpServletRequest request, MethodArgumentNotValidException exception) {
        String content = ExceptionUtil.print(request, exception);
        log.error(content, exception);

        BindingResult result = exception.getBindingResult();
        FieldError error = result.getFieldError();
        String msg = error.getDefaultMessage();

        return R.exception(R.Status.PARAM_VALID_ERROR, msg, exception);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public R<?> exceptionHandler(HttpServletRequest request, NoHandlerFoundException exception) {
        String content = ExceptionUtil.print(request, exception);
        log.error(content, exception);

        return R.exception(R.Status.NOT_FOUND, "路径不存在，请检查路径是否正确", exception);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public R<?> exceptionHandler(HttpServletRequest request, BindException exception) {
        String message = exception.getAllErrors().get(0).getDefaultMessage();

        String content = ExceptionUtil.print(request, exception);
        log.error(content, exception);

        return R.exception(R.Status.PARAM_VALID_ERROR, message, exception);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(ReflectionException.class)
    public R<?> exceptionHandler(HttpServletRequest request, ReflectionException exception) {
        String content = ExceptionUtil.print(request, exception);
        log.error(content, exception);

        return R.exception(R.Status.INTERNAL_SERVER_ERROR, "服务异常", exception);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<?> exceptionHandler(HttpServletRequest request, HttpRequestMethodNotSupportedException exception) {
        String content = ExceptionUtil.print(request, exception);
        log.error(content, exception);

        return R.exception(R.Status.METHOD_NOT_SUPPORTED, "请检查请求方式", exception);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R<?> exceptionHandler(HttpServletRequest request, MissingServletRequestParameterException exception) {
        String content = ExceptionUtil.print(request, exception);
        log.error(content, exception);

        return R.exception(R.Status.PARAM_VALID_ERROR, "必要参数缺失", exception);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R<?> exceptionHandler(HttpServletRequest request, HttpMessageNotReadableException exception) {
        String message = "请求体读取失败";
        if (Objects.requireNonNull(exception.getMessage()).contains("Required request body is missing")) {
            message = "请求体缺失";
        }

        String content = ExceptionUtil.print(request, exception);
        log.error(content, exception);

        return R.exception(R.Status.PARAM_VALID_ERROR, message, exception);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public R<?> exceptionHandler(HttpServletRequest request, MethodArgumentTypeMismatchException exception) {
        String message = "参数类型匹配失败";

        String content = ExceptionUtil.print(request, exception);
        log.error(content, exception);

        return R.exception(R.Status.PARAM_VALID_ERROR, message, exception);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public R<?> exceptionHandler(HttpServletRequest request, ConstraintViolationException exception) {
        String content = ExceptionUtil.print(request, exception);
        log.error(content, exception);

        Set<ConstraintViolation<?>> set = exception.getConstraintViolations();
        StringBuffer errorInfo = new StringBuffer();
        for (ConstraintViolation constraintViolation : set) {
            final String[] paramName = {""};
            constraintViolation.getPropertyPath().forEach(n -> paramName[0] = n.getName());
            String message = constraintViolation.getMessage();
            String messageTemplate = constraintViolation.getMessageTemplate();
            if (messageTemplate.equals(message)) {
                errorInfo.append("," + constraintViolation.getMessage());
            } else {
                errorInfo.append(", param " + paramName[0] + " " + constraintViolation.getMessage());
            }
        }
        if (errorInfo.length() > 2) {
            errorInfo.delete(0, 2);
        }
        return R.exception(R.Status.PARAM_VALID_ERROR, errorInfo.toString(), exception);
    }

    /**
     * 参数校验失败
     */
    @ExceptionHandler(ValidationException.class)
    public R<?> exceptionHandler(HttpServletRequest request, ValidationException exception) {
        String content = ExceptionUtil.print(request, exception);
        log.error(content, exception);

        return R.exception(R.Status.PARAM_VALID_ERROR, exception.getMessage(), exception);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public R<?> exceptionHandler(HttpServletRequest request, AccessDeniedException exception) {
        String content = ExceptionUtil.print(request, exception);
        log.error(content, exception);

        return R.exception(R.Status.REQ_REJECT, exception.getMessage(), exception);
    }

    @ExceptionHandler(FeignException.class)
    public R<?> exceptionHandler(HttpServletRequest request, FeignException exception) {
        String content = ExceptionUtil.print(request, exception);
        log.error(content, exception);

        return R.exception(R.Status.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
    }

    @ExceptionHandler(Exception.class)
    public R<?> exceptionHandler(HttpServletRequest request, Exception exception) {
        String content = ExceptionUtil.print(request, exception);
        log.error(content, exception);

        return R.exception(R.Status.INTERNAL_SERVER_ERROR, StrUtil.EMPTY, exception);
    }

    @ExceptionHandler(Throwable.class)
    public R<?> exceptionHandler(HttpServletRequest request, Throwable exception) {
        String content = ExceptionUtil.print(request, exception);
        log.error(content, exception);

        return R.exceptionByCustom(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "服务升级中，请稍后重试", exception);
    }

}
