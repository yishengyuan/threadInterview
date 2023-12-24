package com.daniel.reference;

import java.util.HashMap;
import java.util.WeakHashMap;

public class WeakHashMapDemo {


    public static void myHashMap(){
        HashMap<Integer,String > map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "Hashmap";
        map.put(key,value);
        System.out.println(map);

        key = null ;
        System.out.println(map);
        /**
         *结果：
         * {1=Hashmap}
         * {1=Hashmap}
         * @param args
         */
        System.gc();
        System.out.println(map.size());  //1
        //还是有size
    }
    private static void myWeakHashMap() {
        WeakHashMap<Integer,String > map = new WeakHashMap<Integer,String >();
        Integer key = new Integer(2);
        String value = "WeakHashMap";
        map.put(key,value);
        System.out.println(map);

        key = null ;
        System.out.println(map);
        /**
         *结果：
         * {1=WeakHashmap}
         * {1=WeakHashmap}
         * @param args
         */
        System.gc();
        System.out.println(map.size()); //0
    }

    public static void main(String[] args) {
//        myHashMap();
        myWeakHashMap();
    }


}
