package com.meishubao.nettystudy.socket.bean;

import lombok.Data;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author lilu
 */
@Data
public class Message {

    private final Charset charset = StandardCharsets.UTF_8;

    private byte magicType;
    /**
     * 消息类型
     * 0xAF 表示心跳包
     * 0xBF 表示超时包
     * 0xCF 业务信息包
     **/
    private byte type;
    /**
     * 请求id
     **/
    private long requestId;
    private int length;
    private String body;

//    public Message() {
//    }
//
//    public Message(byte magicType, byte type, long requestId, byte[] data) {
//        this.magicType = magicType;
//        this.type = type;
//        this.requestId = requestId;
//        this.length = data.length;
//        this.body = new String(data, charset);
//    }

    public Message(byte magicType, byte type, long requestId, String body) {
        this.magicType = magicType;
        this.type = type;
        this.requestId = requestId;
        this.length = body.getBytes(charset).length;
        this.body = body;
    }

}
