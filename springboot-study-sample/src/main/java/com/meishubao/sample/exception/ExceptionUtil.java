package com.meishubao.sample.exception;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.meishubao.sample.constant.RequestAttributeConstant;
import com.meishubao.sample.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author 任元龙
 */
@Slf4j
public class ExceptionUtil {
    private final static char COLON = ':';
    private static String HOSTNAME;

    static {
        try {
            HOSTNAME = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            HOSTNAME = "UnknownHost";
        }
    }

    public static String print(HttpServletRequest request, Throwable throwable) {
        try {
            return getString(request, throwable, HOSTNAME, getRequestTime());
        } catch (JsonProcessingException e) {
            log.error("处理异常日志失败:{}",e);
        }
        return StrUtil.EMPTY;
    }

    /**
     * 格式化异常信息
     * @param request 请求
     * @param throwable 异常
     * @param hostname
     * @param requestTime
     * @return
     */
    public static String getString(HttpServletRequest request, Throwable throwable, String hostname, String requestTime) throws JsonProcessingException {
        StringBuffer requestParameters = new StringBuffer();
        request.getParameterMap().keySet().forEach(
                item -> requestParameters.append(item).append(ExceptionUtil.COLON).append(request.getParameter(item))
        );

        if(StrUtil.isBlank(requestParameters)){
            requestParameters.append(null == request.getAttribute(RequestAttributeConstant.ATTRIBUTE_REQUEST_BODY)? StrUtil.EMPTY:request.getAttribute(RequestAttributeConstant.ATTRIBUTE_REQUEST_BODY));
        }

        Enumeration<String> headers = request.getHeaderNames();
        String key;

        Map<String,Object> requestHeaders = Maps.newHashMap();
        while (headers.hasMoreElements()) {
            key = headers.nextElement();
            if(StrUtil.isNotBlank(key)){
                requestHeaders.put(key,request.getHeader(key));
            }
        }

        ExceptionDto dto = ExceptionDto.builder()
                .hostName(hostname)
                .errorTime(requestTime)
                .error(Throwables.getRootCause(throwable).getMessage())
                .resources(request.getMethod() + StrUtil.SPACE + request.getRequestURI())
                .requestParameters(Convert.toStr(requestParameters))
                .requestHeaders(requestHeaders)
                .build();

        return JsonUtils.toJson(dto);
    }

    private static String getRequestTime() {
        return LocalDateTime.now(ZoneOffset.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
