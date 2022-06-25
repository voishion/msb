package com.meishubao.sample.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author lilu
 */
@Configuration
@ConditionalOnProperty(prefix = "knife4j", name = "enable", havingValue = "true")
@EnableSwagger2WebMvc
@RequiredArgsConstructor
@Import(BeanValidatorPluginsConfiguration.class)
public class Knife4jConfig {

    @Value("${spring.profiles.active:default}")
    private String active;

    @Value("${knife4j.setting.packages:com.artworld}")
    private String packages;

    /**
     * 引入Knife4j提供的扩展类
     **/
    private final OpenApiExtensionResolver openApiExtensionResolver;

    /**
     * 设置显示的swagger环境信息
     **/
    private final Set<String> profiles = Sets.newHashSet("local", "default", "dev", "test");

    @Bean
    public Docket createRestApi() {
        String groupName = active;
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(this.profiles.contains(active))
                .apiInfo(apiInfo())
                .groupName(groupName)
                .select()
                //此包路径下的类，才生成接口文档
                .apis(RequestHandlerSelectors.basePackage(packages))
                //加了ApiOperation注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildExtensions(groupName))
                .globalOperationParameters(operationParameters());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("开发示例 - 接口文档")
                .description("本项目为开发示例，用于平时集成测试、技术研究、示例测试等功能，基于JDK17。")
                .contact(new Contact("LI LU", "https://gitee.com/voishion", "voishion@foxmail.com"))
                .version("v1.0.0")
                .build();
    }

    private List<Parameter> operationParameters() {
        List<Parameter> globalRequestParameters = new ArrayList<>();
        globalRequestParameters.add(new ParameterBuilder()
                .parameterType("header")
                .name("Authorization")
                .description("票据令牌信息")
                .required(false)
                .modelRef(new ModelRef("string"))
                .defaultValue("Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJ1c2VyIiwic3ViIjoidXNlciIsImlhdCI6MTU4OTI4MjIyNiwiYXVkIjoiVVNFUiIsImV4cCI6MTU5NzkyMjIyNn0.2TvWyvXYLYc3m22w-7PPzVczLSwswBGE_zTCflVWRoD3SQjq8pOEDSGnAMUppV776D3IST8EAL-bktQ-XPnzpQ")
                .build());
        return globalRequestParameters;
    }

    /**
     * https://stackoverflow.com/questions/69108273/spring-boot-swagger-documentation-doesnt-work/69814964
     */
    @Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(
            WebEndpointsSupplier webEndpointsSupplier,
            ServletEndpointsSupplier servletEndpointsSupplier,
            ControllerEndpointsSupplier controllerEndpointsSupplier,
            EndpointMediaTypes endpointMediaTypes,
            CorsEndpointProperties corsProperties,
            WebEndpointProperties webEndpointProperties,
            Environment environment) {
        List<ExposableEndpoint<?>> allEndpoints = Lists.newLinkedList();
        Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
        String basePath = webEndpointProperties.getBasePath();
        EndpointMapping endpointMapping = new EndpointMapping(basePath);
        boolean shouldRegisterLinksMapping = shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
        return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes,
                corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath),
                shouldRegisterLinksMapping);
    }

    private boolean shouldRegisterLinksMapping(
            WebEndpointProperties webEndpointProperties,
            Environment environment,
            String basePath) {
        return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath)
                || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
    }

}
