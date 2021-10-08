package com.alany.spider.common;

public enum BizEnum {
    tabobao("taobao");

    private String name;
    BizEnum(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
