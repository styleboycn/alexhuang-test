package com.alexhuang.proxy.dsproxy;

public class TestCityCodeHolder {

	private static ThreadLocal<String> holder = new ThreadLocal<>();
	
	public static String get() {
		return holder.get();
	}
	
	public static void set(String cityCode) {
		holder.set(cityCode);
	}

}
