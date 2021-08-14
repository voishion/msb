package com.alany.spider.bean;

import lombok.Data;

@Data
public class ExecuteContent {

    private long start;

    private long end;

    private String business;

    private String url;

    private String params;

    private HttpResult httpResult;

}
