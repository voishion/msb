package com.meishubao.openfeign.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WGR
 * @create 2019/12/1 -- 20:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("通用接口返回对象")
public class Results<T> {

    @ApiModelProperty(required = true,notes = "结果码",example = "200")
    private int state;
    @ApiModelProperty(required = true,notes = "时间戳",example = "1567425139000")
    private long time;
    @ApiModelProperty(required = true,notes = "返回信息",example = "SUCCESS")
    private String message;
    @ApiModelProperty(required = true,notes = "返回数据",example = "{\"name\":\"blues\"}")
    private T content;

    public Results(int code, String msg, T obj){
        setState(code);
        setMessage(msg);
        setContent(obj);
        setTime(System.currentTimeMillis());
    }
}

