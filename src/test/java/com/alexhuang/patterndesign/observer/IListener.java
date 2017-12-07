package com.alexhuang.patterndesign.observer;

//IListener->观察者接口，实现这个接口的对象，都可以被加入到主题的观察者列表中
public interface IListener {
	
	public void update(ISubject subject);
	
}
