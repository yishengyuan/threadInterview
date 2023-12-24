package com.daniel.collections;

/***
 *
 * 我想自己设计一个容器类 动态数组
 *
 */
public interface MyList {
    void add(Object obj); //添加

    Object get(int index);//获取

    void remove(Object obj);//删除

    int size();//获取有效元素个数

    void set(int index, Object obj);  //替换【index】位置的元素

    void set(Object old,Object replace);//把当前容器的Old对象替换为newObj

    Object[] getAll(); //获取所有的元素

}
