package com.meishubao.swagger3.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "User", description = "用户信息")
public class User {

    @Schema(required = true, description = "用户名", example = "blues")
    private String name;

    @Schema(required = true, description = "年龄", example = "18")
    private Integer age;

    @Schema(required = true, description = "用户返回消息", example = "hello world")
    private String words;
}