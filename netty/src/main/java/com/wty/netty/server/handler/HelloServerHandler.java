package com.wty.netty.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 自定义Handler 需要继承netty,规定好的某个HandlerAdapter(规范)
 *
 * @author wty
 * @date 2020/12/18 21:18
 */
public class HelloServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 读取数据(读取客户端发送的消息)
     * ChannelHandlerContext:上下文对象,含有管道(pipeline),通道(channel),地址
     * Object :客户端发送的数据,默认Object
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server ctx = " + ctx);
        //将msg 转成ByteBuf
        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println("客户端发送的消息："+ byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址:" + ctx.channel().remoteAddress());
    }

    /**
     * 数据读取完毕
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将数据写入到缓存,并刷新(我们需要对发送的数据进行编码)
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello netty 客户端",CharsetUtil.UTF_8));
    }

    /**
     * 处理异常,一般是需要关闭通道的
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
