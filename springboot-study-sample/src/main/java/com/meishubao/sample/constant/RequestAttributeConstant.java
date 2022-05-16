package com.meishubao.sample.constant;

/**
 * @author geekren
 */
public class RequestAttributeConstant {
    /**
     *  当入参数为json类型的时候传递参数
     */
    public static final String ATTRIBUTE_REQUEST_BODY = "requestBody";
    /**
     *  Feign请求URI前缀
     */
    public static final String FEIGN_REQUEST_PATTERN = "/resource/**";
    /**
     *  Feign请求标志键
     */
    public static final String FEIGN_REQUEST_KEY = "artworld-feign-from";
    /**
     *  Feign请求标志值
     */
    public static final String FEIGN_REQUEST_VALUE = "artworld-service-feign";

}
