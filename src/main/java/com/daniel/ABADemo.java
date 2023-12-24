package com.daniel;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/***
 * ABA问题 的解决方案
 * 时间错原子引用
 */
public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static  AtomicStampedReference<Integer> atomicStampedReference   = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {

        System.out.println("==============以下是ABA问题的产生 =====================");
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(() -> {
            //暂停1秒钟t2线程，保证上面的t1线程完成了一次ABA操作
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicReference.compareAndSet(100, 2019) + "\t" + atomicReference.get());
        }, "t2").start();

        System.out.println("==============以下是ABA问题的解决 =====================");

        try {   TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e ){  e.printStackTrace(); }
        new Thread(() -> {
            //暂停1秒钟t2线程，保证上面的t1线程完成了一次ABA操作
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第1次版本号 "+stamp);

            try {   TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e ){  e.printStackTrace(); }
            atomicStampedReference.compareAndSet(  100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t 第2次版本号" +atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(  101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t 第3次版本号" +atomicStampedReference.getStamp());
        }, "t3").start();


        new Thread(() -> {
            //暂停1秒钟t2线程，保证上面的t1线程完成了一次ABA操作
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t第1次版本号 "+stamp);

            try {   TimeUnit.SECONDS.sleep(3); }catch (InterruptedException e ){  e.printStackTrace(); }

            boolean result = atomicStampedReference.compareAndSet(100,2019,stamp,stamp+1);
            System.out.println(Thread.currentThread().getName()+"\t修改成功否"+result+"\t"+"当前实际版本号"+atomicStampedReference.getStamp());

            System.out.println(Thread.currentThread().getName()+"\t 当前实际最新值"+atomicStampedReference.getReference());

        }, "t4").start();


    }


}
