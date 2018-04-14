package com.alexhuang.patterndesign.adapter;

public class ProxyObject implements IRequirement {
	
	private ProxyObject() {};

	private static IRequirement eatRealObject;
	private static IRequirement playRealObject;
	
	public static IRequirement getProxyObject() {
		if (eatRealObject == null)  {
			eatRealObject = new EatBasicRequirement();
		}
		if (eatRealObject == null)  {
			eatRealObject = new EatBasicRequirement();
		}
		return eatRealObject;
	}

	@Override
	public void developRequirement() {
		// TODO Auto-generated method stub
		eatRealObject.developRequirement();
	}

//	public void developRequirement() {
//		// TODO Auto-generated method stub
//		
//		System.out.println("before");
//		realObject.developRequirement();
//		System.out.println("after");
//		
//	}
	
	
	
	
}
