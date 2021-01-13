package com.wty.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import com.wty.netty.server.initializer.ServerInitializer;

/**
 * 客户端发送一个请求,服务器会返回hello netty
 *
 * @author wty
 * @date 2020/7/27 16:01
 */
public class HelloServer {
    public static void main(String[] args) throws InterruptedException {
        new HelloServer().start();
    }
    public void start() throws InterruptedException {
        // 定义主线程组,用于接收客户端的连接,但是不做任何处理,跟老板一样,不做事
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // 从线程组,老板线程组会把任务丢给这个线程组,让手下线程组做任务.
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        // 都是无限循环
        try {
            // netty服务器的创建, ServerBootstrap 是一个启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup) // 设置主从线程组
                    .channel(NioServerSocketChannel.class) // 设置nio的双向通道
                    .option(ChannelOption.SO_BACKLOG, 128)//设置线程队列等待连接的个数
                    .childOption(ChannelOption.SO_KEEPALIVE,true)//保持活动连接状态
                    .handler(null)//handler对应bossGroup处理，childHandler对应workerGroup处理
                    .childHandler(new ServerInitializer());  // 管道处理器：处理workerGroup的EventLoop
            // 启动server,并且设置8088为启动的端口号,同时启动方式为同步
            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();
            // 监听关闭的channel,设置位同步方法
            channelFuture.channel().closeFuture().sync();
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (channelFuture.isSuccess()){
                        System.out.println("监听端口成功");
                    }else {
                        System.out.println("监听端口失败");
                    }
                }
            });
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
