package com.collections.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class Student  implements  Comparable{
    private int  id  ;
    private String name ;


    @Override
    public int compareTo(Object o) {
        Student  stu = (Student) o;
        /**
         * this.id > stu.id  返回正整数
         * this.id < stu.id  返回负整数
         * this.id = stu.id  返回0
         */
        return this.id - stu.id;
    }
}
