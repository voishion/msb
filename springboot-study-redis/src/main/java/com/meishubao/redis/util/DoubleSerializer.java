package com.meishubao.redis.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

public class DoubleSerializer extends JsonSerializer<Double> {

    @Override
    public void serialize(Double value, JsonGenerator generator,
                          SerializerProvider provider) throws IOException {
        String realString = new BigDecimal(value).toPlainString();
        generator.writeString(realString);
    }

}