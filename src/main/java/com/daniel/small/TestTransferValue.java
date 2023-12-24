package com.daniel.small;

public class TestTransferValue {
    public void changeValue1(int age) {
        age = 30;
    }

    public void changeValue2(Person person) {
        person.setPersonName("xxxx");
    }

    public void changeValue3(String str) {
        str = "xxx";
    }

    public static void main(String[] args) {
        TestTransferValue test  = new TestTransferValue();
        int age = 20  ;
        test.changeValue1(20);
        System.out.println("age---------------"+age);
        //20 是main方法的
        //在调用changevalue的时候 会复制一份到changeValue这个栈
        //根据栈先进后出的原则  所以值不会改变

        Person person = new Person("abc");
        test.changeValue2(person);
        System.out.println("personName------------"+person.getPersonName());

        String str =  "abc";
        test.changeValue3(str);
        System.out.println("string------------------"+str);


    }

}
