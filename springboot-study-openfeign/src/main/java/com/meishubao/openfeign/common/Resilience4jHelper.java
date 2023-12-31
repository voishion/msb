package com.meishubao.openfeign.common;

import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.retry.MaxRetriesExceededException;
import org.slf4j.Logger;
import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;

import java.util.Objects;
import java.util.concurrent.TimeoutException;

/**
 * 熔断常量接口
 *
 * @author lilu
 */
public interface Resilience4jHelper {

    String DEFAULT = "default";

    String TIMEOUT2_MN = "timeout2";
    String TIMEOUT2_FM = TIMEOUT2_MN + "Fallback";

    String SELECT_CURRENT_LIVE_MN = "selectCurrentLive";
    String SELECT_CURRENT_LIVE_FM = SELECT_CURRENT_LIVE_MN + "Fallback";

    String GET_LIVE_LINK_MN = "getLiveLink";
    String GET_LIVE_LINK_FM = GET_LIVE_LINK_MN + "Fallback";

    String GET_USER_LIVE_ACTIVITY_MN = "getUserLiveActivity";
    String GET_USER_LIVE_ACTIVITY_FM = GET_USER_LIVE_ACTIVITY_MN + "Fallback";

    String GET_USER_LIVE_ACTIVITY_LINK_MN = "getUserLiveActivityLink";
    String GET_USER_LIVE_ACTIVITY_LINK_FM = GET_USER_LIVE_ACTIVITY_LINK_MN + "Fallback";

    /**
     * 处理熔断异常
     *
     * @param throwable
     * @return
     */
    default void handleException(Throwable throwable) {
        handleException(throwable, null);
    }
    /**
     * 处理熔断异常
     *
     * @param throwable 异常对象
     * @param log 日志对象
     * @return
     */
    default void handleException(Throwable throwable, Logger log) {
        String error = null;
        if (throwable instanceof NoFallbackAvailableException) {
            error = "断路器无回退可用";
        } else if (throwable instanceof CallNotPermittedException) {
            error = "接口熔断";
        } else if (throwable instanceof BulkheadFullException) {
            error = "隔舱保护";
        } else if (throwable instanceof RequestNotPermitted) {
            error = "流量限制";
        } else if (throwable instanceof TimeoutException) {
            error = "访问超时";
        } else if (throwable instanceof MaxRetriesExceededException) {
            error = "频率保护";
        } else {
            error = "服务降级";
        }
        if (Objects.nonNull(log)) {
            log.error("触发断路器规则，error={}，throwable={}，throwableClass={}", error, throwable.getMessage(), throwable.getClass().getName());
        }
    }

}
