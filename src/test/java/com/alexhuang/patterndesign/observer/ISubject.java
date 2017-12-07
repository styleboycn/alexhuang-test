package com.alexhuang.patterndesign.observer;

//ISubject->主题接口，定义基本的监听对象增/删接口
public interface ISubject {
	
	public void attach(IListener observer);
	
	public void deattach(IListener observer);
	
	public String getMatchInfo();
	
	public void setMatcInfo(String matchInfo);
	
	public void matchStart();
	
	public void matchStop();
}
