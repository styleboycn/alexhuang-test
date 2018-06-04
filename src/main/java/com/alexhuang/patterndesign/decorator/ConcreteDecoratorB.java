package com.alexhuang.patterndesign.decorator;

//具体装饰角色
public class ConcreteDecoratorB extends Decorator {

	public ConcreteDecoratorB(IComponent component) {
		super(component);
	}

	@Override
    public void sampleOperation() {
		super.sampleOperation();
        // 写相关的业务代码
		System.out.println("ConcreteDecoratorB->sampleOperation()");
    }
}