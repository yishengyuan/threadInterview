package com.shuosen.锁;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/***
 * 多个线程抢多个资源
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {//模拟6个汽车而
              new Thread(()->{
                  try {
                      semaphore.acquire();
                      System.out.println(Thread.currentThread().getName()+"\t抢到车位");
                       try {   TimeUnit.SECONDS.sleep(3); }catch (InterruptedException e ){  e.printStackTrace(); }

                      System.out.println(Thread.currentThread().getName()+"\t 停车3秒后离开车位");
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }finally {
                      semaphore.release();
                  }
              },String.valueOf(i)).start();

        }
    }
}
