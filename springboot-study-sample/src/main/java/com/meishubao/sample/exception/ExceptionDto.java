package com.meishubao.sample.exception;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @author geekren
 */
@Data
@Builder
public class ExceptionDto {
    private String hostName;
    private String errorTime;
    private String error;
    private String resources;

    private String requestParameters;
    private Map<String,Object> requestHeaders;
}
