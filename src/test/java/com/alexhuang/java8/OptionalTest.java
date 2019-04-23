package com.alexhuang.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionalTest {

	public static class Student {
		private String name;
		private String convertName;

		public Student(String name) {
			this.name = name;
		}

		public void convertName() {
			this.convertName = name.toUpperCase();
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getConvertName() {
			return convertName;
		}

		public void setConvertName(String convertName) {
			this.convertName = convertName;
		}
	}

	public static void test_Optional_ofNullable() {
		Student std1 = new Student("zhangsan");
		Student std2 = new Student("lisi");
		Student std3 = new Student("wangwu");
		List<Student> studentList = Arrays.asList(std1, std2, std3);
		Student std4 = null;

		// test orElse when result equal null
		String std4_result = Optional.ofNullable(std4).map(Student::getName).orElse("none");
		System.out.println("std4_result : " + std4_result);

		// test orElse when result isn't null
		String std3_result = Optional.ofNullable(std3).map(Student::getName).orElse("none");
		System.out.println("std3_result : " + std3_result);

		// test orElseThrow
		Optional.ofNullable(std4).map(Student::getName).orElseThrow(() -> new IllegalArgumentException());
	}

	public static void test_Optional_ifPresent_1() {
		String std1 = "hello1111";
		Optional<String> optStr = Optional.ofNullable(std1);
		optStr.ifPresent(s -> System.out.println(s));
	}

    public static void test_Optional_ifPresent_2() {
        String std1 = null;
        Optional<String> optStr = Optional.ofNullable(std1);
        optStr.ifPresent(s -> System.out.println(s));
    }

    public static void test_Optional_ifPresent_3() {
        String std1 = null;
        Optional<String> optStr = Optional.ofNullable(std1);
        System.out.println(optStr.orElseGet(() -> "kk11"));
    }
	
	
	public static void main(String[] args) {
		test_Optional_ofNullable();
//      test_Optional_ifPresent_1();
//      test_Optional_ifPresent_2();
//        test_Optional_ifPresent_3();
	}

}
