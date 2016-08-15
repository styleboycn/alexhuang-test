package com.alexhuang.patterndesign.observer;

public class WhiteHuman implements IHuman, IListener {

	public void update(ISubject subject) {
		String matchInfo = subject.getMatchInfo();
		System.out.println("WhiteHuman watch this match : " + matchInfo);
	}

	public void work() {
		// TODO Auto-generated method stub
		
	}

	public void study() {
		// TODO Auto-generated method stub
		
	}
	
	
}
