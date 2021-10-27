package com.meishubao.openfeign.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author lilu
 */
@Configuration
@EnableSwagger2WebMvc
public class Swagger2Config {

    @Autowired
    private Environment environment;

    /*引入Knife4j提供的扩展类*/
    @Autowired
    private OpenApiExtensionResolver openApiExtensionResolver;

    // 设置显示的swagger环境信息
    Profiles profiles = Profiles.of("default", "dev", "test");

    @Bean
    public Docket createRestApi() {
        String groupName = environment.getDefaultProfiles()[0];
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(environment.acceptsProfiles(this.profiles))
                .apiInfo(apiInfo())
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildExtensions(groupName));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Fantasy All Star - 接口文档")
                .description("本项目模拟了回合制对战类游戏，让玩家能够体验到来自不同游戏、动漫、影视、小说中的经典人物互相组队对战的畅爽快感。")
                .contact(new Contact("Leif Liu", null, "leif1995@dingtalk.com"))
                .version("v1.0.0")
                .build();
    }

}
