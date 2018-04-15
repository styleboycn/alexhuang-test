package com.alexhuang.patterndesign;

import com.alexhuang.patterndesign.decorator.BasicRequirement;
import com.alexhuang.patterndesign.decorator.IRequirement;
import com.alexhuang.patterndesign.decorator.RequirementExt;

/**
 * 装饰模式和代理模式很相似
 *
 */

public class TestDecorator {

	public static void main(String[] args) {
		IRequirement reqInf = new BasicRequirement();
		IRequirement req = new RequirementExt(reqInf);
		req.developRequirement();
	}

}
