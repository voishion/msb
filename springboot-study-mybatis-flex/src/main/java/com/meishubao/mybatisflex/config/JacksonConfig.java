package com.meishubao.mybatisflex.config;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
 
/**
 * @author roc
 */
@Configuration
public class JacksonConfig {
 
    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    private String pattern;
 
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN);
            // 返回日期数据序列化
            builder.serializerByType(LocalDate.class, new LocalDateSerializer(localDateFormatter));
            // 接收日期数据反序列化
            builder.deserializerByType(LocalDate.class, new LocalDateDeserializer(localDateFormatter));

            DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            // 返回日期时间数据序列化
            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(localDateTimeFormatter));
            // 接收日期时间数据反序列化
            builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(localDateTimeFormatter));
        };
    }
 
}