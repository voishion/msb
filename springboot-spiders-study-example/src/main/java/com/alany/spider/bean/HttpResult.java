package com.alany.spider.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HttpResult {
    private int code;

    private String content;

    private String msg;

    public HttpResult(int code, String content){
        this.code = code;
        this.content = content;
    }

}
