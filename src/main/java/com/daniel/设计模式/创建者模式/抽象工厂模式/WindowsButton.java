package com.daniel.设计模式.创建者模式.抽象工厂模式;

public class WindowsButton implements Button {

    @Override
    public void paint() {
        System.out.println("You have created WindowsButton.");
    }
}