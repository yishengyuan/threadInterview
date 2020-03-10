package com.shuosen.辅助类;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

      CountDownLatch  countDownLatch  = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t国，被灭");
                countDownLatch.countDown();

            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t ********秦灭帝国 一统华夏");
    }

    private static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i <= 6; i++) {
            new Thread(()-> {
                System.out.println(Thread.currentThread().getName() + "\t 上完自习，离开教室");
                countDownLatch.countDown();

            },String.valueOf(i)).start();
        }
        countDownLatch.await();  //main 线程必须等待countdownlatch所在的线程执行完成
        System.out.println(Thread.currentThread().getName()+"\t ********ss班长最后关门走人");
    }
}
