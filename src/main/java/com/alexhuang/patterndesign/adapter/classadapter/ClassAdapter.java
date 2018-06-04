package com.alexhuang.patterndesign.adapter.classadapter;

//类适配器
public class ClassAdapter extends ClassAdaptee implements ClassTargetInf {

	@Override
	public void sampleOperation2() {
		System.out.println("this is ClassAdapter->sampleOperation2()");
	}
	
}
