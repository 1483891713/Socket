package com.lingyu.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NioServer  {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        Selector selector = Selector.open();

        serverSocketChannel.bind(new InetSocketAddress(9999));

        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            //6.监控客户端
            if (selector.select() == 0){
                System.out.println("Server:没有客户端搭理我，我就干点别的事");
                continue;
            }

            //得到SelectionKey,判断通道里的事件
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()){
                SelectionKey key=keyIterator.next();
                if(key.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector,SelectionKey.OP_ACCEPT, ByteBuffer.allocate(1024));
                }

                if (key.isReadable()){
                    SocketChannel socketChannel =(SocketChannel)key.channel();
                    ByteBuffer buffer = (ByteBuffer)key.attachment();
                    System.out.println("客户端发来数据：" + new String(buffer.array()));

                }

                keyIterator.remove();
            }
        }

        }

}
