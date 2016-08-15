package com.alexhuang.patterndesign.observer;

public interface ISubject {
	
	public void attach(IListener observer);
	
	public void deattach(IListener observer);
	
	public void notifyObserver();
	
	public String getMatchInfo();
	
	public void setMatcInfo(String matchInfo);
}
