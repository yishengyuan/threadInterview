package com.collections.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
//实现Comparable   并重写compareTo方法
public class Employee implements  Comparable{
    private int id  ;
    private String name ;

    @Override
    public int compareTo(Object o) {
        Employee e = (Employee) o;
        return this.id - e.id;
    }
}
