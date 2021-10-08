package com.meishubao.nettystudy.socket.client;

import com.meishubao.nettystudy.socket.bean.Message;
import com.meishubao.nettystudy.socket.client.handler.ClientHandlersInitializer;
import com.meishubao.nettystudy.socket.client.retry.ExponentialBackOffRetry;
import com.meishubao.nettystudy.socket.client.retry.RetryPolicy;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * Socket客户端
 *
 * @author lilu
 */
@Slf4j
public class TcpClient {

    private String host;
    private int port;
    private Bootstrap bootstrap;
    /** 重连策略 */
    private RetryPolicy retryPolicy;
    /** 将<code>Channel</code>保存起来, 可用于在其他非handler的地方发送数据 */
    private Channel channel;

    public TcpClient(String host, int port) {
        this(host, port, new ExponentialBackOffRetry(1000, Integer.MAX_VALUE, 60 * 1000));
    }

    public TcpClient(String host, int port, RetryPolicy retryPolicy) {
        this.host = host;
        this.port = port;
        this.retryPolicy = retryPolicy;
        init();
    }

    public RetryPolicy getRetryPolicy() {
        return retryPolicy;
    }

    private void init() {
        EventLoopGroup group = new NioEventLoopGroup();
        // bootstrap 可重用, 只需在TcpClient实例化的时候初始化即可.
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ClientHandlersInitializer(TcpClient.this));
    }

    /**
     * 向远程TCP服务器请求连接
     */
    public void connect() {
        synchronized (bootstrap) {
            try {
                ChannelFuture future = bootstrap.connect(host, port).sync();
                future.addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        if (!future.isSuccess()) {
                            future.channel().pipeline().fireChannelInactive();
                        }
                    }
                });
                future.awaitUninterruptibly(2000, TimeUnit.MILLISECONDS);
                if (future.channel().isActive()) {
                    this.channel = future.channel();
                }
            } catch (InterruptedException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TcpClient tcpClient = new TcpClient("localhost", 1989);
        tcpClient.connect();
        Channel channel = tcpClient.channel;
        for (long i = 0; i < 100; i++) {
            String body = "Hello world from client:" + i;
            Message msg = new Message((byte) 0XAF, (byte) 0XCF, i, body);
            channel.writeAndFlush(msg);
            TimeUnit.SECONDS.sleep(5);
        }
    }

}