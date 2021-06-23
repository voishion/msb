package com.meishubao.nettystudy.socket.codec;

import com.meishubao.nettystudy.socket.bean.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

/**
 * 解码器
 *
 * @author lilu
 */
@Slf4j
public class MessageDecoder extends LengthFieldBasedFrameDecoder {

    /** 头部信息的大小应该是 byte+byte+int = 1+1+8+4 = 14 **/
    private static final int HEADER_SIZE = 14;

    public MessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in == null) {
            return null;
        }
        if (in.readableBytes() <= HEADER_SIZE) {
            return null;
        }

        in.markReaderIndex();
        // 1
        byte magic = in.readByte();
        // 1
        byte type = in.readByte();
        // 8
        long requestId = in.readLong();
        // 4
        int dataLength = in.readInt();
        // FIXME 如果dataLength过大，可能导致问题
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return null;
        }

        byte[] data = new byte[dataLength];
        in.readBytes(data);

        return new Message(magic, type, requestId, new String(data, "UTF-8"));
    }

}
