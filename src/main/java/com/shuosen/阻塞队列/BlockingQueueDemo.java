package com.shuosen.阻塞队列;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/***
 *
 * 1：队列  排队打饭 排队买电影票 先到先得
 * 2：阻塞队列
 *    2.1 阻塞对垒有没有好的一面
 *    2.2 不得不阻塞 ，你如何管理  比如海底捞排队的客户 医院排队
 *    2.3
 *    面试要录音
 *    CopyOnwriteArrayList：
 *    Queue
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
//        throwException();
//        blockingQueue2();
//        blockingQueue3();


    }

    private static void blockingQueue3() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println("==============");
        //如果队列满，就会阻塞等待
        System.out.println("x");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        //如果没有数据可取，会阻塞等待
        System.out.println(blockingQueue.take());
    }

    private static void blockingQueue2() {
        //放不进去返回false
        //取不出来返回null
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("x"));

        System.out.println(blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
    }

    //抛出异常版本
    private static void throwException() {

        //一言不合抛出异常
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        //检查队首是哪个值
        System.out.println(blockingQueue.element());

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
    }
}
