package com.alexhuang.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionalTest1 {

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

	public static void testbasic1() {
		List list1 = Stream.of("one", "two", "three", "four")
				.filter(e -> e.length() > 3)
				.peek(e -> System.out.println("Filtered value: " + e))
				.map(String::toUpperCase)
				.peek(e -> System.out.println("Mapped value: " + e))
				.collect(Collectors.toList());//print none if no this line!!!
		System.out.println(list1);
	}

	public static void testbasic2() {
		Stream.of("one", "two", "three", "four")
				.filter(e -> e.length() > 3)
				.peek(e -> System.out.println("Filtered value: " + e));
//				.foreach(e -> System.out.println("Filtered value: " + e));
	}
	
	public static void testbasic3() {
		Stream.of("one", "two", "three", "four")
				.filter(e -> e.length() > 3)
				.forEach(e -> System.out.println(e));
	}
	
	public static void testbasic4() {
		Stream.of("one", "two", "three", "four")
				.filter(e -> e.length() > 3)
				.peek(e -> System.out.println("Filtered value: " + e))
				.peek(e -> e.toUpperCase())
				.peek(e -> System.out.println("Mapped value: " + e))
				.collect(Collectors.toList());
	}

	public static void testbasic5() {
		Student std1 = new Student("zhangsan");
		Student std2 = new Student("lisi");
		Student std3 = new Student("wangwu");
		List<Student> studentList = Arrays.asList(std1, std2, std3);
		
		System.out.println("BEFORE CONVERT : ");
		List<Student> convertList = studentList
				.stream()
				.filter(e -> e.getName().length() > 4)
				.peek(e -> System.out.println(e.getName()))
				.peek(Student::convertName)
				.peek(e -> System.out.println(e.getConvertName()))
				.collect(Collectors.toList());
		
		System.out.println("\nAFTER CONVERT : ");
		convertList.forEach(e -> System.out.println(e.getConvertName()));
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
		Optional.ofNullable(std4).orElseThrow(() -> new IllegalArgumentException());
	}
	
	
	public static void main(String[] args) {
		test_Optional_ofNullable();
	}

}
