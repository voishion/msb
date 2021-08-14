package com.alany.spider.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.HttpHost;

@Data
@NoArgsConstructor
public class HttpProxy {
    private String address;         //地址

    private int port;               //端口

    private String type = "http";   //类型

    private String provider;        //代理商

    private boolean isValid;


    public HttpProxy(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public HttpHost toHost() {
        return new HttpHost(address, port, "http");
    }
}
