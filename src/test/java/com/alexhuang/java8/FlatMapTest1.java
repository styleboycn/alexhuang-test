package com.alexhuang.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapTest1 {
	
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
		
	public static List<Student> studentList = new ArrayList<Student>(); 
	
	
	public static void testbasic1() {
		
		studentList.add(new Student("zhangsan", 15, Level.RED));
		studentList.add(new Student("lisi", 17, Level.GREEN));
		studentList.add(new Student("wangwu", 19, Level.YELLOW));
		
		List<String> ids = 
				studentList.
				stream().
				filter(l -> l.age > 15).
				filter(l -> l.level == Level.YELLOW).
				map(Student::getName). //map(stu -> stu.getName())
				collect(Collectors.toList());
		ids.forEach(System.out::println);
	}
	
	public static void testbasic2() {
		List<Integer> nums = Arrays.asList(1,1,null,2,3,4,null,5,6,7,8,9,10);
		System.out.println("sum is:"
				+ nums.stream().filter(num -> num != null).distinct()
						.mapToInt(num -> num * 2).peek(System.out::println)
						.skip(2).limit(5).sum());
		
	}
	//有时候我们想替换为一个新的Stream对象，更常见的是把多个Stream和合并为一个Stream
	public static void testflatMap() {
		List<Integer> together = Stream
				.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
				.flatMap(numbers -> numbers.stream())
				.collect(Collectors.toList());
		together.forEach(System.out::println);
	}

	
	public static void main(String[] args) {

		testbasic1();
		
		testbasic2();
		
		testflatMap();
		
	}

	
//case1:
//	public Set<String> findLongTracks(List<Album> albums) {
//	    Set<String> trackNames = new HashSet<>();
//	    for(Album album : albums) {
//	        for (Track track : album.getTrackList()) {
//	            if (track.getLength() > 60) {
//	                String name = track.getName();
//	                trackNames.add(name);
//	            }
//	        }
//	    }
//	    return trackNames;
//	}

//case2:
//	public Set<String> findLongTracks(List<Album> albums) {
//	    return albumns.stream()
//	           .flatMap(album -> album.getTracks()) //合并每個專輯(albums)中的所有歌曲(track)
//	           .filter(track -> track.getLength() > 60)//過濾每個歌曲的特定屬性
//	           .map(track -> track.getName())//對每個歌曲獲取名字(函數轉換/操作)
//	           .collect(toSet());//轉換成一個集合(set)
//	}

	
	
}
