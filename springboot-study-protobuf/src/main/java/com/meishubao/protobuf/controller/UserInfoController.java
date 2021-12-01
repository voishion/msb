package com.meishubao.protobuf.controller;

import cn.hutool.core.util.DesensitizedUtil;
import com.meishubao.protobuf.protocol.UserInfoCommunicationV0.UserInfoRequest;
import com.meishubao.protobuf.protocol.UserInfoCommunicationV0.UserInfoResponse;
import com.meishubao.protobuf.protocol.UserInfoCommunicationV0.UserInfoResponse.Builder;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lilu
 */
@Log4j2
@RestController
public class UserInfoController {

    @PostMapping(value="/getUserInfo", produces = "application/x-protobuf")
    public UserInfoResponse getUserInfo(@RequestBody UserInfoRequest request) {
        log.info("请求：{}" , request.toString());
        Builder builder = UserInfoResponse.newBuilder();
        builder.setMobile(DesensitizedUtil.mobilePhone("18500219629"));
        builder.setUserType(100);
        return builder.build();
    }

}
