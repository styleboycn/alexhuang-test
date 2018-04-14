package com.alexhuang.patterndesign.observer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

//SnookerSubject->具体的主题，它的某些行为会触发通知给观察者，比如matchStart()、matchStop()
public class SnookerSubject implements ISubject {
	
	//observerList->主题维护的一个观察者列表
	private final List<IListener> observerList = new CopyOnWriteArrayList<IListener>();
	//matchInfo->主题内部属性或状态
	private String matchInfo;

	public void attach(IListener observer) {
		if (observer != null) {
			observerList.add(observer);
		}
	}

	public void deattach(IListener observer) {
		if (observer != null) {
			observerList.remove(observer);
		}
	}

	public String getMatchInfo() {
		return this.matchInfo;
	}

	public void setMatcInfo(String matchInfo) {
		this.matchInfo = matchInfo;
	}

	private void notifyObserver() {
		observerList.stream().forEach(obj -> obj.update(this));
	}
	
	@Override
	public void matchStart() {
		System.out.println("Snooker had started !!!");
		this.notifyObserver();
	}

	@Override
	public void matchStop() {
		System.out.println("Snooker had stopped !!!");
		this.notifyObserver();
	}

}
