package com.meishubao.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import javax.annotation.PostConstruct;
import javax.servlet.MultipartConfigElement;
import java.util.TimeZone;

@SpringBootApplication
@ServletComponentScan(basePackages = {"com.meishubao.sample.config.filter"})
public class SpringbootStudySampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootStudySampleApplication.class, args);
    }

    /**
     * 解决时区问题
     */
    @PostConstruct
    void setDefaultTimezone(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    //允许多请求地址多加斜杠  比如 /msg/list   //msg/list
    //@Bean
    //public HttpFirewall httpFirewall() {
    //    return new DefaultHttpFirewall();
    //}

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个数据大小
        factory.setMaxFileSize(DataSize.of(204800000, DataUnit.KILOBYTES)); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize(DataSize.of(2048000000, DataUnit.KILOBYTES));
        factory.setLocation("tmp/");
        return factory.createMultipartConfig();
    }

}
