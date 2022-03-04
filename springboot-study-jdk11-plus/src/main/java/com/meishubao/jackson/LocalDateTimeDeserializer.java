package com.meishubao.jackson;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 将String数据类型反序列化为LocalDateTime<br>
 *
 * @author lilu
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DatePattern.UTC_MS_PATTERN);

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext context) throws IOException {
        return LocalDateTime.parse(p.getValueAsString(), LocalDateTimeSerializer.DATE_FORMATTER);
    }

}