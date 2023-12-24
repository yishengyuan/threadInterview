package com.daniel.阻塞队列;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 *
 * 2.0版本的 生产者消费者
 */
class ShareData {  //相当于资源类
    private int number = 0;
    private Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            //1:判断
            while (number != 0) {
                //等待不能生产
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知唤醒
            condition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void decrement() throws Exception {
        lock.lock();
        try {
            //1:判断 多线程判断要用while
            while (number == 0) {
                //等待不能生产
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //通知唤醒
            condition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}

/***
 * 题目：一个初始值为零的变量  两个线程对其交替操作 ，一个加1一个减一来五轮
 * 线程 操作（）  资源类
 * 判断 干活 通知  高内聚 低耦合
 * 防止虚假唤醒机制
 * wait是object类的 跟线程没关系
*用synchronized 和lock的区别？
 * 1：synchronized属于jvm层面，是java关键字
 * monitorenter：底层是通过monitor对象来完成的 其实wait和notify都是依赖于monitor的对象 ，
 * 只是在同步代码块中才能调用wait和notify
 *
 * lock是java.util.concurrent 类java5之后 api类
 *
 *
 */
public class ProConsumer_TraditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();




    }
}
