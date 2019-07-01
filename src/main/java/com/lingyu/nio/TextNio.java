package com.lingyu.nio;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TextNio {

    @Test
    public void test1() throws  Exception {

        FileOutputStream fis = new FileOutputStream("basic.txt");

        FileChannel channel = fis.getChannel();

        ByteBuffer bf = ByteBuffer.allocate(1024);

        String str = "hello.java";
        bf.put(str.getBytes());
        /**
         * 反转缓冲区
         */
        bf.flip();
        channel.write(bf);


    }

    @Test
    public void test2() throws  Exception {
        File file = new File("basic.txt");
        FileInputStream fis =  new FileInputStream(file);

        FileChannel fc = fis.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());

        fc.read(buffer);
        System.out.println(new String(buffer.array()));

        fis.close();
    }


    @Test
    public void test3 ()throws Exception{
        /**
         * 创建两个流
         */
        FileInputStream fis = new FileInputStream("basic.txt");
        FileOutputStream fos= new FileOutputStream("d:\\test\\basic.txt");

        /**
         * 得到两个通道
         */
        FileChannel sourceFC = fis.getChannel();
        FileChannel destFC = fos.getChannel();

        //复制
        destFC.transferFrom(sourceFC,0,sourceFC.size());

        //4. 关闭
        fis.close();
        fos.close();

    }
}
