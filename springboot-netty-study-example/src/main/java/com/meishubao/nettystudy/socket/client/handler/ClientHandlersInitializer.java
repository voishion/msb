package com.meishubao.nettystudy.socket.client.handler;

import com.meishubao.nettystudy.socket.client.TcpClient;
import com.meishubao.nettystudy.socket.codec.MessageDecoder;
import com.meishubao.nettystudy.socket.codec.MessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.springframework.util.Assert;

/**
 * 客户端处理程序初始化程序
 *
 * @author lilu
 */
public class ClientHandlersInitializer extends ChannelInitializer<SocketChannel> {

    private ReconnectHandler reconnectHandler;
    private EchoHandler echoHandler;

    public ClientHandlersInitializer(TcpClient tcpClient) {
        Assert.notNull(tcpClient, "TcpClient can not be null.");
        this.reconnectHandler = new ReconnectHandler(tcpClient);
        this.echoHandler = new EchoHandler();
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 心跳发送
//        pipeline.addLast(new IdleStateHandler(0, 5, 0));
//        pipeline.addLast(new ClientIdleStateTrigger());
        // 自定义心跳发送
        pipeline.addLast(new HeartbeatHandler());
        pipeline.addLast(this.reconnectHandler);
        pipeline.addLast(new MessageDecoder(1 << 20, 10, 4));
        pipeline.addLast(new MessageEncoder());
        pipeline.addLast(this.echoHandler);
    }

}