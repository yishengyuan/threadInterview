package com.shuosen.oom;

/***
 * JVM参数
 * -XX:MetaspaceSize=8m  -XX:MaxMetaspaceSize=8m
 * Java8 以及以后的版本使用Metaspace来替代永久代
 * Metaspace 是方法去在HotSpot中的实现 ，它与持久代最大的区别在于 Metaspace并不在虚拟机内存中而是使用本地内存，也即在java8中 class metadata
 * 被存储在叫做Metaspace中的native memory
 * 永久代 （java8 后被元空间Metaspace取代了）   存放了以下信息
 *
 *虚拟机加载的类信息
 * 常量池
 * 静态变量
 * 即时编译后的代码
 * 模拟Metaspace空间移除，我们不断生成往元空间注入，类占据的空间会超过Metaspace指定的空间大小的
 *
 */
public class MetaspaceOOMTest {
    static class OOMTest{

    }
    public static void main(String[] args) {
        int i = 0 ;//模拟多少次以后发生异常
        try{
                while (true){
                    i++;
                }
        }catch(Throwable  e){
            System.out.println("*************第多少次后发生了异常"+i);
            e.printStackTrace();
        }finally{
            
        }
    }
}
