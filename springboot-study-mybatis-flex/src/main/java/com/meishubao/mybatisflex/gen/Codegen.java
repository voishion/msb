package com.meishubao.mybatisflex.gen;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.EntityConfig;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.service.IService;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.zaxxer.hikari.HikariDataSource;

import java.io.File;

public class Codegen {

    private final static String PACKAGE_PATH = StrUtil.format("{}/src/main/java/{}",
            new File("").getAbsoluteFile(),
            Codegen.class.getPackage().getName().replace(".", File.separator));

    public static void main(String[] args) {
        String applicationName = "springboot-study-mybatis-flex";

        // 配置数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/springboot_study_sample");
        dataSource.setUsername("root");
        dataSource.setPassword("abc123");

        // 创建配置内容，两种风格都可以。
        String projectDir = StrUtil.format("{}/{}", new File("").getAbsoluteFile(), applicationName);
        String basePackage = Codegen.class.getPackage().getName();
        GlobalConfig globalConfig = createGlobalConfigUseStyle(projectDir, basePackage);

        // 通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, globalConfig);

        // 生成代码
        generator.generate();
    }

    public static GlobalConfig createGlobalConfigUseStyle(String projectDir, String basePackage) {
        // 创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();

        // 注释配置
        globalConfig.getJavadocConfig()
                .setSince(DateTime.now().toString(DatePattern.NORM_DATE_PATTERN))
                .setAuthor("lilu");

        // 包配置
        globalConfig.getPackageConfig()
                .setSourceDir(projectDir + "/src/main/java")
                .setBasePackage(basePackage)
                .setEntityPackage(globalConfig.getBasePackage() + ".domain.entity")
                .setMapperPackage(globalConfig.getBasePackage() + ".mapper")
                .setServicePackage(globalConfig.getBasePackage() + ".service")
                .setServiceImplPackage(globalConfig.getBasePackage() + ".service.impl")
                .setControllerPackage(globalConfig.getBasePackage() + ".controller")
                .setTableDefPackage(globalConfig.getEntityPackage() + ".tables")
                .setMapperXmlPath(projectDir + "/src/main/resources/mapper")
        ;

        // 策略配置
        globalConfig.getStrategyConfig()
                .setVersionColumn("version")
                .setLogicDeleteColumn("deleted")
                .setTablePrefix("tb_", "t_", "sys_")
                .setGenerateTable("tb_account", "tb_device");

        // 模板配置
        globalConfig.getTemplateConfig()
                .setEntity("/templates/kitty/entity.tpl")
                .setMapper("/templates/kitty/mapper.tpl")
                .setService("/templates/kitty/service.tpl")
                .setServiceImpl("/templates/kitty/serviceImpl.tpl")
                .setController("/templates/kitty/controller.tpl")
                .setTableDef("/templates/kitty/tableDef.tpl")
                .setMapperXml("/templates/kitty/mapperXml.tpl")
        ;
        System.out.println(globalConfig.getTemplateConfig().getEntity());

        // Entity 生成配置
        globalConfig.setEntityGenerateEnable(true);
        globalConfig.getEntityConfig()
                .setOverwriteEnable(true)
                .setWithLombok(true)
                .setWithSwagger(true)
                .setSwaggerVersion(EntityConfig.SwaggerVersion.FOX);

        // Mapper 生成配置
        globalConfig.setMapperGenerateEnable(true);
        globalConfig.getMapperConfig()
                .setOverwriteEnable(true)
                .setSuperClass(BaseMapper.class);

        // MapperXml 生成配置
        globalConfig.setMapperXmlGenerateEnable(true);
        globalConfig.getMapperXmlConfig()
                .setOverwriteEnable(true)
                .setFileSuffix("Mapper");

        // Service 生成配置
        globalConfig.setServiceGenerateEnable(true);
        globalConfig.getServiceConfig()
                .setOverwriteEnable(true)
                .setSuperClass(IService.class);

        // ServiceImpl 生成配置
        globalConfig.setServiceImplGenerateEnable(true);
        globalConfig.getServiceImplConfig()
                .setOverwriteEnable(true)
                .setClassSuffix("ServiceImpl")
                .setSuperClass(ServiceImpl.class);

        // Controller 生成配置
        globalConfig.setControllerGenerateEnable(true);
        globalConfig.getControllerConfig()
                .setOverwriteEnable(true)
                .setClassSuffix("Controller");

        return globalConfig;
    }

}