package com.alexhuang.patterndesign.prototype;

public interface IPrototype {
	
	/**
	 * 克隆自身的方法
	 * 
	 * @return 一个从自身克隆出来的对象
	 */
	public Object clone();
}
