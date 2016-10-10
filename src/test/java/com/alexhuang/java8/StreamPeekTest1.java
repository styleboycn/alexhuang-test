package com.alexhuang.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPeekTest1 {

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
		Stream.of("one", "two", "three", "four")
				.filter(e -> e.length() > 3)
				.peek(e -> System.out.println("Filtered value: " + e))
				.map(String::toUpperCase)
				.peek(e -> System.out.println("Mapped value: " + e))
				.collect(Collectors.toList());
	}

	public static void testbasic2() {
		Stream.of("one", "two", "three", "four")
				.filter(e -> e.length() > 3)
				.peek(e -> System.out.println("Filtered value: " + e));
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
	
	public static void testbasic6() {
		Student std1 = new Student("zhangsan");
		Student std2 = new Student("lisi");
		Student std3 = new Student("wangwu");
		List<Student> studentList = Arrays.asList(std1, std2, std3);
		
		System.out.println("BEFORE CONVERT : ");
		Map<? extends String, ? extends Student> convertMap = studentList
				.stream()
				.filter(e -> e.getName().length() > 4)
				.peek(e -> System.out.println(e.getName()))
				.peek(Student::convertName)
				.peek(e -> System.out.println(e.getConvertName()))
				.collect(Collectors.toMap(Student::getName, Function.identity()));
		
		System.out.println("\nAFTER CONVERT : ");
		System.out.println(convertMap.size());
		System.out.println(convertMap.get("zhangsan"));
		System.out.println(convertMap.get("lisi"));
		System.out.println(convertMap.get("wangwu"));
	}
	
	
	public static void main(String[] args) {

//		testbasic1();
//		testbasic2();
//		testbasic3();
//		testbasic4();
//		testbasic5();
		testbasic6();
		
	}

}
