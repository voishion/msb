package com.meishubao.nettystudy.socket.client.handler;

import com.meishubao.nettystudy.socket.bean.Message;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 消息接收处理器
 *
 * @author lilu
 */
@Slf4j
@ChannelHandler.Sharable
public class EchoHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) throws Exception {
        try {
            // 业务层处理数据
            log.info("收到服务端消息:{}", message.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("服务端连接，channel对应的短id为：{}，长id为：{}", ctx.channel().id().asShortText(), ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("服务端断开，channel对应的短id为：{}，长id为：{}", ctx.channel().id().asShortText(), ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        log.info("服务端断开，channel对应的短id为：{}，长id为：{}", ctx.channel().id().asShortText(), ctx.channel().id().asLongText(), cause);
    }

}