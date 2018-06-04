package com.alexhuang.patterndesign.adapter.objectadapter;

//对象适配器
public class ObjectAdapter implements IObjectTargetInf {
	
	private ObjectAdaptee adaptee;
	
	public ObjectAdapter(ObjectAdaptee adaptee){
        this.adaptee = adaptee;
	}
	
	public void sampleOperation1(){
        this.adaptee.sampleOperation1();
    }

	@Override
	public void sampleOperation2() {
		System.out.println("this is ObjectAdapter->sampleOperation2()");
	}
	
}
