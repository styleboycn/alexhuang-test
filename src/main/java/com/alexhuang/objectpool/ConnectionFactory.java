package com.alexhuang.objectpool;

import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.commons.pool.BasePoolableObjectFactory;

public class ConnectionFactory extends BasePoolableObjectFactory {

	private InetSocketAddress address;

	public ConnectionFactory(String ip, int port) {
		// 初始化内部变量
		address = new InetSocketAddress(ip, port);
	}

	@Override
	public Object makeObject() throws Exception {
		Socket socket = new Socket();
		socket.connect(address);
		return socket;
	}

	@Override
	public void destroyObject(Object obj) throws Exception {
		if (obj instanceof Socket) {
			((Socket) obj).close();
		}
	}

	@Override
	public boolean validateObject(Object obj) {
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

}
