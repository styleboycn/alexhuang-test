package com.alexhuang.java8.KeyWord;

import java.util.List;

import static java.lang.System.*;

public class Demo<E> {

    public void extendsTest(List<? extends E> list){
        out.println("extendsTest ok");
    }

    public void superTest(List<? super E> list){
        out.println("superTest ok");
    }

}
