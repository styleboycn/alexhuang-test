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
public class DataSourceProxy implements FactoryBean<DataSource>, InitializingBean {

	private DataSource proxyObject;
	
	@Autowired
	private MyInvocationHandler myInvocationHandler;

	@Override
	public void afterPropertiesSet() throws Exception {
		MyInvocationHandler handler = new MyInvocationHandler();
		proxyObject = (DataSource) Proxy.newProxyInstance(getClass().getClassLoader(), new Class<?>[] { DataSource.class }, myInvocationHandler);
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
