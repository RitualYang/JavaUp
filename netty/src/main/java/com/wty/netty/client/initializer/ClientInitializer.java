package com.wty.netty.client.initializer;

import com.wty.netty.client.handler.HelloClientHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * 初始化器：当channel注册后,会执行里面的响应的初始化方法
 *
 * @author wty
 * @date 2020/7/27 16:17
 */
public class ClientInitializer extends ChannelInitializer<SocketChannel> {
    /**
     * 创建一个通道处理对象
     * @param socketChannel
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 通过SocketChannel去获得对应的管道
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 通过管道,添加handler

        // 添加自定义的助手类,返回“hello netty~”
        pipeline.addLast("helloClientHandler", new HelloClientHandler());
    }
}
