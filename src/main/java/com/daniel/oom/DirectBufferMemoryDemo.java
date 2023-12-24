package com.daniel.oom;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/***
 * 写NIO程序使用ByteBuffer来读取或者写入数据，这是一种基于通道Channel与缓冲区Buffer的I/O方式，
 * 它可以使用Native函数库直接分配对外内存，然后通过一个存储在Java堆里面的DirectByteBuffer对象作为这块内存的引用进行操作。
 * 这样能在一些场景中显著提高性能，因为避免了Java堆和Native堆中来回复制数据。
 * ByteBuffer.allocate(capability) 第一种方式是分配JVM堆内存，属于GC管辖范围由于需要拷贝所以速度相对较慢
 * ByteBuffer.allocateDirect(capability) 第2中方式是分配OS本地内存，不属于GC管辖范围，由于不需要内存拷贝所以速度相对较快。
 * 但如果不断分配本地内存，堆内存很少使用，那么JVM就不需要执行GC，DirectByteBuffer对象们就不会被回收，
 * 这时候堆内存充足，但本地内存可能已经使用光了，再次尝试分配本地内存就会出现OutOfMemoryError ，那程序就直接崩溃了
 */
public class DirectBufferMemoryDemo {
    // -Xms10m -Xmx10m  -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m

    //Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
    public static void main(String[] args) {
        //JVM 能使用的直接内存就是物理内存的1/4
//        System.out.println("配置的maxDirectoryMemory"+ (sun.misc.VM.maxDirectMemory()/(double)1024/1024)+"M");
         try {   TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e ){  e.printStackTrace(); }
        ByteBuffer.allocateDirect(6*1024*1024);
    }
}
