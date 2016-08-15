package com.alexhuang.patterndesign.observer;

public class Test1 {

	public static void main(String[] args) {
		ISubject subject = new SnookerSubject();
		subject.attach(new WhiteHuman());
		subject.attach(new YellowHuman());
		subject.setMatcInfo("o'sulliven and ding ^^ ");
		subject.notifyObserver();
	}

}
