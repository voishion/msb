package com.meishubao.nettystudy.socket.client.handler;

import com.meishubao.nettystudy.socket.bean.Message;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ScheduledFuture;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 客户端连接到服务器端后，会循环执行一个任务：随机等待几秒，然后ping一下Server端，即发送一个心跳包。
 *
 * @author lilu
 */
@Slf4j
public class HeartbeatHandler extends ChannelInboundHandlerAdapter {

    private Random random = new Random();
    private int baseRandom = 25;

    private Channel channel;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.channel = ctx.channel();

        ping(ctx.channel());
    }

    private void ping(Channel channel) {
        int second = Math.max(20, random.nextInt(baseRandom));
        // 为了让服务端主动断开连接，设置值大于服务端断开心跳时间间隔
        // if (second == 5) { second = 6; }
        log.info("下一次心跳将在{}秒之后发送", second);
        ScheduledFuture<?> future = channel.eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                if (channel.isActive()) {
                    log.info("向服务器发送心跳...");
                    channel.writeAndFlush(new Message((byte) 0XAF, (byte) 0XBF, System.currentTimeMillis(), ClientIdleStateTrigger.HEART_BEAT));
                } else {
                    log.info("连接已断开，取消将发送心跳的任务");
                    channel.closeFuture();
                    throw new RuntimeException();
                }
            }
        }, second, TimeUnit.SECONDS);

        future.addListener(new GenericFutureListener() {
            @Override
            public void operationComplete(Future future) throws Exception {
                if (future.isSuccess()) {
                    ping(channel);
                }
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        log.error(cause.getMessage(), cause);
    }

}