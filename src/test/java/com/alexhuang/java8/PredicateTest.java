package com.alexhuang.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PredicateTest {

    public static void test_predicate_conditionFilter() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        PredicateTest predicateTest = new PredicateTest();

        List<Integer> result = predicateTest.conditionFilter(list1, integer -> integer > 5);
        result.forEach(System.out::println);
        System.out.println("----------------");

        result = predicateTest.conditionFilter(list1, integer -> true);
        result.forEach(System.out::println);
    }

    public static void test_predicate_conditionFilterAnd() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        PredicateTest predicateTest = new PredicateTest();

        List<Integer> result = predicateTest.conditionFilterAnd(list1, integer -> integer > 5, integer -> integer % 2 == 0);
        result.forEach(System.out::println);
        System.out.println("----------------");
    }

    public static void test_predicate_conditionFilterOr() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        PredicateTest predicateTest = new PredicateTest();

        List<Integer> result = predicateTest.conditionFilterOr(list1, integer -> integer > 5, integer -> integer % 2 == 0);
        result.forEach(System.out::println);
        System.out.println("----------------");
    }

    public static void test_predicate_conditionFilterNegate() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        PredicateTest predicateTest = new PredicateTest();

        List<Integer> result = predicateTest.conditionFilterNegate(list1, integer -> integer > 5);
        result.forEach(System.out::println);
        System.out.println("----------------");
    }

    public List<Integer> conditionFilter(List<Integer> list, Predicate<Integer> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    public List<Integer> conditionFilterAnd(List<Integer> list, Predicate<Integer> predicate1, Predicate<Integer> predicate2) {
        return list.stream().filter(predicate1.and(predicate2)).collect(Collectors.toList());
    }

    public List<Integer> conditionFilterOr(List<Integer> list, Predicate<Integer> predicate1, Predicate<Integer> predicate2) {
        return list.stream().filter(predicate1.or(predicate2)).collect(Collectors.toList());
    }

    public List<Integer> conditionFilterNegate(List<Integer> list, Predicate<Integer> predicate) {
        return list.stream().filter(predicate.negate()).collect(Collectors.toList());
    }

    public static void main(String[] args) {
//        test_predicate_conditionFilter();
//        test_predicate_conditionFilterAnd();
//        test_predicate_conditionFilterOr();
        test_predicate_conditionFilterNegate();
    }
}
