package com.alexhuang.patterndesign.observer;

public class YellowHuman implements IHuman, IListener {

	public void work() {
		// TODO Auto-generated method stub

	}

	public void study() {
		// TODO Auto-generated method stub
		
	}

	public void update(ISubject subject) {
		// TODO Auto-generated method stub
		String matchInfo = subject.getMatchInfo();
		System.out.println("YellowHuman watch this match : " + matchInfo);
	}
	
	
}
