package com.meishubao.jackson;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * 将LocalDateTime序列化为String数据类型<br>
 *
 * @author lilu
 */
public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN, Locale.CHINA);

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.format(DATE_FORMATTER));
    }

}