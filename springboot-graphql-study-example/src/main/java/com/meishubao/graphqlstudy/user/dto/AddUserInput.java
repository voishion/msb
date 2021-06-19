package com.meishubao.graphqlstudy.user.dto;

import lombok.Data;

/**
 * @author lilu
 */
@Data
public class AddUserInput {
    private String nickname;
    private String mail;
    private String password;
    private String description;
}
