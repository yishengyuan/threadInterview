package com.daniel.多线程;

import java.util.concurrent.*;

class MyThread implements  Runnable{

    @Override
    public void run() {

    }
}
class MyThread2 implements  Callable<Integer>{


    @Override
    public Integer call() throws Exception {
        System.out.println("come in Callable");
         try {   TimeUnit.SECONDS.sleep(3); }catch (InterruptedException e ){  e.printStackTrace(); }
        return 1024;
    }
}
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //两个线程，一个main线程，一个是AA futuretask
//        Thread t1 = new Thread();
//        t1.start();

        RunnableFuture<Integer> futureTask1 = new FutureTask<>(new MyThread2());
        RunnableFuture<Integer> futureTask2 = new FutureTask<>(new MyThread2());
        new Thread(futureTask1,"AA").start();
        new Thread(futureTask2,"BB").start();


        System.out.println(Thread.currentThread().getName()+"\t 当前线程");
        int result2 = 100 ;


        while(!futureTask1.isDone()){
            //自旋锁  直到结束
        }

        Integer result = futureTask1.get();
        System.out.println("*************result="+ result); //建议放在最后

        System.out.println(result2+result);



    }
}
