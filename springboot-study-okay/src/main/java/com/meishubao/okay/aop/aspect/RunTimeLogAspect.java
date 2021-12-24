package com.meishubao.okay.aop.aspect;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meishubao.okay.aop.annotation.RunTimeLog;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;

/**
 * 执行时间日志切面
 *
 * @author lilu
 */
@Log4j2
@Aspect
@Component
public class RunTimeLogAspect {

    @Around("@annotation(runTimeLog)")
    @SneakyThrows
    public Object around(ProceedingJoinPoint point, RunTimeLog runTimeLog) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object obj;
        try {
            obj = point.proceed();
        } finally {
            stopWatch.stop();
            String className = point.getTarget().getClass().getSimpleName();
            String methodName = point.getSignature().getName();
            if (StrUtil.isBlank(runTimeLog.value())) {
                log.info("记录方法执行 -> 类:{}, 方法名:{}, 执行时长:{}ms, 参数列表:{}", className, methodName, stopWatch.getTotalTimeMillis(), paramToJson(point));
            } else {
                log.info("记录方法执行 ->【{}】类:{}, 方法名:{}, 执行时长:{}ms, 参数列表:{}", runTimeLog.value(), className, methodName, stopWatch.getTotalTimeMillis(), paramToJson(point));
            }
        }
        return obj;
    }

    private String paramToJson(ProceedingJoinPoint point) {
        String[] names = ((MethodSignature) point.getSignature()).getParameterNames();
        Object[] args = point.getArgs();
        Map<String, Object> params = new HashMap<>();
        if (names != null && names.length > 0 && args != null && args.length > 0) {
            for (int i = 0; i < names.length ; i++){
                params.put(names[i], args[i]);
            }
        }
        try {
            return new ObjectMapper().writeValueAsString(params);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }

}
