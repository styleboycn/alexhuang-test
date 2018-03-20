package com.alexhuang.spring.aware;

public class Market implements AppleAware {

	private Apple a;
	
	@Override
	public void setApple(Apple a) {
		// TODO Auto-generated method stub
		this.a = a;
	}
	
	public String getName() {
		return a.getName();
	}

	public int getSize() {
		return a.getSize();
	}
}
