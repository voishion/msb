package com.meishubao.nettystudy.socket.client.handler;

import com.meishubao.nettystudy.socket.client.TcpClient;
import com.meishubao.nettystudy.socket.client.retry.RetryPolicy;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 重新连接处理器
 *
 * @author lilu
 */
@Slf4j
@ChannelHandler.Sharable
public class ReconnectHandler extends ChannelInboundHandlerAdapter {

    private int retries = 0;
    private RetryPolicy retryPolicy;

    private TcpClient tcpClient;

    public ReconnectHandler(TcpClient tcpClient) {
        this.tcpClient = tcpClient;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("成功建立到服务器的连接");
        retries = 0;
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if (retries == 0) {
            log.info("与服务器的TCP连接丢失");
            ctx.close();
        }

        boolean allowRetry = getRetryPolicy().allowRetry(retries);
        if (allowRetry) {
            long sleepTimeMs = getRetryPolicy().getSleepTimeMs(retries);
            log.info(String.format("尝试在%d毫秒后重新连接到服务器，重试次数：%d", sleepTimeMs, ++retries));
            final EventLoop eventLoop = ctx.channel().eventLoop();
            eventLoop.schedule(() -> {
                log.info("重新连接...");
                tcpClient.connect();
            }, sleepTimeMs, TimeUnit.MILLISECONDS);
        }

        ctx.fireChannelInactive();
    }

    private RetryPolicy getRetryPolicy() {
        if (this.retryPolicy == null) {
            this.retryPolicy = tcpClient.getRetryPolicy();
        }
        return this.retryPolicy;
    }

}