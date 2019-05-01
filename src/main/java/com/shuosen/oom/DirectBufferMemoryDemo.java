package com.shuosen.oom;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

public class DirectBufferMemoryDemo {
    // -Xms10m -Xmx10m  -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m

    //Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
    public static void main(String[] args) {
        System.out.println("配置的maxDirectoryMemory"+ (sun.misc.VM.maxDirectMemory()/(double)1024/1024)+"M");
         try {   TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e ){  e.printStackTrace(); }
        ByteBuffer.allocateDirect(6*1024*1024);
    }
}
