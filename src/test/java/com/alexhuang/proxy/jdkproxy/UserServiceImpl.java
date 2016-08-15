package com.alexhuang.proxy.jdkproxy;


public class UserServiceImpl implements UserService {

	@Override
	public void add() {
		System.out.println("UserServiceImpl -> add executed !");
	}

}
