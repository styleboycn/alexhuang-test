package com.alexhuang.patterndesign.prototype;

public class ConcretePrototype2 implements IPrototype {

	public IPrototype clone() {
		// 最简单的克隆，新建一个自身对象，由于没有属性就不再复制值了
		IPrototype prototype = new ConcretePrototype2();
		return prototype;
	}

}