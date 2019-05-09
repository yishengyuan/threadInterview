package com.collections;

import java.util.Arrays;

/***
 *
 * 我想自己设计一个容器类 ：动态数组
 * 对于使用者 ，使用MyArrayList对象时，像是一个会自动扩容的容器 ，但是它的底层是数组存储
 *
 */
public class MyArrayList  implements  MyList {

  private  Object[] data ; //实际存储的数组

   private int total  ;//记录有效元素的个数

   public MyArrayList(){
       data= new Object[10];
   }


    @Override
    public void add(Object obj) {
        if(total>data.length){
            data = Arrays.copyOf(data,data.length*2);
        }
        data[total++] = obj  ;
    }

    @Override
    public Object get(int index) {
        checkIndex(index);
        return data[index];
    }

    private void checkIndex(int index) {
        if(index <0 || index >= total){
            throw new IndexOutOfBoundsException(index+"超过数组边界");
        }
    }

    @Override
    public void remove(Object obj) {
        //(1)先找到Obj的下标
        int index = indexOf(obj);
        //(2)找到了 就删除
        if(index != -1){
            System.arraycopy(data,index +1,data,index ,total-index -1 );
            data[--total ]= null ;
        }
    }

    private int indexOf(Object obj) {
        int index = -1;
        if(obj!=null) {
            for (int i = 0; i < total; i++) {
                if (obj.equals(data[i])) {
                    index = i;
                    break;
                }
            }
        }else{
            for (int i = 0; i < total; i++) {
                if(obj == data[i]){
                    index = i ;
                    break  ;
                }
            }
        }
        return index;
    }

    @Override
    public int size() {
        return total;
    }

    @Override
    public void set(int index, Object obj) {
        checkIndex(index);
      data[index] = obj ;
    }

    @Override
    public void set(Object old, Object newObj) {
       //先找到old的下标index
        int index = indexOf(old);
        //(2) 用set(index,newObj)
        set(index,newObj);
    }

    @Override
    public Object[] getAll() {
        return new Object[0];
    }
}
