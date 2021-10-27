package com.meishubao.nettystudy.socket.client.handler;

import com.meishubao.nettystudy.socket.bean.Message;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 用于捕获{@link IdleState#WRITER_IDLE}事件（未在指定时间内向服务器发送数据），然后向<code>Server</code>端发送一个心跳包
 *
 * @author lilu
 */
public class ClientIdleStateTrigger extends ChannelInboundHandlerAdapter {

    public static final String HEART_BEAT = "heart beat!";

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            if (state == IdleState.WRITER_IDLE) {
                // write heartbeat to server
                ctx.channel().writeAndFlush(new Message((byte) 0XAF, (byte) 0XBF, System.currentTimeMillis(), HEART_BEAT));
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

}