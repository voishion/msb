package com.meishubao.swagger3.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author lilu
 */
@Profile({"locale", "default", "dev", "test", "prod"})
@Configuration
// 手动开启Swagger3
@EnableOpenApi
@Import(BeanValidatorPluginsConfiguration.class)
public class Knife4jConfig {

    @Value("${spring.profiles.active}")
    private String active;

    private final BuildProperties buildProperties;
    private final OpenApiExtensionResolver openApiExtensionResolver;

    public Knife4jConfig(BuildProperties buildProperties, OpenApiExtensionResolver openApiExtensionResolver) {
        this.buildProperties = buildProperties;
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    @Bean
    public Docket createRestApi() {
        // Swagger 2 使用的是：DocumentationType.SWAGGER_2
        // Swagger 3 使用的是：DocumentationType.OAS_30
        return new Docket(DocumentationType.OAS_30)
                // 定义是否开启swagger，false为关闭，可以通过变量控制
                .enable(true)
                .apiInfo(new ApiInfoBuilder()
                        .title("美术宝文档")
                        .description("小熊艺术-[" + active + "]-" + buildProperties.getName())
                        .termsOfServiceUrl("https://ai-app-" + active + ".meixiu.mobi/music/wk/doc.html")
                        .version(buildProperties.getVersion())
                        .contact(new Contact("voishion", "https://gitee.com/voishion", "voishion@foxmail.com"))
                        .build())
                        .groupName(active)
                        .extensions(openApiExtensionResolver.buildExtensions(active))
                        .select()
                        //.apis(RequestHandlerSelectors.withMethodAnnotation(RestController.class))
                        .apis(RequestHandlerSelectors.basePackage("com.meishubao.swagger3.controller"))
                        .paths(PathSelectors.any())
                        .build();
    }

}
