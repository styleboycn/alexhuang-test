package com.alexhuang.patterndesign;

import com.alexhuang.patterndesign.decorator.ConcreteComponent;
import com.alexhuang.patterndesign.decorator.ConcreteDecoratorA;
import com.alexhuang.patterndesign.decorator.IComponent;
import com.alexhuang.patterndesign.decorator.other.BasicRequirement;
import com.alexhuang.patterndesign.decorator.other.IRequirement;
import com.alexhuang.patterndesign.decorator.other.RequirementExt;

/**
 * 装饰模式和代理模式很相似
 * 《JAVA与模式》之装饰模式
	https://www.cnblogs.com/java-my-life/archive/2012/04/20/2455726.html
	JUnit源码分析(四)——从Decorator模式说起
	http://www.blogjava.net/killme2008/archive/2007/04/06/108894.html
 *
 */
public class DecoratorTest {

	public static void main(String[] args) {
		componentTest();
//		requirementTest();
	}
	
	public static void componentTest() {
		IComponent concreteComp = new ConcreteComponent();
		ConcreteDecoratorA decoratorA = new ConcreteDecoratorA(concreteComp);
		decoratorA.sampleOperation();
	}
	
	public static void requirementTest() {
		IRequirement reqInf = new BasicRequirement();
		IRequirement req = new RequirementExt(reqInf);
		req.developRequirement();
	}

}
