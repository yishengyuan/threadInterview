package com.shuosen.死锁;

import java.util.concurrent.TimeUnit;

/***
 * 死锁指的是两个或两个以上的进程在执行过程中，因争夺资源而造成的一种互相等待的现象
 * 若无外力
 */

class HoldLockThread implements Runnable {

    private String LockA;
    private String LockB;

    public HoldLockThread(String lockA, String lockB) {
        LockA = lockA;
        LockB = lockB;
    }

    @Override
    public void run() {
        synchronized (LockA) {
            System.out.println(Thread.currentThread().getName() + "\t 自己持有" + LockA + "尝试获得" + LockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (LockB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有" + LockB + "尝试获得" + LockB);
            }
        }
    }
}

public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA, lockB), "ThreadAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "ThreadBB").start();
        /***
         * linux  ps -ef|grep xx  ls -l
         * windows 下的java运行程序 也有类似于ps的查看进程的命令 ，但是目前我们需要查看的只是java
         * jps = java ps   jps -l
         *
         *
         */
    }
}
