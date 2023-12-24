package com.daniel.锁;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T1 {
      volatile int n = 0 ;
      public void add(){
          n++;
      }

    public static void main(String[] args) {
        /***
         * 默认为不公平
         * This is equivalent to using {@code ReentrantLock(false)}.
         * public ReentrantLock() {
         *         sync = new NonfairSync();
         *     }
         */
        Lock lock = new ReentrantLock(true);



    }
}
