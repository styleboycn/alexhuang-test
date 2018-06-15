package com.alexhuang.patterndesign;

import com.alexhuang.patterndesign.facade.ModuleFacade;

/**
 *	《JAVA与模式》之门面模式
	http://www.cnblogs.com/java-my-life/archive/2012/05/02/2478101.html
	
	门面模式是对象的结构模式，外部与一个子系统的通信必须通过一个统一的门面对象进行。
	门面模式提供一个高层次的接口，使得子系统更易于使用。
	
	门面模式在Tomcat中的使用
	Request对象中的很多方法都是内部组件之间相互交互时使用的，比如setComet、setRequestedSessionId等方法。
	这些方法并不对外部公开，但是又必须设置为public，因为还需要跟内部组件之间交互使用。最好的解决方法就是通过使用一个Facade类，
	将与内部组件之间交互使用的方法屏蔽掉，只提供给外部程序感兴趣的方法。
	如果不使用Facade类，直接传递的是Request对象和Response对象，
	那么熟悉容器内部运作的程序员可以分别把ServletRequest和ServletResponse对象向下转换为Request和Response，并调用它们的公共方法。
	比如拥有Request对象，就可以调用setComet、setRequestedSessionId等方法，这会危害安全性。
 *
 */
public class FacadeTest {

	public static void main(String[] args) {
		
		ModuleFacade facade = new ModuleFacade();
		facade.a1();
		facade.b1();
		facade.c1();
    }
	
}
