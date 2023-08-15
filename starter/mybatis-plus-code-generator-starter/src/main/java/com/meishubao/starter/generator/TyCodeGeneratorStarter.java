package com.meishubao.starter.generator;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collections;

/**
 * 通用技术MybatisPlus代码生成器
 *
 * @author lilu
 */
public class TyCodeGeneratorStarter {

    private final String url;
    private final String username;
    private final String password;

    private String projectMiddleDir = StrUtil.EMPTY;

    /**
     * 设置项目中间目录
     *
     * @param projectMiddleDir 中间目录，如：/starter/mybatis-plus-code-generator-starter
     * @return this
     */
    public TyCodeGeneratorStarter setProjectMiddleDir(String projectMiddleDir) {
        if (StrUtil.isNotBlank(projectMiddleDir)) {
            this.projectMiddleDir = projectMiddleDir;
        }
        return this;
    }

    /**
     * 数据源配置
     */
    private DataSourceConfig.Builder dataSourceConfig;
    private String outputDir;
    private String mapperXmlOutputDir;

    public TyCodeGeneratorStarter(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        init();
    }

    private void init() {
        dataSourceConfig = new DataSourceConfig.Builder(url, username, password);
    }

    /**
     * 执行 run
     */
    public void run() {
        runBefore();
        FastAutoGenerator.create(dataSourceConfig)
                // 数据库配置
                //.dataSourceConfig((scanner, builder) -> builder.schema(scanner.apply("请输入表名:")))
                // 全局配置
                .globalConfig((scanner, builder) -> {
                    builder.author(scanner.apply("请输入作者:"));
                    // 注释日期
                    builder.commentDate("yyyy-MM-dd hh:mm:ss");
                    // 指定输出目录
                    builder.outputDir(outputDir);
                    // 禁止打开输出目录，默认打开
                    builder.disableOpenDir();
                    // 开启 swagger 模式	默认值:false
                    builder.enableSwagger();
                })
                // 包配置
                .packageConfig((scanner, builder) -> {
                    // 设置父包名
                    builder.parent(scanner.apply("请输入父包名:"));
                    // 设置mapperXml生成路径
                    builder.pathInfo(Collections.singletonMap(OutputFile.xml, mapperXmlOutputDir));
                })
                // 策略配置
                .strategyConfig((scanner, builder) -> {
                    // 设置需要生成的表名
                    builder.addInclude(scanner.apply("请输入表名，多个表名用','隔开:"));
                    // 设置过滤表前缀
                    builder.addTablePrefix(scanner.apply("请输入过滤表前缀，多个表名用','隔开:"));
                    // Entity 策略配置
                    builder.entityBuilder()
                            // 开启Lombok
                            .enableLombok()
                            // 开启链式模型	默认值:false
                            .enableChainModel()
                            // 覆盖已生成文件
                            .enableFileOverride()
                            // 数据库表映射到实体的命名策略：下划线转驼峰命
                            .naming(NamingStrategy.underline_to_camel)
                            // 数据库表字段映射到实体的命名策略：下划线转驼峰命
                            .columnNaming(NamingStrategy.underline_to_camel)
                    ;
                    // Mapper 策略配置
                    builder.mapperBuilder()
                            .mapperAnnotation(Mapper.class)
                            // 覆盖已生成文件
                            .enableFileOverride();
                    // Service 策略配置
                    builder.serviceBuilder()
                            // 覆盖已生成文件
                            .enableFileOverride()
                            // 格式化 service 接口文件名称，%s进行匹配表名，如 UserService
                            .formatServiceFileName("%sService")
                            // 格式化 service 实现类文件名称，%s进行匹配表名，如 UserServiceImpl
                            .formatServiceImplFileName("%sServiceImpl");
                    // Controller 策略配置
                    builder.controllerBuilder()
                            // 开启生成@RestController 控制器	默认值:false
                            .enableRestStyle()
                            // 覆盖已生成文件
                            .enableFileOverride();
                })
                //.templateConfig(builder -> {
                //    builder.entity("/templates/entity.java");
                //    builder.service("/templates/service.java");
                //    builder.serviceImpl("/templates/serviceImpl.java");
                //    builder.mapper("/templates/mapper.java");
                //    builder.xml("/templates/mapper.xml");
                //    builder.controller("/templates/controller.java");
                //})
                // 模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker 或 Enjoy
                //.templateEngine(new BeetlTemplateEngine())
                //.templateEngine(new FreemarkerTemplateEngine())
                //.templateEngine(new EnjoyTemplateEngine())
                .execute();
    }

    private void runBefore() {
        String userDir = System.getProperty("user.dir");
        outputDir = StrUtil.format("{}{}{}", userDir, projectMiddleDir, "/src/main/java");
        mapperXmlOutputDir = StrUtil.format("{}{}{}", userDir, projectMiddleDir, "/src/main/resources/mapper");
    }

}
