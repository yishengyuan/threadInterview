package com.daniel.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;

public class TestNonBlockingNIO {
    //客户端
    @Test
    public void client() throws IOException {
        //1：获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        //2.切换到非阻塞模式
        sChannel.configureBlocking(false);
        //3.分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //4.发送数据给服务器
        buf.put(new Date().toString().getBytes());
        buf.flip();
        sChannel.write(buf);
        buf.clear();
        //5/关闭通道
        sChannel.close();
    }

    //服务端
    @Test
    public void server() throws IOException {
        //1:获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        //2:切换到非阻塞模式
        ssChannel.configureBlocking(false);

        //3：绑定连接
        ssChannel.bind(new InetSocketAddress(9898));

        //4.获取选择器
        Selector selector = Selector.open();

        //5.将通道注册到选择器上  ,并且指定监听事件
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        //6.轮询的获取选择器上已经准备就绪的事件
        while (selector.select() > 0) {
            //7：获取当前选择器中注册的选择键   已就绪的监听事件
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();


            while(it.hasNext()){
                //8：获取准备就绪的事件
                SelectionKey sk = it.next();
                //9：判断具体是什么事件准备就绪
                if(sk.isAcceptable()){

                }
            }

        }


    }
}
