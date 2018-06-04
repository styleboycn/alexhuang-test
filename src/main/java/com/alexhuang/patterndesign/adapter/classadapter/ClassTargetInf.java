package com.alexhuang.patterndesign.adapter.classadapter;

//目标角色/接口：这是所期待得到的接口。
public interface ClassTargetInf {
	
	/**
     * 这是源类Adaptee也有的方法
     */
    public void sampleOperation1(); 
    /**
     * 这是源类Adaptee没有的方法
     */
    public void sampleOperation2(); 

}
