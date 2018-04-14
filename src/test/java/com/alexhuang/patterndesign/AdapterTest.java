package com.alexhuang.patterndesign;

import com.alexhuang.patterndesign.adapter.IRequirement;
import com.alexhuang.patterndesign.adapter.ProxyObject;

/**
 * 装饰模式和代理模式很相似
 *
 */

public class AdapterTest {

	public static void main(String[] args) {
		
		IRequirement reqInf = ProxyObject.getProxyObject();
		reqInf.developRequirement();
		
	}
	

}
