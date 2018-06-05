package com.alexhuang.patterndesign;

import com.alexhuang.patterndesign.observer.ISubject;
import com.alexhuang.patterndesign.observer.SnookerSubject;
import com.alexhuang.patterndesign.observer.WhiteHuman;
import com.alexhuang.patterndesign.observer.YellowHuman;

/**
 * Observer是对象行为型模式之一
 * 1、观察者模式定义了一种一对多的依赖关系，让多个观察者对象同时监听某一个主题对象。
 * 2、这个主题对象在状态上发生变化时，会通知所有观察者对象，让它们能够自动更新自己。
 * 3、此模块通常包括四个部分：抽象主题角色、抽象观察者角色、具体主题角色、具体观察者角色。
 * 《JAVA与模式》之观察者模式
 * https://www.cnblogs.com/java-my-life/archive/2012/05/16/2502279.html
 */

public class ObserverTest {

	public static void main(String[] args) {
		
		ISubject subject = new SnookerSubject();
		subject.attach(new WhiteHuman());
		subject.attach(new YellowHuman());
		
		subject.setMatcInfo("o'sulliven and ding is flighting !!! ");
		subject.matchStart();
		System.out.println("");
		
		subject.setMatcInfo("o'sulliven and ding game over !!! ");
		subject.matchStop();
		
	}

}
