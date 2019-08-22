package com.alexhuang.objectpool;

import java.net.Socket;

import org.apache.commons.pool.impl.GenericObjectPool;

public class ConnectionPoolFactory {
	private GenericObjectPool pool;

	public ConnectionPoolFactory(GenericObjectPool.Config config, String ip,
			int port) {
	    //创建对象工厂
		ConnectionFactory factory = new ConnectionFactory(ip, port);
		//使用工厂对象(factory)作为参数，创建对象池(pool)
		pool = new GenericObjectPool(factory, config);
	}

	public Socket getConnection() throws Exception {
		return (Socket) pool.borrowObject();
	}

	public void releaseConnection(Socket socket) {
		try {
			pool.returnObject(socket);
		} catch (Exception e) {
			if (socket != null) {
				try {
					socket.close();
				} catch (Exception ex) {
					//
				}
			}
		}
	}

	public int getNumActive() {
		if (pool != null)
			return pool.getNumActive();
		return -1;
	}

	public int getNumIdle() {
		if (pool != null)
			return pool.getNumIdle();
		return -1;
	}
}
