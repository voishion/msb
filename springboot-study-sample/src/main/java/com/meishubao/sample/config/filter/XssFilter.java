package com.meishubao.sample.config.filter;


import com.meishubao.sample.config.wrapper.XssHttpServletRequestWrapper;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 防止XSS（跨站脚本攻击）以及SQL注入攻击
 *
 * @author lilu
 */
@WebFilter(filterName = "xssFilter", urlPatterns = "/*", asyncSupported = true)
public class XssFilter implements Filter {

    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 忽略权限检查的url地址
     */
    private final String[] excludeUrls = new String[]{
            "/file/**", "/file/upload"
    };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取请求你ip后的全部路径
        String uri = req.getRequestURI();
        //跳过不需要的Xss校验的地址
        for (String str : excludeUrls) {
            if (pathMatcher.match(str, uri)) {
                filterChain.doFilter(servletRequest, response);
                return;
            }
        }
        //注入xss过滤器实例
        XssHttpServletRequestWrapper reqW = new XssHttpServletRequestWrapper(req);
        //过滤
        filterChain.doFilter(reqW, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
