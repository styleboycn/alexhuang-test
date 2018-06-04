package com.alexhuang.patterndesign.decorator;

//具体装饰角色
public class ConcreteDecoratorA extends Decorator {

	public ConcreteDecoratorA(IComponent component) {
		super(component);
	}

	@Override
    public void sampleOperation() {
		super.sampleOperation();
        // 写相关的业务代码
		System.out.println("ConcreteDecoratorB->sampleOperation()");
    }
}