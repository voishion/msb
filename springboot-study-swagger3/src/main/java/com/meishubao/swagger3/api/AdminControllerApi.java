package com.meishubao.swagger3.api;

import com.meishubao.swagger3.model.R;
import com.meishubao.swagger3.model.dto.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "管理员相关接口", description = "AdminControllerApi")
public interface AdminControllerApi {

    @Operation(summary = "管理员添加用户",
            description = "根据姓名添加用户并返回",
            parameters = {
                    @Parameter(name = "name", description = "姓名", example = "OBM")
            },
            responses = {
                    @ApiResponse(description = "返回添加的用户", content = @Content(mediaType = "application/json", schema = @Schema(anyOf = {R.class, User.class}))),
                    @ApiResponse(responseCode = "400", description = "返回400时候错误的原因")
            }
    )
    R<User> addUser(String name);

    @Operation(summary = "管理员删除用户", description = "根据姓名删除用户")
    @ApiResponse(description = "返回添加的用户", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    R<User> delUser(@Parameter(description = "姓名") String name);


    @Operation(summary = "管理员更新用户", description = "管理员根据姓名更新用户")
    @ApiResponse(description = "返回更新的用户", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    R<User> updateUser(@Parameter(schema = @Schema(implementation = User.class), required = true, description = "用户类") User user);
}


