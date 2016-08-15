package com.alexhuang.common_pool.connectionpool;

import java.net.Socket;

import org.apache.commons.pool.impl.GenericObjectPool;

public class ConnectionPoolFactory {
	private GenericObjectPool pool;

	public ConnectionPoolFactory(GenericObjectPool.Config config, String ip,
			int port) {
		ConnectionFactory factory = new ConnectionFactory(ip, port);
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
}
