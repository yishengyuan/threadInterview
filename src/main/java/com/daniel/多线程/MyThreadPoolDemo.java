package com.daniel.多线程;

import java.util.concurrent.*;

/***
 * 第四种使用java多线程的方式 线程池
 *
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {

        //查看电脑的核心数
        System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );
        try {
            for (int i = 1; i <=10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void threadInit() {
        //查看电脑的核心数
        System.out.println(Runtime.getRuntime().availableProcessors());
        //Array Arrays
        //Collection 接口  Collections辅助该工具类
        //Executor 接口 Executors 工具类
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);  //一池有五个线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor(); //一池有一个线程
        ExecutorService threadPool = Executors.newCachedThreadPool(); //一池n个线程
        //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
        try {
            for (int i = 0; i < 30; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                     try {   TimeUnit.MILLISECONDS.sleep(200); }catch (InterruptedException e ){  e.printStackTrace(); }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
