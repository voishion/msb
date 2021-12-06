package com.meishubao.redis.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.meishubao.redis.config.jackson.LocalDateTimeToStringConverter;
import com.meishubao.redis.config.jackson.StringToLocalDatetimeConverter;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lilu
 */
@Data
@ToString
public class Person implements Serializable {

    private Long id;

    @JsonSerialize(converter = LocalDateTimeToStringConverter.class)
    @JsonDeserialize(converter = StringToLocalDatetimeConverter.class)
    private LocalDateTime mtime;

//    private Integer age;

}
