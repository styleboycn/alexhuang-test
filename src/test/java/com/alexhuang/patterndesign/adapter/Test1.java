package com.alexhuang.patterndesign.adapter;

/**
 * 装饰模式和代理模式很相似
 *
 */

public class Test1 {

	public static void main(String[] args) {
		
		IRequirement reqInf = ProxyObject.getProxyObject();
		reqInf.developRequirement();
		
	}
	

}
