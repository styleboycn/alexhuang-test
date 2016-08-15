package com.alexhuang.spring.factorybean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class CarProxy implements FactoryBean<ICar>, InitializingBean, InvocationHandler {
	
	/* 代理的目标接口 */
	private Class<?> serviceInterface;
	/* 生成的代理对象 */
	private ICar proxyObject;
	/* 真实的客户端代理 */
	private volatile ICar client;
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)  throws Throwable {
		return method.invoke(client, args);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (serviceInterface == MazdaCar.class) {
			client = new MazdaCar("maliu", 168000);
		} else {
			client = new AudiCar("audiA6", 328000);
		}
		proxyObject = (ICar) Proxy.newProxyInstance(
				getClass().getClassLoader(), new Class[] { ICar.class }, this);
	}

	@Override
	public ICar getObject() throws Exception {
		// TODO Auto-generated method stub
		return proxyObject;
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return serviceInterface;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

	public void setServiceInterface(Class<?> serviceInterface) {
		this.serviceInterface = serviceInterface;
	}
}
