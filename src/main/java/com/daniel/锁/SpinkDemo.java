package com.daniel.锁;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/***
 *
 * 自旋锁
 */
public class SpinkDemo {

    //原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t   come in xixi");
        while (!atomicReference.compareAndSet(null, thread)) {
            //必须采用循环的方式使用自旋锁
        }
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t invoked myUnLock()");
    }

    public static void main(String[] args) {
        SpinkDemo spinkDemo = new SpinkDemo();
        new Thread(() -> {
            spinkDemo.myLock();
            //暂停一会儿线程
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinkDemo.myUnLock();
        }, "AA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            spinkDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinkDemo.myUnLock();
        }, "BB").start();

    }
}
