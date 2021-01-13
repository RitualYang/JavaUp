package com.wty.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import com.wty.netty.client.initializer.ClientInitializer;

/**
 * TODO
 *
 * @author wty
 * @date 2020/12/18 21:57
 */
public class HelloClient {
    public static void main(String[] args) throws InterruptedException {
        new HelloClient().start();
    }
    public void start() throws InterruptedException {
        // 从线程组,老板线程组会把任务丢给这个线程组,让手下线程组做任务.
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // netty服务器的创建, ServerBootstrap 是一个启动类
            Bootstrap serverBootstrap = new Bootstrap();
            serverBootstrap.group(workerGroup) // 设置处理线程组
                    .channel(NioSocketChannel.class) // 设置nio的双向通道
                    .handler(new ClientInitializer());  // 管道处理器：处理workerGroup的EventLoop
            // 启动server,并且设置8088为启动的端口号,同时启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.connect("127.0.0.1",8088).sync();
            // 监听关闭的channel,设置位同步方法
            channelFuture.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
