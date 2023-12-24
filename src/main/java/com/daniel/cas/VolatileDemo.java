package com.daniel.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyData {  //MyData.java MyData.class  === JVM字节码
    volatile int number = 0; //volatile 增加属性的可见性

    public void addT060() {
        this.number = 60;
    }

    public void addPlusPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic() {
        //获取后自增
        atomicInteger.getAndIncrement();
    }

    public void mysort(){
        int x = 11 ;
        int y = 12 ;
        x = x +5 ;
        y = x*x  ;
    }
    //正常 1234
    //指令重排后可能是2134
    //1324


}

/***
 * 验证volatile的可见性
 * 1:假如 int number = 0 number 变量之前根本没有增加volatile关键字修饰 没有可见性
 * 1.2 添加了 volatile ，可以解决可见性问题
 * 2.验证volatile 不保证原子性  不可分割 完整性  也即某个线程正在做某个具体业务，中间不可被加塞或者被分割
 * 需要整体完整 要么同时成功 要么同时失败
 *
 * 2.4 如果解决原子性
 *
 *
 */
public class VolatileDemo {

    public static void main(String[] args) {

//        SeeOkByVolatile();
        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }
        //需要等待上面20个线程全部计算完成后，再用main线程取得最终的结果值
//        try {   TimeUnit.SECONDS.sleep(5); }catch (InterruptedException e ){  e.printStackTrace(); }
        //如果当前运行的数量大于2    2默认为 main线程和gc线程
        while (Thread.activeCount() > 2) {
            //等待其他的线程完成
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "\t int finally number value:  " + myData.number);
        System.out.println(Thread.currentThread().getName() + "\t AtomicInteger finally number value:  " + myData.atomicInteger);
        //执行后结果  第一次  main	 finally number value:  16627  第二次  main	 finally number value:  18322
        //无法保证原子性

    }


    //volatile可以保证可见性 及时通知其他线程  主物理内存的值 已经被修改
    private static void SeeOkByVolatile() {
        MyData myData = new MyData();
        //第一个线程
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
                myData.addT060();
                System.out.println(Thread.currentThread().getName() + "\t update number value" + myData.number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();
        //第二个线程就是我们的main线程
        while (myData.number == 0) {

        }
        //main线程就在这里一直等待 直到number 不等于0
        System.out.println(Thread.currentThread().getName() + "\t mission is over  ");
    }
}
