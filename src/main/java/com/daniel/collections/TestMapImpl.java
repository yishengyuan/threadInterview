package com.daniel.collections;

import com.daniel.collections.bean.Employee;
import org.junit.Test;

import java.util.*;

/***
 * Map和key是不重复的，value可能重复。
 * key 和value都是任意引用数据类型，只是大多数时候，key使用Integer和String
 *
 * Map集合的实现类，
 * (1) HashMap：散列表
 * （2） HashTable：散列表
 * （3）TreeMap：
 * (4) LinkedHashMap
 * (5) Properties
 *
 *
 * HashTable和HashMap ：
 * （1） HashTable:旧的 线程安全的  任何Key和value都不能为null
 * （2） Hashmap:新的 线程不安全的   允许key和value可以为null
 *
 *   LinkedHashmap是HashMap的子类 ，比HashMap多为胡的映射关系的添加顺序
 *   在添加和删除，比Hashmap要多维护一下前后的关系，相对效率低一些。
 *   Properties：是HashTable的子类，并且它比较特殊，它的key和value的类型只能是String类型
 *
 *   Treemap是一种特殊的集合，特殊在它会按照key的大小顺序排序。
 *   默认按照key的自然排序规则 ，要求key的类型实现java.lang.Comparable 接口 重写  int compareTo(Object obj)方法
 *   也可以为TreeMap指定一个java.util.Comprator接口的实现类对象，重写 int compare(Object o1,Object o2)方法
 *   HashTable和HashMap  LinkedHashMap和Properties的key不可重复和存储都是依赖于key的hash值和equlas方法
 *
 *
 *   TreeMap的key不可重复和依赖于 compareTo和compare比较规则 ，它认为大小相等的两个key只能有一个。
 *
 *
 *   存储到HashTable和Hashmap LinkedHashMap Properties 这些map的映射关系是key
 *   一旦存储进去，那么参与它的hash值和equlas比较的属性值就不要修改了，因为一旦修改就会导致原来的数据找不到了。
 *   StringBuffer和StringBuilder：都是字符串缓冲区 也成为可变字符序列
 *   Vector 和ArrayList
 */
public class TestMapImpl {


    @Test
    public void testMapSort(){
          Map<String,String> map = new TreeMap<String,String>();


    }

    @Test
    public void test7(){
        HashMap map = new HashMap();

        Employee e1 = new Employee(1,"张三");
        map.put(e1,"hah");
        Employee e2 = new Employee(2,"李四");
        map.put(e2,"hehe");
        Employee e3 = new Employee(3,"王五");
        map.put(e3,"xixi");

        e1.setName("张三丰");
//        map.put(e1,"张三丰");
        Object o = map.get(e1);
        System.out.println(o);
    }

    @Test
    public void test6(){
        HashMap map = new HashMap();
        map.put(new Employee(1,"张三"),"haha");
        map.put(new Employee(2,"李四"),"heh");
        map.put(new Employee(3,"王五"),"xixi");

        Object zhangsan = map.get(new Employee(1, "张三"));
        System.out.println(zhangsan);
    }


    @Test
    public void test5(){
        TreeMap  tree = new TreeMap();
        tree.put(new Employee(1,"张三"),"hah");
    }

    @Test
    public void test4(){
        TreeMap  tree = new TreeMap();
        //key是Integer ，他实现了Comparable接口
        tree.put("如花","张胜男");
        tree.put("凤姐","张三");
        tree.put("芙蓉","张超");

        Set set = tree.entrySet();
        for (Object o : set) {
            System.out.println(o);
        }
    }

    @Test
    public void test3(){
        TreeMap  tree = new TreeMap();
        //key是Integer ，他实现了Comparable接口
        tree.put(3,"张胜男");
        tree.put(2,"张三");
        tree.put(1,"张超");

        Set set = tree.entrySet();
        for (Object o : set) {
            System.out.println(o);
        }
    }


    @Test
    public void test1(){
        //存储用户名和密码的对应关系
        Properties  pro  = new Properties();
        pro.put("chai","123");
        pro.setProperty("admin","abc");

        String admin = pro.getProperty("admin");
        Object chai = pro.get("chai");
        System.out.println(chai);
        System.out.println(admin);

    }

}
