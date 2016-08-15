package com.alexhuang.common_pool.connectionpool;

import java.net.Socket;

import org.apache.commons.pool.impl.GenericObjectPool.Config;

public class ConnectionPoolTest {

	public static void main(String[] args) throws Exception {
		
		Config config = new Config();
		config.maxActive = 16;
		config.maxWait = 30000;
		ConnectionPoolFactory poolFactory = new ConnectionPoolFactory(config,
				"127.0.0.1", 80);
		Socket socket1 = null;
		Socket socket2 = null;
		try {
			socket1 = poolFactory.getConnection();
			socket2 = poolFactory.getConnection();
			System.out.println("socket1 : " + socket1);
			System.out.println("socket2 : " + socket2);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (socket1 != null) {
				poolFactory.releaseConnection(socket1);
			}
			if (socket2 != null) {
				poolFactory.releaseConnection(socket2);
			}
		}

	}
	 
}
