package com.meishubao.reflect;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author lilu
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Apple extends BaseEntity {

    private Long id;
    private String color;
    private BigDecimal price;
    private Integer size;

}
