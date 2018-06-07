package com.alexhuang.patterndesign;

import com.alexhuang.patterndesign.visitor.Car;
import com.alexhuang.patterndesign.visitor.PrintVisitor;

//《JAVA与模式》之访问者模式
//http://www.cnblogs.com/java-my-life/archive/2012/06/14/2545381.html
//访问者模式适用于数据结构相对未定的系统，它把数据结构和作用于结构上的操作之间的耦合解脱开，使得操作集合可以相对自由地演化。

public class VisitorTest {

	public static void main(String[] args) {
		Car car = new Car("weilai");
		car.accept(new PrintVisitor());//add a visitor
		car.action();
	}

}
