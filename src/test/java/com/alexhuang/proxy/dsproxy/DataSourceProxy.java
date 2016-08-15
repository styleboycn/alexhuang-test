package com.alexhuang.proxy.dsproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.sql.DataSource;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("dataSource")
public class DataSourceProxy implements FactoryBean<DataSource>,
		InitializingBean, InvocationHandler {

	private DataSource proxyObject;

	@Autowired
	private DataSource originalDS;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("Thread id is " + Thread.currentThread().getId()
				+ ", method is " + method.toString() + ", CityCode is "
				+ TestCityCodeHolder.get());
		// Map<String, DataSource> map = null;
		// DataSource targetDataSource = map.get(TestCityCodeHolder.get());
		DataSource targetDataSource = originalDS;

		return method.invoke(targetDataSource, args);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		proxyObject = (DataSource) Proxy.newProxyInstance(getClass()
				.getClassLoader(), new Class<?>[] { DataSource.class }, this);
	}

	@Override
	public DataSource getObject() throws Exception {
		return proxyObject;
	}

	@Override
	public Class<?> getObjectType() {
		return DataSource.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
