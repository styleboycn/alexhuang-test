package com.alexhuang.patterndesign;

import com.alexhuang.patterndesign.proxy.AbstractObject;
import com.alexhuang.patterndesign.proxy.ProxyObject;

/**
	所谓代理，就是一个人或者机构代表另一个人或者机构采取行动。
	在一些情况下，一个客户不想或者不能够直接引用一个对象，而代理对象可以在客户端和目标对象之间起到中介的作用。
	在代理模式中的角色：
	1、抽象对象角色
	2、目标对象角色
	3、代理对象角色
	《JAVA与模式》之代理模式
	http://www.cnblogs.com/java-my-life/archive/2012/04/23/2466712.html
 */

public class ProxyTest {

	public static void main(String[] args) {
		
		AbstractObject obj = new ProxyObject();
		obj.operation();
	}

}
