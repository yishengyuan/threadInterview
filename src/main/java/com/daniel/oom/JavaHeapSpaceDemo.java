package com.daniel.oom;

public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        byte[] bytes = new byte[80*1024*1024];
//        String str = "atguigu";
//        while(true){
//            str+=str+new Random().nextInt(11111111)+new Random().nextInt(222222222);
//            str.intern();
//        }

    }
}
