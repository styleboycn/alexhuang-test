package com.alexhuang.java8.KeyWord;

import java.util.ArrayList;
import java.util.List;

public class KeyWordTest {

/*
logical Relationship
            A
           / \
          A1 A2
          /
         B
        / \
       B1 B2
       |
       C
  <? extends E> 上限通配符，用来限制类型的上限
  <? super E> 下限通配符，用来限制类型的下限
*/

    public static void main(String[] args) {
        Demo<Class_B> targetList = new Demo<>();
        List<Class_A> aList = new ArrayList<>();
        List<Class_A1> a1List = new ArrayList<>();
        List<Class_A2> a2List = new ArrayList<>();
        List<Class_B> bList = new ArrayList<>();
        List<Class_B1> b1List = new ArrayList<>();
        List<Class_C> cList = new ArrayList<>();

//      extends 子类对象，使用继承基类的子类都能用
//      可以理解成向下兼容
//      targetList B类的对象
//      targetList.extendsTest(aList);       // 基类的基类对象    报错
//      targetList.extendsTest(a1List);      // 基类对象         报错
//      targetList.extendsTest(a2List);      // 基类的兄弟类对象   报错
        targetList.extendsTest(bList);       // 自己对象         可用
        targetList.extendsTest(b1List);      // 子类对象         可用
        targetList.extendsTest(cList);       // 子类的子类        可用

//      super 基类对象，使用继承基类及其父类都能用
//      可以理解成向上兼容
        targetList.superTest(aList);         // 基类的基类        可用
        targetList.superTest(a1List);        // 基类             可用
//      targetList.superTest(a2List);        // 基类的兄弟类       报错
        targetList.superTest(bList);         // 自己对象          可用
//      targetList.superTest(b1List);        // 子类对象          报错
//      targetList.superTest(cList);         // 子类的子类        报错
    }
}
