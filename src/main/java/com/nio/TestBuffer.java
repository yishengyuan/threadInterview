package com.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/***
 * 一：缓冲区（Buffer）在JavaNIO中负责数据存取  ，缓存区就是数组，用于存储不同数据类型的数据
 * 二：根据数据类型不同（boolean除外），提供了响应类型的缓冲区 ，
 * ByteBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * 上述缓冲区的管理方式几乎一致，通过allocate获取缓冲区
 * 二：缓冲区存取数据的两个核心方法
 * put():存入数据到缓冲区
 * get()  ：获取缓冲区中的数据
 *
 *
 *四：缓冲区中的四个核心属性
 * capacity:容量 表示缓冲区中最大存储数据的容量，一旦声明不能改变
 * limit：界限 ，表示缓冲区中可以操作数据的大小  limit后数据不能进行读写
 * position：位置 表示缓冲区中正在操作数据的位置
 *
 * position <= limit <= capacity
 *
 * mark:标记 表示记录当前position的位置，可以通过reset回复到mark的位置
 *
 *五：直接缓冲区和非直接缓冲区
 * 非直接缓冲区 通过allocate（）方法分配缓冲区 ，将缓冲区简历在JVM的内存中  heapbytebufferr
 * 直接缓冲区 通过allocateDirect（）分配缓冲区，将缓冲区简历在物理内存中 。可以提高效率  directByteBuffer
 *
 *System.gc 也不会立即回收
 * 不容易控制物理内存的数据
 *
 * java.nio.channels 包定义
 * IO过多，会造成CPU，所以CPU会占用过高
 * DMA总线  ：DMA总线太多也会影响性能  》》进化成了
 * 通道（Channel）的方式  通道是完全的独立处理器，专用于IO处理 附属于CPU CPU利用率高  更能提高效率
 */
public class TestBuffer {


    @Test
    public void test3(){
        ByteBuffer buff = ByteBuffer.allocate(1024);
        System.out.println(buff.isDirect());
    }

    @Test
    public void test2() {
        String str = "abcde";
        //1:配置一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(str.getBytes());
        buf.flip();
        byte[] dst = new byte[buf.limit()];
        buf.get(dst, 0, 2);
        System.out.println(new String(dst, 0, 2));
        System.out.println(buf.position());

        //mark（） 标记
        buf.mark();
        buf.get(dst,2,2);
        System.out.println(new String(dst, 2, 2));
        System.out.println(buf.position());

        //reset()  :恢复到mark的位置
        buf.reset();
        System.out.println(buf.position());
        //判断缓冲区中是否还有剩余数据
        if (buf.hasRemaining()) {
            System.out.println(buf.remaining());
        }

    }


    @Test
    public void test1() {

        String str = "abcde";
        //1:配置一个指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        System.out.println("allocate()");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        //2:利用put（）存入数据到缓冲区中
        buf.put(str.getBytes());

        System.out.println("put()");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());


        //3: 切换到读取数据模式
        buf.flip();
        System.out.println("flip()");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //4：利用get（）读取缓冲区的数据
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println(new String(dst, 0, dst.length));
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //5：rewind 可重复读取数据
        buf.rewind();
        System.out.println("rewind()");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //6：clear  清空缓冲区，但是缓冲区中的数据依然存在，但是处于被遗忘的状态
        buf.clear();
        System.out.println("clear()");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());


        System.out.println((char) buf.get());
    }

}
