package com.meishubao.graphqlstudy.common.response;

import lombok.Builder;
import lombok.Data;

/**
 * @author lilu
 */
@Data
@Builder
public class Result {
    private Integer code;
    private String msg;
}
