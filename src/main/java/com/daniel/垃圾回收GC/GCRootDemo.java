package com.daniel.垃圾回收GC;

/***
 * 1:虚拟机栈中（栈帧中的本地变量表）中引用的对象
 * 2：方法区中的类静态属性引用的对象
 * 3：方法区中常量引用的对象
 * 4：本地方法栈中JNI一般的native方法中引用的对象
 *
 */
public class GCRootDemo {
    private byte[]  byteArray = new byte[100*1024*1024];
//    static:方法区  永久代  java8 原空间
    private static  GCRootDemo  t2 ;
    private static final  GCRootDemo t3 = new GCRootDemo();
    public static void  m1(){
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("第一次gc完成");
    }

    public static void main(String[] args) {
        m1();
    }
}
