package com.meishubao.nettystudy.socket.server;

import com.meishubao.nettystudy.socket.server.handler.ServerIdleStateTrigger;
import com.meishubao.nettystudy.socket.server.handler.SocketServerMessageHandler;
import com.meishubao.nettystudy.socket.codec.MessageDecoder;
import com.meishubao.nettystudy.socket.codec.MessageEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * 初始化Netty服务
 *
 * @author lilu
 */
@Slf4j
@Component
public class SocketServerRunner implements ApplicationRunner, ApplicationListener<ContextClosedEvent>, ApplicationContextAware {

    @Value("${netty.socket.enable:false}")
    private boolean enable;

    @Value("${netty.socket.port}")
    private int port;

    @Value("${netty.socket.ip}")
    private String ip;

    private ApplicationContext applicationContext;

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    private Channel serverChannel;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!enable) {
            return;
        }
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
        serverBootstrap.localAddress(new InetSocketAddress(this.ip, this.port));
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline pipeline = socketChannel.pipeline();

                pipeline.addLast(new IdleStateHandler(30, 0, 0));
                pipeline.addLast(new ServerIdleStateTrigger());

                pipeline.addLast(new MessageDecoder(1 << 20, 10, 4));
                pipeline.addLast(new MessageEncoder());
                /**
                 * 从IOC中获取到Handler
                 */
                pipeline.addLast("socketServerMessageHandler", applicationContext.getBean(SocketServerMessageHandler.class));
            }
        });
        Channel channel = serverBootstrap.bind().sync().channel();
        this.serverChannel = channel;
        log.info("Socket 服务启动，ip={},port={}", this.ip, this.port);
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        if (!enable) {
            return;
        }
        if (this.bossGroup != null) {
            this.bossGroup.shutdownGracefully();
        }
        if (this.workerGroup != null) {
            this.workerGroup.shutdownGracefully();
        }
        if (this.serverChannel != null) {
            this.serverChannel.close();
        }
        log.info("Socket 服务停止");
    }

}
