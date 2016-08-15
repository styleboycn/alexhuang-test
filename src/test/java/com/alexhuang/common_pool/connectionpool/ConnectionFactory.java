package com.alexhuang.common_pool.connectionpool;

import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.commons.pool.BasePoolableObjectFactory;

public class ConnectionFactory extends BasePoolableObjectFactory {

	private InetSocketAddress address;

	public ConnectionFactory(String ip, int port) {
		address = new InetSocketAddress(ip, port);
	}

	@Override
	public Object makeObject() throws Exception {
		Socket socket = new Socket();
		socket.connect(address);
		return socket;
	}

	public void destroyObject(Object obj) throws Exception {
		if (obj instanceof Socket) {
			((Socket) obj).close();
		}
	}

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
