package com.alexhuang.java8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FlatMapTest2 {
	
	public static class Developer {
	    private String name;
	    private Set<String> languages;

	    public Developer(String name) {
	        this.languages = new HashSet<>();
	        this.name = name;
	    }
	    public void add(String language) {
	        this.languages.add(language);
	    }
	    public Set<String> getLanguages() {
	        return languages;
	    }
	}

	public static void testflatMap() {
        List<Developer> team = new ArrayList<>();
        Developer polyglot = new Developer("esoteric");
        polyglot.add("clojure");
        polyglot.add("scala");
        polyglot.add("groovy");
        polyglot.add("go");

        Developer busy = new Developer("pragmatic");
        busy.add("java");
        busy.add("javascript");

        team.add(polyglot);
        team.add(busy);
//case1:
//        List<String> teamLanguages = 
//        		team.stream()
//        		.map(d -> d.getLanguages())
//        		.flatMap(l -> l.stream())
//        		.collect(Collectors.toList());
//case2:
//        List<String> teamLanguages = 
//        		team.stream()
//        		.flatMap(l -> l.getLanguages().stream())
//        		.collect(Collectors.toList());
//case3:
        Set<String> teamLanguages = 
        		team.stream().flatMap(languages -> languages.getLanguages().stream()).collect(Collectors.toSet());
        teamLanguages.forEach(System.out::println);
        
        System.out.println(teamLanguages.containsAll(polyglot.getLanguages()));
        System.out.println(teamLanguages.containsAll(busy.getLanguages()));
	}
	
	public static class Developer2 {

	    private String name;
	    private int age;
	    private List<String> hobbys;

	    public Developer2(String name, int age) {
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
	
	public static void testmap() {
		List<Developer2> list = new ArrayList<Developer2>();
		list.add(new Developer2("zhangsan", 22));
		list.add(new Developer2("lisi", 33));
		list.add(new Developer2("wangwu", 44));
		List<String> nameList = list.stream().map(Developer2::getName).collect(Collectors.toList());
		nameList.forEach(System.out::println);
	}
	
	public static void testmap2() {
		Map<Integer, String> map = new HashMap<>();
		map.put(7, "已与客户预约，更改收派时间");
		map.put(47, "客户重复下单");
		map.put(46, "客户取消寄件");
		map.forEach((k, v) -> {
			System.out.println(k + "=" + v);
		});
	}
	
	public static void testflatMap2() {
		
		List<Developer2> list = new ArrayList<Developer2>();
		list.add(new Developer2("zhangsan", 22));
		list.add(new Developer2("lisi", 33));
		list.add(new Developer2("wangwu", 44));
		
		Set<String> hobbySet = list.stream().flatMap(l -> l.getHobbys().stream()).collect(Collectors.toSet());
		hobbySet.forEach(System.out::println);
	}
	
	public static void main(String[] a) {

		// testmap();

		// testmap2();

		// testflatMap();

		testflatMap2();

	}
}
