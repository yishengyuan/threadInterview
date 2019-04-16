package com.shuosen;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/***
 * 集合类不安全的问题
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {
        //set不安全
//        setNotSafe();
        //list不安全
//        listNotSafe();
//        mapNotSafe();
    }

    private static void mapNotSafe() {
        Map<String, String> map = new ConcurrentHashMap<>(); //Collections.sychronized(new HashMap<>())
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
        //线程不安全的  底层是hashmap   map = new HashMap<>();
        //default initial capacity (16) and load factor (0.75). 初始值是16  负载因子是0.75
        //你确定hashset的底层是hashmap么？
        //        Set<String>  set = new HashSet<>();

        /***
         *  public boolean add(E e) {
         *         return map.put(e, PRESENT)==null;
         *     }
         *   present:是个常量
         * private static final Object PRESENT = new Object();
         */
        new HashSet<>().add("a");


//        Set<String> set = Collections.synchronizedSet(new HashSet<>());

        //继承了抽象的AbstractSet  底层也是CopyOnWriteArrayList
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
        //第一问：new ArrayList 底层new的是什么 ？  答案：数组
        //是什么类型的数组？ new一个空的list 伴随的初始值是ten Object的数组
        //线程是安全还是不安全的？ 不安全
        //给我举例一个不安全的case 为了保证高并发下的执行效率 在add方法上没有加sychronized
//        List<String> list = new ArrayList<>();
        List<String> list = new CopyOnWriteArrayList<>();
        //Vector的add方法写了sychronized   Vector  出现比较早  数据一致性保证，但是高并发性急剧下降
//        List<String > list = new Vector<>();

        //Collection:接口
        //Collections:辅助工具类
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
                /***
                 *
                 *  5个常见的异常
                 * 30个线程
                 *  new Vector
                 *  高并发下的异常
                 *java.util.ConcurrentModificationException
                 * 	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:901)
                 * 	at java.util.ArrayList$Itr.next(ArrayList.java:851)
                 * 	at java.util.AbstractCollection.toString(AbstractCollection.java:461)
                 */
                /***
                 * 3个线程
                 * [null, c9e16083, 445c22de]
                 * [null, c9e16083, 445c22de]
                 * [null, c9e16083, 445c22de]
                 */
            }, String.valueOf(i)).start();
        }

        /***
         * 不要只是会用 会用只是一个api调用工程师
         * 笔试的意义不是筛选人才，是过滤学渣
         * 1：故障现象：java.util.ConcurrentModificationException
         * 2：导致原因？
         *          并发争抢修改导致 参考我们的花名册签名情况
         *          一个人正在写，另外一个同学抢夺导致数据 不一致异常  并发修改异常
         *
         * 3：解决方案：
         *          3.1 new Vector()
         *          3.2 Collections.synchronizedList(new ArrayList<>());
         *          3.3 new CopyOnWriteArrayList
         *          3.4
         * 4：优化建议（同样的错误不犯第二次）
         * 故障收集手册 四个维度 软实力 只会撸代码 走不远 天下的技术没有高低之分，只有习武的人有强弱之别。
         */}
}
