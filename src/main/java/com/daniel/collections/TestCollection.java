package com.daniel.collections;

import com.daniel.collections.bean.Employee;
import org.junit.Test;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

public class TestCollection {


    /***
     *
     *
     * TreeMap是一个特殊的集合，特殊在它会按照大小顺序排列
     *    默认按照key的自然排序规则，要求key的实现java.lang.Comparable接口 重写int compareTo（Object obj）方法
     *    也可以作为TreemMap 指定一个java.util.Comparator接口的实现类对象，重写int  compare (Object o1,Object o2)方法
     * 特别的说明：存储到HashTable和Hashmap LinkedHashMap,Properties这些map中的映射关系的key，
     * 要注意，一旦存储进去，那么参与它的hash值得计算和equlas比较的属性的值就不要修改了，因为一旦修改了，就会导致原来的数据找不到了
     *
     *
     * StringBuffer和Stirngbuilder 都是字符串缓冲区 也是可变字符列表
     */
    @Test
    public void  test7(){
        HashMap map = new HashMap();
        Employee e1  = new Employee(1, "张三");

        map.put(e1,"hah");

        Employee e2  = new Employee(1, "李四");

        map.put(e2,"heh");
        Employee e3  = new Employee(1, "张三");

        map.put(e3,"xixi");

        e1.setName("张三丰");//为什么setname后 获取Object为null  ？
        //修改了name值，因为name参与hashCode的计算，意味着hashcode变了 。

        Object object  = map.get(e1 ); //需要重写equlas和hashcode
        System.out.println(object);
    }



    @Test
    public void  test6(){
        HashMap map = new HashMap();
        map.put(new Employee(1,"张三"),"hah");
        map.put(new Employee(2,"李四"),"hah");
        map.put(new Employee(3,"王五"),"hah");
        Object object  = map.get(new Employee(1, "张三")); //需要重写equlas和hashcode
        System.out.println(object);
    }


    @Test
    public void testTreeMap3(){
        TreeMap tree = new TreeMap();
        //key是Integer类型  Itege实现了Comparable接口
        tree.put(new Employee(1,"张三"),"哈哈");
        tree.put(new Employee(1,"张三"),"哈哈");
        /***
         * java.lang.ClassCastException: com.daniel.daniel.collections.bean.Employee cannot be cast to java.lang.Comparable
         * 对象类型 必须实现Comparable
         */
        Set entrySet = tree.entrySet();
        for (Object object : entrySet) {
            System.out.println(object);
        }
        /***
         * Employee(id=1, name=张三)=哈哈
         * 输出哈哈
         */
    }


    @Test
    public void testTreeMap1(){
        TreeMap tree = new TreeMap();
        //key是Integer类型  Itege实现了Comparable接口
        tree.put(3,"张胜男");
        tree.put(1,"张超");
        tree.put(2,"张胜男");
        Set entrySet = tree.entrySet();
        for (Object object : entrySet) {
            System.out.println(object);
        }
    }

    @Test
    public void testTreeMap2(){
        TreeMap tree = new TreeMap();
        //key是String  实现了Comparable接口
        tree.put("如花","张胜男");
        tree.put("凤姐","张超");
        tree.put("芙蓉","张胜男");
        Set entrySet = tree.entrySet();
        for (Object object : entrySet) {
            System.out.println(object);
        }
    }

}
