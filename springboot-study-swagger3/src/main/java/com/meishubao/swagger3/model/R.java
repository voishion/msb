package com.meishubao.swagger3.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "CommonResult", description = "通用返回对象")
public class R<T> {

    @Schema(required = true, description = "结果码", example = "200")
    private int state;
    @Schema(required = true, description = "时间戳", example = "1567425139000")
    private long time;
    @Schema(required = true, description = "返回信息", example = "SUCCESS")
    private String message;
    @Schema(required = true, description = "返回数据")
    private T content;

    public R(int code, String msg, T obj) {
        setState(code);
        setMessage(msg);
        setContent(obj);
        setTime(System.currentTimeMillis());
    }

    public static <T> R<T> success(T obj) {
        return new R(200, "处理成功", obj);
    }
}

