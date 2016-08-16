package com.alexhuang.proxy.dsproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.sql.DataSource;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//dataSource（@Component("dataSource")）作为sessionFactory的一个属性
//dataSource是通过spring的控制反转机制由spring的FactoryBean接口实现构造，getObject()时返回代理对象（DataSource的代理）
//代理对象通过InvocationHandler和反射机制，最终调用物理真实的datasource数据源完成操作

@Component("dataSource")
public class DataSourceProxy implements FactoryBean<DataSource>,
		InitializingBean, InvocationHandler {

	private DataSource proxyObject;

	@Autowired
	private DataSource originalDS;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("[DataSourceProxy.invoke]Thread id is "
				+ Thread.currentThread().getId() + ", method is "
				+ method.toString() + ", CityCode is "
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
