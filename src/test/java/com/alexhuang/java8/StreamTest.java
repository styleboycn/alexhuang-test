package com.alexhuang.java8;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {

	public static class Student {
		private String name;
		private String convertName;
		private int age;

		public Student(String name) {
			this.name = name;
		}

		public Student(String name, int age) {
			this.age = age;
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

		public int getAge() {
			return age;
		}

		public void setAge(int age) { this.age = age; }
	}

	public static class Developer {

		private String name;
		private int age;
		private List<String> hobbys;

		public Developer(String name, int age) {
			this.name = name;
			this.age = age;
			this.hobbys = Arrays.asList("snooker", "football", String.valueOf(System.currentTimeMillis()));
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

		public List<String> getHobbys() {
			return hobbys;
		}

		public void setHobbys(List<String> hobbys) {
			this.hobbys = hobbys;
		}
	}

	public static void test_basic_1() {
		List list1 = Stream.of("one", "two", "three", "four")
				.filter(e -> e.length() > 3)
				.peek(e -> System.out.println("Filtered value: " + e))
				.map(String::toUpperCase)
				.peek(e -> System.out.println("Mapped value: " + e))
				.collect(Collectors.toList());//print none if no this line!!!
		System.out.println(list1);
	}

	public static void test_basic_2() {
		Stream.of("one", "two", "three", "four")
				.filter(e -> e.length() > 3)
				.peek(e -> System.out.println("Filtered value: " + e));
//				.foreach(e -> System.out.println("Filtered value: " + e));
	}
	
	public static void test_basic_3() {
		Stream.of("one", "two", "three", "four")
				.filter(e -> e.length() > 3)
				.forEach(e -> System.out.println(e));
	}
	
	public static void test_basic_4() {
		Stream.of("one", "two", "three", "four")
				.filter(e -> e.length() > 3)
				.peek(e -> System.out.println("Filtered value: " + e))
				.peek(e -> e.toUpperCase())
				.peek(e -> System.out.println("Mapped value: " + e))
				.collect(Collectors.toList());
	}

	public static void test_basic_5() {
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
	
	public static void test_basic_6() {
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

	public static void test_basic_7() {
		Student std1 = new Student("zhangsan", 20);
		Student std2 = new Student("lisi", 30);
		Student std3 = new Student("wangwu", 40);
		List<Student> studentList = Arrays.asList(std1, std2, std3);

		int totalAge = studentList.stream().
				filter(s -> s.getAge() > 20).
				mapToInt(s -> s.getAge()).
				sum();
		System.out.println("\nSum Result : " + totalAge);

		List<String> totalName = studentList.stream().
				filter(s -> s.getAge() > 10).
				map(s -> s.getName()).
				collect(Collectors.toList());
		System.out.println("\ntotalName Result : " + totalName);
		totalName.forEach(w -> System.out.println(w));
	}

	public static void test_stream_foreach_list() {
		List<Developer> list = new ArrayList<Developer>();
		list.add(new Developer("zhangsan", 22));
		list.add(new Developer("lisi", 33));
		list.add(new Developer("wangwu", 44));
		List<String> nameList = list.stream().map(Developer::getName).collect(Collectors.toList());
		nameList.forEach(System.out::println);
	}

	public static void test_stream_foreach_map() {
		Map<Integer, String> execptMap = new HashMap<>();
		execptMap.put(7, "已与客户预约，更改收派时间");
		execptMap.put(47, "客户重复下单");
		execptMap.put(46, "客户取消寄件");
		execptMap.forEach((k, v) -> {
			System.out.println(k + "=" + v);
		});
	}

	public static void test_stream_foreach_set() {
		List<Developer> list = new ArrayList<Developer>();
		list.add(new Developer("zhangsan", 22));
		list.add(new Developer("lisi", 33));
		list.add(new Developer("wangwu", 44));
		Set<String> hobbySet = list.stream().flatMap(l -> l.getHobbys().stream()).collect(Collectors.toSet());
		hobbySet.forEach(System.out::println);
	}

	public static void test_stream_function_test1() {
//		Consumer<String> c = i -> System.out.println("hello" + i);
		Function<String, String> f = i -> {
			if (i == "aa")
				return i + "hello2";
			else
				return i + "hello1";
		};
		Stream.of("aa", "bb", "cc").map(f).forEach(System.out::println);
	}

	public static void test_stream_groupingby_1() {
		//xxxxxx
	}

	public static void test_stream_groupingby_2() {
		//
	}

	public static void test_stream_partitioningby_1() {
		//
	}

	public static void main(String[] args) {
//		test_basic_1();
//		test_basic_2();
//		test_basic_3();
//		test_basic_4();
//		test_basic_5();
//		test_basic_6();
//		test_basic_7();
//		test_stream_foreach_list();
//		test_stream_foreach_map();
//		test_stream_foreach_set();
		test_stream_function_test1();
	}

}
