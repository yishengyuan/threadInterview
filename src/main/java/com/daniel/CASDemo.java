package com.daniel;

import java.util.concurrent.atomic.AtomicInteger;

/***
 * CAS是什么
 * 比较并交换   =》compareAndSet
 * 如果线程的期望值 和 物理内存的真实值一样 ，我就修改真实值
 * 如果期望值和真实值不一样，本次修改失败
 *
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2019)+"\t current Data"+ atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2014)+"\t current Data"+ atomicInteger.get());
        atomicInteger.getAndIncrement();
    }
}
