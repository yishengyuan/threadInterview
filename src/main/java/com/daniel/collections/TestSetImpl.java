package com.daniel.collections;

import com.daniel.collections.bean.Student;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/***
 * Set:无序的不可重复的
 * 常见的实现类：
 * (1)HashSet
 *  不可重复是根据原始的hashCode和equlas方法
 *  无序，HashSet内部存储，不是按照添加的顺序存储的，是有自己的内部结构，是和hashCode值有关
 * (2)TreeSet
 *    不可重复 ：依据Comparable的compareTo()  只要是compareTo返回的是0的两个对象，只能进去一个，它认为大小相等的两个对象是重复对象
 *    不保证添加顺序，但是有大小顺序
 *     结论：添加到TreeSet中的元素必须支持可比较大小，即实现java.lang.Comparable接口
 *
 * (3) LinkedHashSet  它是HashSet的子类 ，它比较HashSet多维护了元素添加的顺序
 *      添加和删除比较低，要维护前后元素的顺序关系
 *      如果，你想用一个集合，既要不可重复，又要维持添加顺序，那么选择它可以做到
 *
 *
 * */
public class TestSetImpl {

    @Test
    public void test7() {
        LinkedHashSet set = new LinkedHashSet();
        set.add(4);
        set.add(1);
        set.add(3);
        set.add(5);
        set.add(2);
        for (Object o : set) {
            System.out.println(o);
        }
    }

    @Test
    public void test6() {
        TreeSet set = new TreeSet();
        set.add(new Student(1,"张三"));
        set.add(new Student(2,"李四"));
        set.add(new Student(3,"张三"));
        set.add(new Student(1,"李四")); //进不去
        for (Object o : set) {
            System.out.println(o);
        }
    }

    @Test
    public void test5() {
        TreeSet set = new TreeSet();
        //元素是Integer类型
        set.add(4);
        set.add(1);
        set.add(3);
        set.add(5);
        set.add(2);
        for (Object o : set) {
            System.out.println(o);
        }
    }

    @Test
    public void test4() {
        TreeSet set = new TreeSet();
        //实现Comparable接口  所以可以进行排序
        set.add("Jack");
        set.add("Alice");
        set.add("Rose");
        set.add("Leo");
        set.add("Lily");
        for (Object o : set) {
            System.out.println(o);
        }
    }


    @Test
    public void test3() {
        TreeSet set = new TreeSet();
        set.add("张三");
        set.add("李四");
        set.add("张三");
        set.add("李四");
        set.add("王五");
        for (Object o : set) {
            System.out.println(o);
        }
    }

    @Test
    public void test2() {
        HashSet set = new HashSet();
        set.add(new Student(1,"张三"));
        set.add(new Student(2,"李四"));
        set.add(new Student(3,"张三"));
        set.add(new Student(1,"李四"));
        for (Object o : set) {
            System.out.println(o);
        }

    }


    @Test
    public void test1() {
        HashSet hashSet = new HashSet();
        hashSet.add("张三");
        hashSet.add("李四");
        hashSet.add("张三");
        hashSet.add("李四");
        hashSet.add("王五");
        for (Object o : hashSet) {
            System.out.println(o);
        }


    }
}
