package com.meishubao.nettystudy.socket.codec;

import com.meishubao.nettystudy.socket.bean.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 编码器
 *
 * @author lilu
 */
public class MessageEncoder extends MessageToByteEncoder<Message> {

    private final Charset charset = StandardCharsets.UTF_8;

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        byte[] data = msg.getBody().getBytes(charset);

        out.writeByte(msg.getMagicType());
        out.writeByte(msg.getType());
        out.writeLong(msg.getRequestId());
        out.writeInt(data.length);
        out.writeBytes(data);
    }

}
