package com.meishubao.graphqlstudy.user.entity;

import com.meishubao.graphqlstudy.common.enums.Sex;
import lombok.Builder;
import lombok.Data;

/**
 * @author lilu
 */
@Data
@Builder
public class User {
    private String id;
    private Sex sex;
    private String nickname;
    private String mail;
    private String password;
    private String description;
    private String updateTime;
    private String createTime;
    private Car car;
    private Bike bike;
}
