package com.shuosen.oom;

import com.sun.jndi.ldap.EntryChangeResponseControl;

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
