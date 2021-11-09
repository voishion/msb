package com.meishubao.swagger3.controller;

import com.meishubao.swagger3.model.R;
import com.meishubao.swagger3.model.dto.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "管理员相关接口", description = "AdminControllerApi")
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

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
    @PostMapping("/add/{name}")
    public R<User> addUser(@PathVariable String name) {
        return R.success(new User(name, 18, ""));
    }

    @Operation(summary = "管理员删除用户", description = "根据姓名删除用户")
    @ApiResponse(description = "返回添加的用户", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    @GetMapping("/del/{name}")
    public R<User> delUser(@Parameter(description = "姓名") @PathVariable String name) {
        log.info("管理员删除name={}的用户", name);
        return R.success(new User(name, 25, ""));
    }

    @Operation(summary = "管理员更新用户", description = "管理员根据姓名更新用户")
    @ApiResponse(description = "返回更新的用户", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
    @PostMapping("/update")
    public R<User> updateUser(@Parameter(schema = @Schema(implementation = User.class), required = true, description = "用户类") @RequestBody User user) {
        user.setAge(100);
        log.info("管理员更新{}用户的年龄为{}", user, 100);
        return R.success(user);
    }

}

