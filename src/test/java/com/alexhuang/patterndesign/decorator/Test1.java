package com.alexhuang.patterndesign.decorator;

/**
 * 装饰模式和代理模式很相似
 *
 */

public class Test1 {

	public static void main(String[] args) {
		IRequirement reqInf = new BasicRequirement();
		IRequirement req = new RequirementExt(reqInf);
		req.developRequirement();
	}

}
