package com.alexhuang.patterndesign.decorator;

//装饰角色
public class Decorator implements IComponent {
	private IComponent component;

	public Decorator(IComponent component) {
		this.component = component;
	}

	@Override
	public void sampleOperation() {
		// 委派给构件
		component.sampleOperation();
	}

}