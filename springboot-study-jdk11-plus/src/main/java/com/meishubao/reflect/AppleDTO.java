package com.meishubao.reflect;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author lilu
 */
@Data
@Accessors(chain = true)
public class AppleDTO implements Serializable {

    private Long id;
    private String color;
    private BigDecimal price;
    private Integer size;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;

}
