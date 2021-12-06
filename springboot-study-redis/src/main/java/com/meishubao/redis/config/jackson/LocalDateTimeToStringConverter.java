package com.meishubao.redis.config.jackson;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * 将LocalDateTime序列化为String数据类型<br>
 * <pre>
 * 使用如下:
 * <code>@JsonSerialize(converter = LocalDateTimeToStringConverter.class)
 * private LocalDateTime birthDate;
 * </code></pre>
 *
 * @author lilu
 */
public class LocalDateTimeToStringConverter extends StdConverter<LocalDateTime, String> {

    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN, Locale.CHINA);

    @Override
    public String convert(LocalDateTime value) {
        return value.format(DATE_FORMATTER);
    }

}