package com.shuosen;

/***
 *  多线程下的单例模式
 */
public class SingletonDemo {

    private  static volatile SingletonDemo instance = null ;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法SingletonDemo");
    }

    //synchronized 高并发  会效率降低 太重了 并发性下降
    //DCL模式（Double Check Lock 双端检索机制 ） 加锁前和后进行了检查  99.999999% 但是有可能会有指令重排
    public static    SingletonDemo getInstance(){
        if(instance==null){
            synchronized (SingletonDemo.class){
                if(instance==null){
                    instance = new SingletonDemo() ; //指令重排有可能会先执行这一行
                }
            }
        }
        return  instance;
    }

    public static void main(String[] args) {
        //单线程 main线程的操作动作
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        //并发多线程后出现的问题
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                    SingletonDemo.getInstance();
            },String.valueOf(i)).start();
        }
    }
}
