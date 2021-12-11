package com.meishubao.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lilu
 */
@Data
@AllArgsConstructor
public class RtmClientVO implements Serializable {

    private String userId;
    private String clientId;

}
