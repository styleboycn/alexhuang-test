package com.alexhuang.proxy.dsproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class MyInvocationHandler implements InvocationHandler {
	
	@Autowired
	private DataSource originalDS;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("Thread id is " + Thread.currentThread().getId() + ", method is " + method.toString() + ", CityCode is "
				+ TestCityCodeHolder.get());
		
//		Map<String, DataSource> map = null;
//		DataSource targetDataSource = map.get(TestCityCodeHolder.get());
		DataSource targetDataSource = originalDS;

		return method.invoke(targetDataSource, args);
	}

}
