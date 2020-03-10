package com.shuosen.oom;

import java.util.ArrayList;
import java.util.List;

/****
 * -Xms10m  -Xmx10m  -XX:PrintGCDetails -XX:MaxDirectMemorySize=5m
 * GC回收时间过长时会抛出 OutOfMemoryError
 * 过长的定义是，超过98%的时间来做GC 并且回收了不到2%的堆内存
 * 连续多次GC都回收不到2%的极端情况下才会抛出，假如不抛出GC OverHead limit 错误会发生什么情况呢？
 * 那就是GC清理的这么点内存很快就会再次填满，破事GC再次执行，这样就行成恶性循环 ，CPU使用率一直是100%  ，而GC 却没有任何成果
 *
 */
public class GCOverHeadDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();
        try{
            while(true){
                list.add(String.valueOf(i++).intern());
            }
        }catch(Throwable e){
            System.out.println("**************i"+i);
            e.printStackTrace();
        }finally{

        }

    }
}
