package com.alexhuang.proxy.jdkproxy;

//JDK动态代理实现原理
//http://rejoy.iteye.com/blog/1627405
public class ProxyTest {

	public static void main(String[] args) {
		
		UserService userService = new UserServiceImpl();
		
		MyInvocationHandler myHandler = new MyInvocationHandler(userService);
		
		UserService userServiceProxy = (UserService) myHandler.getProxy();
		
		userServiceProxy.add();
	}

}
