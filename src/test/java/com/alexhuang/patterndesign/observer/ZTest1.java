package com.alexhuang.patterndesign.observer;

//JUnit源码分析（二）——观察者模式
//http://www.blogjava.net/killme2008/archive/2007/04/05/108743.html

//Observer是对象行为型模式之一
//意图：定义对象间的一种一对多的依赖关系，当一个对象的状态发现改变时，所有依赖于它的对象都得到通知并被自动更新
//适用场景：
//1）当一个抽象模型有两个方面，其中一个方面依赖于另一个方面，通过观察者模式将这两者封装在不同的独立对象当中，以使它们可以独立的变化和复用
//2）当一个对象改变时，需要同时改变其他对象，并且不知道其他对象的具体数目
//3）当一个对象需要引用其他对象，但是你又不想让这个对象与其他对象产生紧耦合的时候
public class ZTest1 {

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
