package com.alexhuang.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class FunctionTest {


    public static void test_function() {
        FunctionTest functionTest = new FunctionTest();
        System.out.println(functionTest.compute1(5,i -> i * 2,i -> i * i));//50
        System.out.println(functionTest.compute2(5,i -> i * 2,i -> i * i));//100
    }

    public static void test_biFunction() {
        FunctionTest biFunctionTest = new FunctionTest();
        System.out.println(biFunctionTest.compute(4,5,(a,b) -> a * b,a -> a * 2));
    }

    public int compute1(int i, Function<Integer,Integer> after, Function<Integer,Integer> before){
        return after.compose(before).apply(i);
    }

    public int compute2(int i, Function<Integer,Integer> before,Function<Integer,Integer> after){
        return before.andThen(after).apply(i);
    }

    public int compute(int a, int b, BiFunction<Integer,Integer,Integer> biFunction,
                       Function<Integer,Integer> function){
        return biFunction.andThen(function).apply(a,b);
    }

    public static void main(String[] args) {
//        test_function();
        test_biFunction();
    }
}
