package com.alexhuang.patterndesign;

import com.alexhuang.patterndesign.visitor.Car;
import com.alexhuang.patterndesign.visitor.PrintVisitor;

//http://blog.csdn.net/hfmbook/article/details/7684175
//http://www.cnblogs.com/java-my-life/archive/2012/06/14/2545381.html
//1、創建一個結構對象(object structure)
//2、結構對象增加節點(node)
//3、創建一個訪問者(visitor)
//4、機構對象傳入訪問者，進行相關操作

public class TestVisitor {

	public static void main(String[] args) {
		
		Car car = new Car();
		car.accept(new PrintVisitor());//add a visitor
		
	}

}
