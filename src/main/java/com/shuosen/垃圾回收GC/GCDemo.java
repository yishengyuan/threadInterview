package com.shuosen.垃圾回收GC;

import java.util.Random;

public class GCDemo {
    public static void main(String[] args) {
        System.out.println("GCDemo Hello");
        String str = "atguigu";
        while(true){
            str+=str+new Random().nextInt(11111111)+new Random().nextInt(222222222);
            str.intern();
        }
    }
}
