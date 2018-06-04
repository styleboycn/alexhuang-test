package com.alexhuang.patterndesign;

import com.alexhuang.patterndesign.adapter.classadapter.*;
import com.alexhuang.patterndesign.adapter.objectadapter.*;

/**
 * 装饰模式和代理模式很相似
 * 1、适配器模式（Adapter Pattern）
	适配器模式把一个类的接口变换成客户端所期待的另一种接口，
	从而使原本因接口不匹配而无法在一起工作的两个类能够在一起工作。
	适配器模式有类适配器模式和对象适配器模式两种不同的形式。
	《JAVA与模式》之适配器模式
	http://www.cnblogs.com/java-my-life/archive/2012/04/13/2442795.html
 *
 */

public class AdapterTest {
	
	public static void main(String[] args) {
		classAdapterTest();
		objectAdapterTest();
	}
	
	//类适配器模式
	public static void classAdapterTest() {
		IClassTargetInf classAdapter = new ClassAdapter();
		classAdapter.sampleOperation1();
		classAdapter.sampleOperation2();
	}
	
	//对象适配器模式
	public static void objectAdapterTest() {
		ObjectAdaptee adaptee = new ObjectAdaptee();
		IObjectTargetInf objectAdapter = new ObjectAdapter(adaptee);
		objectAdapter.sampleOperation1();
		objectAdapter.sampleOperation2();
	}
	
}
