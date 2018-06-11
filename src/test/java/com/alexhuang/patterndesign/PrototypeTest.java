package com.alexhuang.patterndesign;

import com.alexhuang.patterndesign.prototype.ConcretePrototype1;
import com.alexhuang.patterndesign.prototype.IPrototype;

/**
	原型模式最主要的缺点是每一个类都必须配备一个克隆方法。配备克隆方法需要对类的功能进行通盘考虑，这对于全新的类来说不是很难，
	而对于已经有的类不一定很容易，特别是当一个类引用不支持序列化的间接对象，或者引用含有循环结构的时候。
	《JAVA与模式》之原型模式
	http://www.cnblogs.com/java-my-life/archive/2012/04/11/2439387.html
 */
public class PrototypeTest {

	public static void main(String[] args) {
		IPrototype prototype = new ConcretePrototype1().clone();
		System.out.println(prototype.getClass());
	}

}
