package com.shuosen.reference;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {
    public static void softRef_Memory_Enough(){
        Object o1  = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null ;
        System.gc();

        System.out.println(o1);
        System.out.println(softReference.get());
    }

    /***
     * JVM配置 故意产生大对象并配置小的内存，让它内存不够用了导致OOM 看软引用的回收情况
     * -Xms5m  -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRef_Memory_NotEnough(){
        Object o1  = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(softReference.get());
        o1 = null ;

        try{
            byte[] bytes = new byte[30*1024*1024];
            //OOM自动GC
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            System.out.println(o1);
            System.out.println(softReference.get());
        }
    }

    public static void main(String[] args) {
//        softRef_Memory_Enough();
        softRef_Memory_NotEnough();
    }
}
