package com.meishubao.openfeign.controller;

import com.meishubao.openfeign.model.Results;
import com.meishubao.openfeign.model.live.UserVO;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WGR
 * @create 2019/12/1 -- 20:01
 */
@Api(tags = "HELLO CONTROLLER 测试功能接口")
@RestController
public class HelloController {

    @ApiOperation(value = "Hello 测试接口", notes = "访问此接口，返回hello语句，测试接口", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名称", required = true, example = "blues1")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "接口返回成功状态"),
            @ApiResponse(code = 500, message = "接口返回未知错误，请联系开发人员调试")
    })
    @GetMapping("hello/{name}")
    public Results<UserVO> hello(@PathVariable String name) {
        UserVO userVO = new UserVO(name, "hello " + name);
        Results<UserVO> results = new Results<>(200, "SUCCESS", userVO);
        return results;
    }

}
