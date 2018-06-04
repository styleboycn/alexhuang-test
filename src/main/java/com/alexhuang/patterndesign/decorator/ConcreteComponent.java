package com.alexhuang.patterndesign.decorator;

//具体构件角色
public class ConcreteComponent implements IComponent {

    @Override
    public void sampleOperation() {
        // 写相关的业务代码
    	System.out.println("ConcreteComponent->sampleOperation()");
    }

}