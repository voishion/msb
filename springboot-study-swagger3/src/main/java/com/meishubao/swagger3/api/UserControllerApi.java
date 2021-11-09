package com.meishubao.swagger3.api;

import com.meishubao.swagger3.model.dto.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "用户的增删改查", description = "UserControllerApi")
public interface UserControllerApi {

    @Operation(summary = "添加用户",
            description = "根据姓名添加用户并返回",
            parameters = {
                    @Parameter(name = "name", description = "姓名")
            },
            responses = {
                    @ApiResponse(description = "返回添加的用户",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "返回400时候错误的原因")}
    )
    User addUser(String name);

    @Operation(summary = "删除用户",
            description = "根据姓名删除用户",
            parameters = {
                    @Parameter(name = "name", description = "姓名")
            })
    void delUser(String name);
}

