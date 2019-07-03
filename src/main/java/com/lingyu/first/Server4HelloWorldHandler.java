package com.lingyu.first;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import java.nio.ByteBuffer;

/**
 * @Sharable注解 -
 *  代表当前Handler是一个可以分享的处理器。也就意味着，服务器注册此Handler后，可以分享给多个客户端同时使用。
 *  如果不使用注解描述类型，则每次客户端请求时，必须为客户端重新创建一个新的Handler对象。
 *  如果handler是一个Sharable的，一定避免定义可写的实例变量。
 *  bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
@Override
protected void initChannel(SocketChannel ch) throws Exception {
ch.pipeline().addLast(new XxxHandler());
}
});
 */
@Sharable
public class Server4HelloWorldHandler extends  ChannelHandlerAdapter{

    @Override
    public void channelRead (ChannelHandlerContext ctx, Object msg) throws Exception{
        //获取读取的数量，是一个缓冲
        ByteBuf readBuffer = (ByteBuf) msg;

        //创建一个字节数组，用于保存缓存中的数据
        byte[] tempDatas = new byte[readBuffer.readableBytes()];

        readBuffer.readBytes(tempDatas);
        String message = new String(tempDatas,"UTF-8");
        System.out.println("from client : "+ message);
        if ("exit".equals(message)){
            ctx.close();
            return;
        }
        String line = "server message to client!";
        // 写操作自动释放缓存，避免内存溢出问题。

    }
}


