package com.shuosen.锁;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 *
 * 多个线程读取一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行
 * 但是，如果有一个线程去写共享资源，就不应该再有其它线程可以对该资源进行读或写
 * 小总结：
 * 读读能共存
 * 读写不能共存
 * 写写不能共存
 *
 * 写操作，原子+独占，整个过程必须是一个完整的统一体，中间不许被分割 被打断
 */
class MyCache{
    private volatile Map<String,Object> map  = new HashMap<>();
    private Lock lock = new ReentrantLock ();

    public void  put(String key ,Object value){
        System.out.println(Thread.currentThread().getName()+"\t 正在写入 "+key);
         try {   TimeUnit.SECONDS.sleep(300); }catch (InterruptedException e ){  e.printStackTrace(); }
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"\t 正在写入完成");
    }


    public void  get(String key ){
        System.out.println(Thread.currentThread().getName()+"\t 正在读取 "+key);
        try {   TimeUnit.SECONDS.sleep(300); }catch (InterruptedException e ){  e.printStackTrace(); }
        Object result = map.get(key);
        System.out.println(Thread.currentThread().getName()+"\t 读取完成"+result    );
    }

}
public class ReadWriteLockDemo {
    public static void main(String[] args) {
            MyCache  myCache  = new MyCache();
           for (int i = 0; i < 5; i++) {
               final int tempInt = i ;
               new Thread(()->{
                    myCache.put(tempInt+"",tempInt+"");
               },String.valueOf(i)).start();
           }

        for (int i = 0; i < 5; i++) {
            final int tempInt = i ;
            new Thread(()->{
                myCache.get(tempInt+"");
            },String.valueOf(i)).start();
        }
    }
}
