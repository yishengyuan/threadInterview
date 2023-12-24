package com.daniel.阻塞队列;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/***
 * volatile /CAS /atomicInteger /BlockQueue /线程交互 /原子引用
 */
class MyResource {
    private volatile boolean FLAG = true;//默认开启
    private AtomicInteger atomicInteger = new AtomicInteger();
    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception {
        String data = null;
        boolean retValue;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列" + data + "失败");
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "\t 大老板叫停了 ");
    }

    public void myConsumer() throws InterruptedException {
        String result = null;
        while (FLAG) {
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t 超过2秒钟没有取到蛋糕 消费退出");
            }
            System.out.println(Thread.currentThread().getName() + "\t 消费队列" + result + "成功");
        }

    }

    public void stop() throws Exception{
        this.FLAG = false ;
    }
}

public class ProConsumer_BlockingQueue {
    public static void main(String[] args) throws Exception {
        MyResource resource = new MyResource(new LinkedBlockingDeque(3));
        new Thread(() -> {
            try {
                resource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t 生产线程启动");
        }, "Prod").start();

        new Thread(() -> {
            try {
                resource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t 消费线程启动");
        }, "Consumer").start();
        
         try {   TimeUnit.SECONDS.sleep(5); }catch (InterruptedException e ){  e.printStackTrace(); }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("5秒钟时间到，大老板叫停 表示FLAG=false 生产动作结束");
        resource.stop();

    }


}
