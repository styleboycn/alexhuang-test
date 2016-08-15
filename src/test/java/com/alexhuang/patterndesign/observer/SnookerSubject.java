package com.alexhuang.patterndesign.observer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SnookerSubject implements ISubject {
	
	private final List<IListener> observerList = new CopyOnWriteArrayList<IListener>();
	private String matchInfo;

	public void attach(IListener observer) {
		// TODO Auto-generated method stub
		if (observer != null) {
			observerList.add(observer);
		}
	}

	public void deattach(IListener observer) {
		// TODO Auto-generated method stub
		if (observer != null) {
			observerList.remove(observer);
		}
	}
	
	public static void dosomething(IListener observer) {
		System.out.println(observer.toString());
	}
	
	public void notifyObserver() {
		// TODO Auto-generated method stub
//		for (IListener obj : observerList) {
//			obj.update(this);
//		}
		observerList.stream().forEach(obj -> obj.update(this));
//		observerList.stream().forEach(obj -> dosomething(obj));
	}

	public String getMatchInfo() {
		// TODO Auto-generated method stub
		return this.matchInfo;
	}

	public void setMatcInfo(String matchInfo) {
		// TODO Auto-generated method stub
		this.matchInfo = matchInfo;
	}

}
