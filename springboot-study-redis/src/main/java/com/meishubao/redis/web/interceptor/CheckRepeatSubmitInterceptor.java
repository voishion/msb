package com.meishubao.redis.web.interceptor;

import cn.hutool.core.util.StrUtil;
import com.meishubao.redis.annotation.CheckRepeatSubmit;
import com.meishubao.redis.constant.RequestAttributeConstant;
import com.meishubao.redis.service.SubmitTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 检查重复提交拦截器
 *
 * @author lilu
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class CheckRepeatSubmitInterceptor implements HandlerInterceptor {

    private final SubmitTokenService submitTokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }

        Method method = handlerMethod.getMethod();
        CheckRepeatSubmit annotation = method.getAnnotation(CheckRepeatSubmit.class);
        if (Objects.nonNull(annotation)) {
            check(request);
        }

        return true;
    }

    /**
     * 幂等性校验, 校验通过则放行, 校验失败则抛出异常, 并通过统一异常处理返回友好提示
     *
     * @param request
     */
    private void check(HttpServletRequest request) {
        String token = request.getHeader(RequestAttributeConstant.SUBMIT_TOKEN);
        if (StrUtil.isBlank(token)) {
            token = request.getParameter(RequestAttributeConstant.SUBMIT_TOKEN);
            if (StrUtil.isBlank(token)) {
                throw new RuntimeException("提交令牌不在请求参数中");
            }
        }
        String value = null;
        if (request instanceof BodyRequestWrapper wrapper) {
            value = wrapper.getBody();
        }
        if (StrUtil.isBlank(value)) {
            throw new RuntimeException("Body数据不能为空");
        }
        boolean validToken = submitTokenService.validToken(token, value);
        if (!validToken) {
            throw new RuntimeException("重复提交");
        }
    }

}
