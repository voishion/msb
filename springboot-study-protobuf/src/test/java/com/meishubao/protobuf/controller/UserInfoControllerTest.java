package com.meishubao.protobuf.controller;

import cn.hutool.http.Header;
import cn.hutool.http.HttpUtil;
import com.google.protobuf.InvalidProtocolBufferException;
import com.meishubao.protobuf.protocol.UserInfoCommunicationV0.UserInfoRequest;
import com.meishubao.protobuf.protocol.UserInfoCommunicationV0.UserInfoResponse;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

@Log4j2
class UserInfoControllerTest {

    @Test
    void getUserInfo() {
        try {
            UserInfoRequest.Builder builder = UserInfoRequest.newBuilder();
            builder.setUserId(178624L);

            byte[] body = HttpUtil.createPost("http://127.0.0.1:8080/getUserInfo")
                    .body(builder.build().toByteArray())
                    .header(Header.CONTENT_TYPE, "application/x-protobuf")
                    .execute().bodyBytes();
            UserInfoResponse response = UserInfoResponse.parseFrom(body);
            log.info("result, mobile:{}, userType:{}", response.getMobile(), response.getUserType());
        } catch (InvalidProtocolBufferException e) {
            log.error("error:", e);
        }
    }

}