package com.shuosen.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ReferenceQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        Object  obj1  =new Object();
        ReferenceQueue<Object> referenceQueue  = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(obj1);
        System.out.println(obj1);//java.lang.Object@4554617c
        System.out.println(weakReference.get());//java.lang.Object@4554617c
        System.out.println(referenceQueue.poll());//null

        System.out.println("========================");
        obj1 = null ;
        System.gc();
        Thread.sleep(500);
        System.out.println(obj1);//null
        System.out.println(weakReference.get());//null
        System.out.println(referenceQueue.poll());//null


    }
}
