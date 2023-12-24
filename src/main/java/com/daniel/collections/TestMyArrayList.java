package com.daniel.collections;

public class TestMyArrayList {
    public static void main(String[] args) {
        MyArrayList my = new MyArrayList();//容器对象
        my.add("张三");
        my.add("李四");
        System.out.println("个数"+my.size());

        Object[] all = my.getAll();
        for (Object o : all) {
            System.out.println(o);
        }
    }
}
