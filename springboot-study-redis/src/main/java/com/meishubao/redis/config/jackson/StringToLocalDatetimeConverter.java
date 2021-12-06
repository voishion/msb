package com.meishubao.redis.config.jackson;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 将String数据类型反序列化为LocalDateTime<br>
 * <pre>
 * 使用如下:
 * <code>@JsonDeserialize(converter = StringToLocalDatetimeConverter.class)
 * private LocalDateTime birthDate;
 * </code></pre>
 *
 * @author lilu
 */
public class StringToLocalDatetimeConverter extends StdConverter<String, LocalDateTime> {

    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DatePattern.UTC_MS_PATTERN);

    @Override
    public LocalDateTime convert(String value) {
        return LocalDateTime.parse(value, LocalDateTimeToStringConverter.DATE_FORMATTER);
    }

}