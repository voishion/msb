package com.meishubao.admin.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import de.codecentric.boot.admin.server.config.AdminServerProperties;

/**
 * 安全配置类
 * <p>
 * 没有安全配置的SpringBoot Admin监控微服务，所有的服务都会暴露给外部，一旦SBD地址泄露，那我们的服务将毫无安全可言，所以我
 * 们需要给SBD配置安全策略。Web应用的身份认证和授权方式有多种方法，Spring Boot Admin不提供默认方法。默认情况下，
 * spring-boot-admin-server-ui提供登录页面和注销按钮，我们使用Spring Security实现安全认证。
 *
 * @author lilu
 */
@Configuration
@EnableWebSecurity
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {

    private final String adminContextPath;

    public SecuritySecureConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler();
        handler.setTargetUrlParameter("redirectTo");
        handler.setDefaultTargetUrl(this.adminContextPath + "/");

        // 启用HTTP-Basic支持。这是Spring Boot Admin Client注册所必需的
        http.httpBasic().and()
                // 授予对所有静态资产和登录页面的公共访问权限
                .authorizeRequests().antMatchers(this.adminContextPath + "/assets/**").permitAll()
                // 授予对所有静态资产和登录页面的公共访问权限
                .antMatchers(this.adminContextPath + "/login").permitAll().and()
                // 所有请求都需要验证登录
                .authorizeRequests().anyRequest().authenticated().and()
                // 登录表单
                .formLogin().loginPage(this.adminContextPath + "/login").successHandler(handler).and()
                // 登出表单
                .logout().logoutUrl(this.adminContextPath + "/logout").and().csrf()
                //	Enables CSRF-Protection using Cookies
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).ignoringAntMatchers(
                        //	将服务注册的接口暴露出去.
                        this.adminContextPath + "/instances",
                        this.adminContextPath + "/actuator/**");
        ;
    }
}

