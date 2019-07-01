package com.lingyu.basic;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

//服务端的业务逻辑处理类
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    //读取数据事件
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        System.out.println("Server" + ctx);

        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端发来的消息：" + buf.toString(CharsetUtil.UTF_8));
    }

    //数据读取完毕事件
    public void channelReadComplete(ChannelHandlerContext ctx){
    ctx.writeAndFlush(Unpooled. copiedBuffer("就是没有钱",CharsetUtil.UTF_8));
    }

    //异常关闭事件
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        ctx.close();
    }
}
