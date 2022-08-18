package com.meishubao.sample;

import cn.hutool.core.util.StrUtil;

/**
 * @author lilu
 */
public class Main {

    public static void main(String[] args) {
        String str = StrUtil.format("WW{}SS", "李露");
        System.out.println(str);
    }

}
