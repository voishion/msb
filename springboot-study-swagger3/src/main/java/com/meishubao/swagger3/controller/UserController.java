package com.meishubao.swagger3.controller;

import com.meishubao.swagger3.model.dto.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户的增删改查", description = "UserControllerApi")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

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
    @PostMapping("/add/{name}")
    public User addUser(@PathVariable String name) {
        return new User(name, 18, "");
    }

    @Operation(summary = "删除用户",
            description = "根据姓名删除用户",
            parameters = {
                    @Parameter(name = "name", description = "姓名")
            }
    )
    @GetMapping("/del/{name}")
    public void delUser(@PathVariable String name) {
        log.info("删除name={}的用户", name);
    }

}

