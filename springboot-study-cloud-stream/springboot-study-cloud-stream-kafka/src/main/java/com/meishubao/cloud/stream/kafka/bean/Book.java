package com.meishubao.cloud.stream.kafka.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author lilu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String name;
    private BigDecimal price;

}
