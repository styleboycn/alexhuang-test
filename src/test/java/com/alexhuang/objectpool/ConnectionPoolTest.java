package com.alexhuang.objectpool;

import java.net.Socket;

import org.apache.commons.pool.impl.GenericObjectPool.Config;

public class ConnectionPoolTest {

	public static void main(String[] args) throws Exception {
		
		Config config = new Config();
		config.maxActive = 16;
		config.maxWait = 30000;
		ConnectionPoolFactory poolFactory = new ConnectionPoolFactory(config,
				"127.0.0.1", 6942);
		Socket socket1 = null;
		Socket socket2 = null;
		Socket socket3 = null;
		try {
			System.out.println(poolFactory.getNumActive() + "##" + poolFactory.getNumIdle());
			socket1 = poolFactory.getConnection();
			System.out.println(poolFactory.getNumActive() + "##" + poolFactory.getNumIdle());
			socket2 = poolFactory.getConnection();
			System.out.println(poolFactory.getNumActive() + "##" + poolFactory.getNumIdle());
			System.out.println("socket1 : " + socket1);
			System.out.println("socket2 : " + socket2);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(poolFactory.getNumActive() + "##" + poolFactory.getNumIdle());
			if (socket1 != null) {
				poolFactory.releaseConnection(socket1);
			}
			System.out.println(poolFactory.getNumActive() + "##" + poolFactory.getNumIdle());
			if (socket2 != null) {
				poolFactory.releaseConnection(socket2);
			}
			System.out.println(poolFactory.getNumActive() + "##" + poolFactory.getNumIdle());
			socket3 = poolFactory.getConnection();
			System.out.println(poolFactory.getNumActive() + "####" + poolFactory.getNumIdle());
		}

	}
	 
}
