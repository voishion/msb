package com.meishubao.mybatisflex;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.meishubao.mybatisflex.mapper")
public class SpringbootStudyMybatisFlexApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootStudyMybatisFlexApplication.class, args);
    }

}
