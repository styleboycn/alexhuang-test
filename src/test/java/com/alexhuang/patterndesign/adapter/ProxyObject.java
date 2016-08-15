package com.alexhuang.patterndesign.adapter;

public class ProxyObject implements IRequirement {

	private IRequirement realObject = new BasicRequirement();

	public void developRequirement() {
		// TODO Auto-generated method stub
		
		System.out.println("before");
		realObject.developRequirement();
		System.out.println("after");
		
	}
	
	
	
}
