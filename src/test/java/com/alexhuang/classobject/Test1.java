package com.alexhuang.classobject;

//Java Class类以及获取Class实例的三种方式
//http://blog.csdn.net/zhangzeyuaaa/article/details/17289553
//
//Class 没有公共构造方法。Class 对象是在加载类时由Java虚拟机以及通过调用类加载器中的defineClass方法自动构造的，
//因此不能显式地声明一个Class对象。
//
//虚拟机为每种类型管理一个独一无二的Class对象。也就是说，每个类（型）都有一个Class对象。
//运行程序时，Java虚拟机(JVM)首先检查是否所要加载的类对应的Class对象是否已经加载。如果没有加载，JVM就会根据类名查找.class文件，
//并将其Class对象载入。 
public class Test1 {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		test_0();
		
		test_1();
		
		test_2();

	}
	
	public static void test_0() {
		Integer intObj1 = 1;
		Integer intObj2 = 2;
		Object obj = null;
		Class<? extends Object> c = intObj1.getClass();
		System.out.println(intObj1.getClass().getName());
		System.out.println(intObj1.getClass().getSuperclass().getName());
		System.out.println(intObj1.getClass().getSuperclass().getSuperclass().getName());
		System.out.println("##");
		System.out.println(intObj1.getClass());
		System.out.println(intObj2.getClass());
		System.out.println(intObj1.getClass().hashCode());
		System.out.println(intObj2.getClass().hashCode());
		System.out.println("##");
		System.out.println(intObj1.getClass().getSuperclass());
		System.out.println(intObj2.getClass().getSuperclass());
		System.out.println(intObj1.getClass().getSuperclass().hashCode());
		System.out.println(intObj2.getClass().getSuperclass().hashCode());
	}
	
	public static void test_1() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		TaskServiceBiz biz = TaskServiceBiz.class.newInstance();// new
		System.out.println(biz.getClass().hashCode());
		System.out.println(TaskServiceBiz.class.hashCode());
		if (biz.getClass() == TaskServiceBiz.class) {
			System.out.println("object.class 's hashcode is equal to Class.class.hashCode");
		} else {
			System.out.println("NOT equal");
		}
	}
	
	public static void test_2() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class<?> targetClass = Class.forName(TaskServiceBiz.class.getName());
		System.out.println(targetClass.getName());
		System.out.println(targetClass.hashCode());
	}


	public static class TaskServiceBiz {
	}



}
