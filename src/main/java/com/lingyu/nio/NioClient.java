package com.lingyu.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {

    public static void main(String[] args)  throws  Exception{
        //1. 得到一个网络通道
        SocketChannel channel = SocketChannel.open();

        //2. 设置非阻塞方式
        channel.configureBlocking(false);

        //3.提供服务器端的Ip地址和端口号
        InetSocketAddress address = new InetSocketAddress("127.0.0.1",9999);

        //4. 连接服务端

        if (!channel.connect(address)){
            while (!channel.finishConnect()){
                System.out.println("Client:连接服务器端的同时，我还可以干点别的一些事情");
            }
        }

        //5.得到一个缓冲区并存入数据
        String msg = "hello ,server";

        /**
         * 通过缓存区类，将客户端的数据保存到缓存中
         */
        ByteBuffer  writeBuf = ByteBuffer.wrap(msg.getBytes());

        //使用通道方法write发送到服务器端
        channel.write(writeBuf);

        System.in.read();

    }
}
