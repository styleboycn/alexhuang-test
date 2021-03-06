package com.alexhuang.java8;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapTest {

	public static class Student {
		private String name;
		private int age;
		private Level level;

		public Student(String name, int age, Level level) {
			this.name = name;
			this.age = age;
			this.level = level;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public Level getLevel() {
			return level;
		}

		public void setLevel(Level level) {
			this.level = level;
		}
	}

	public enum Level {
		RED, GREEN, BLANK, YELLOW
	}

	public static List<Student> studentList = Arrays.asList(
			new Student("zhangsan", 15, Level.RED),
			new Student("lisi", 17, Level.GREEN),
			new Student("wangwu", 19, Level.YELLOW)
			);

	public static void testbasic1() {
		List<String> ids = studentList.stream()
				.filter(l -> l.age > 15)
				.filter(l -> l.level == Level.YELLOW)
				.map(l -> l.getName())
				.collect(Collectors.toList());
		ids.forEach(System.out::println);
	}

	public static void testbasic2(Predicate<Student> predicate1, Predicate<Student> predicate2) {
		List<String> ids = studentList.stream()
				.filter(predicate1)
				.filter(predicate2)
				.map(Student::getName) //方法引用
				.collect(Collectors.toList());
		ids.forEach(System.out::println);
	}
	public static Predicate<Student> predicateOfgreatThan15() {
		return p -> p != null && p.getAge() > 15;
	}
	public static Predicate<Student> predicateOfLevelIsYellow() {
		return p -> p != null && p.getLevel() == Level.YELLOW;
	}

	public static void testbasic3() {
		List<Integer> nums = Arrays.asList(1, 1, null, 2, 3, 4, null, 5, 6, 7,
				8, 9, 10);
		
		int valueOfSum = nums.stream()
				.filter(num -> num != null)
				.distinct()
				.mapToInt(num -> num * 2)
				.peek(System.out::println)
				.skip(2)
				.limit(4)
				.sum();
		System.out.println("sum is : " + valueOfSum);
	}

	// 有时候我们想替换为一个新的Stream对象，更常见的是把多个Stream和合并为一个Stream
	public static void test_stream_flatmap() {
		List<Integer> together = Stream
				.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
				.flatMap(numbers -> numbers.stream())
				.collect(Collectors.toList());
		together.forEach(System.out::println);
	}

	public static void test_summary_stat() {
		//同时获取最大 最小 平均值等信息
		List<Integer> list1 = Arrays.asList(1, 3, 5, 7, 9, 11);
		IntSummaryStatistics statistics = list1.stream().filter(integer -> integer > 2).mapToInt(i -> i * 2).skip(2).limit(2).summaryStatistics();
		System.out.println(statistics.getMax());//18
		System.out.println(statistics.getMin());//14
		System.out.println(statistics.getAverage());//16
	}
	
	public static void main(String[] args) {

//		testbasic1();

//		testbasic2(predicateOfgreatThan15(), predicateOfLevelIsYellow());

//		testbasic3();
		
//		testflatMap();

		test_summary_stat();
	}

}
