package com.alexhuang.patterndesign.observer;

//YellowHuman->具体的观察者，它可以实时地获取到主题的更新信息
public class YellowHuman implements IHuman, IListener {

	public void update(ISubject subject) {
		// TODO Auto-generated method stub
		String matchInfo = subject.getMatchInfo();
		System.out.println("YellowHuman watch this match : " + matchInfo);
	}

	public void work() {
		// TODO Auto-generated method stub
	}

	public void study() {
		// TODO Auto-generated method stub
	}
	
}
