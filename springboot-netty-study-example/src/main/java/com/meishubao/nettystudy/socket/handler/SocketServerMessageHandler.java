package com.meishubao.nettystudy.socket.handler;

import com.meishubao.nettystudy.service.DiscardService;
import com.meishubao.nettystudy.socket.bean.Message;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lilu
 */
@Slf4j
@Sharable
@Component
public class SocketServerMessageHandler extends SimpleChannelInboundHandler<Message> {

    /** 用于记录和管理所有客户端的channel **/
    private static ChannelGroup clients= new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Autowired
    DiscardService discardService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        // 业务层处理数据
        this.discardService.discard(msg);
        // 响应客户端
        Message resp = new Message(msg.getMagicType(), msg.getType(), msg.getRequestId(), String.format("我在%s收到了你的消息：%s", System.currentTimeMillis(), msg.getBody()));
        ctx.channel().writeAndFlush(resp);
    }

    /**
     * 当客户端连接服务器端之后（打开连接）获取客户端的channel，并放到ChannelGroup中去进行管理
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
        log.info("客户端连接，channel对应的短id为：{}，长id为：{}，clients size：{}",
                ctx.channel().id().asShortText(), ctx.channel().id().asLongText(), clients.size());
    }

    /**
     * ChannelGroup会自动移除客户端的channel
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 当触发handlerRemoved,ChannelGroup会自动移除客户端的channel
        super.handlerRemoved(ctx);
        log.info("客户端断开，channel对应的短id为：{}，长id为：{}，clients size：{}",
                ctx.channel().id().asShortText(), ctx.channel().id().asLongText(), clients.size());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        log.error(cause.getMessage(), cause);
    }

}
