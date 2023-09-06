package com.meishubao.satoken.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.sign.SaSignUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * API接口参数签名示例
 * https://sa-token.cc/doc.html#/plugin/api-sign
 *
 * @author click33
 * @since 2022-10-13
 */
@RestController
@RequestMapping("/sign/")
public class SignController {

    //发起签名请求
	@SaIgnore
    @GetMapping("initSignRequest")
    public SaResult initSignRequest() {
        // 请求地址
        String url = "http://localhost:8081/sign/addMoney";

        // 请求参数
        Map<String, Object> paramMap = new LinkedHashMap<>();
        paramMap.put("userId", 10001);
        paramMap.put("money", 1000);
        // 更多参数，不限制数量...

        // 补全 timestamp、nonce、sign 参数，并序列化为 kv 字符串
        String paramStr = SaSignUtil.addSignParamsAndJoin(paramMap);

        // 将参数字符串拼接在请求地址后面
        url += "?" + paramStr;

        // 发送请求
        //String res = HttpUtil.get(url);

        // 根据返回值做后续处理
        //∂System.out.println("server 端返回信息：" + res);
        return SaResult.ok(url);
    }

    // 为指定用户添加指定余额
	@SaIgnore
    @RequestMapping("addMoney")
    public SaResult addMoney(long userId, long money) {

        // 1、校验请求中的签名
        SaSignUtil.checkRequest(SaHolder.getRequest());

        // 2、校验通过，处理业务
        System.out.println("userId=" + userId);
        System.out.println("money=" + money);

        // 3、返回
        return SaResult.ok();
    }

}
