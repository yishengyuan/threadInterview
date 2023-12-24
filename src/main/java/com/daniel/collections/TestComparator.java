package com.daniel.collections;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

/***
 * 在TreeSet存储元素时
 *  （1）如果没有Treeset单独传 java.util.Comparator接口的实现类对象，那么TreeSet会按照元素的自然顺序，实现java.lang.Comparable接口
 *  （2）如果给TreeSet单独传java.util.Compaator的接口的实现类对象，那么TreeSet按照Comparator接口的实现类compare(o1,o2)比较元素大小
 */
public class TestComparator {
    @Test
    public void test(){
        //现在要把Employee的对象存储到Treesset中 ，
        //方案一：Employee实现java.lang.Comparable接口 ，并重写int  CompareTo(Object obj)方法，我们称之为自然排序
        //方案二：Employee不方便实现java.lang.Comparable接口 或者是重写的CompareTo(Object obj)方法的实现规则不适合当前的操作
        //可以选择方案二，给TreeSet传一个java.util.Comparator接口的实现类对象 用于比较元素的大小 ，我们称之为定制排序
        //注意java.util.Comparator接口的一个抽象方法需要实现 int compare（Object o1 ,Object o2）


        TreeSet set = new TreeSet(new EmployeeComparator());
        set.add(new Employee(1,"张三",1000000));
        set.add(new Employee(2,"李四",1000000));
        set.add(new Employee(3,"王五",3000000));

        for (Object o : set) {
            System.out.println(o);
        }
    }

    @Data
    @AllArgsConstructor
    @ToString
    class Employee{
        private int id ;
        private String name ;
        private double salaray;
    }
    //java.util.Comparator
    class EmployeeComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            Employee e1 = (Employee) o1;
            Employee e2 = (Employee) o2;
            //先按照薪资排序 ，如果薪资排序 再按照编号排序
            if(e1.getSalaray() > e2.getSalaray()){
                return  1  ;
            } else if(e1.getSalaray() < e2.getSalaray()){
                return -1 ;
            }else{
                return -(e1.getId() - e2.getId()) ;
            }
        }
    }
}
