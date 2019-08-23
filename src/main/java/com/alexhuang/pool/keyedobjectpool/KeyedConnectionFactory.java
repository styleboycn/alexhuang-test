package com.alexhuang.pool.keyedobjectpool;

import org.apache.commons.pool.BaseKeyedPoolableObjectFactory;
import org.apache.commons.pool.BasePoolableObjectFactory;

import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 这个类是用来创建基于Key的对象工厂
 * @author Alex Huang
 */
public class KeyedConnectionFactory extends BaseKeyedPoolableObjectFactory {

	private InetSocketAddress address;

	public KeyedConnectionFactory(String ip, int port) {
		//初始化内部变量
		address = new InetSocketAddress(ip, port);
	}

	@Override
	public Object makeObject(Object key) throws Exception {
		//在这个方法根据不同的key，生成不同的object
		Socket socket = new Socket();
		socket.connect(address);
		socket.setSendBufferSize(Integer.valueOf((String)key));
		return socket;
	}

	@Override
	public void destroyObject(Object key, Object obj) throws Exception {
		//根据key的不同，用不同的销毁方式
		if (obj instanceof Socket) {
			((Socket) obj).close();
		}
	}

	@Override
	public boolean validateObject(Object key, Object obj) {
		if (obj instanceof Socket) {
			Socket socket = ((Socket) obj);
			if (!socket.isConnected()) {
				return false;
			}
			if (socket.isClosed()) {
				return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public void passivateObject(Object key, Object obj)
			throws Exception {
		//do nothing
	}

	@Override
	public void activateObject(Object key, Object obj)
			throws Exception {
		//do nothing
	}

}
