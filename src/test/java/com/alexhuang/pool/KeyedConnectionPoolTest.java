package com.alexhuang.pool;

import com.alexhuang.pool.keyedobjectpool.KeyedConnectionPoolFactory;
import org.apache.commons.pool.impl.GenericKeyedObjectPool.Config;

import java.net.Socket;

public class KeyedConnectionPoolTest {

	private static String TCP_SOCKET = "1";
	private static String UDP_SOCKET = "2";

	public static void main(String[] args) throws Exception {
		
		Config config = new Config();
		config.maxActive = 16;
		config.maxWait = 30000;
		KeyedConnectionPoolFactory poolFactory = new KeyedConnectionPoolFactory(config,
				"127.0.0.1", 6942);
		Socket socket1 = null;
		Socket socket2 = null;
		Socket socket3 = null;
		Socket socket4 = null;
		Socket socket5 = null;
		try {
			System.out.println("######################");
			System.out.println("##in execution phase##");
			System.out.println("######################");
			socket1 = poolFactory.getConnection(TCP_SOCKET);
			System.out.println(poolFactory.getNumActive() + "##" + poolFactory.getNumIdle());
			socket2 = poolFactory.getConnection(TCP_SOCKET);
			System.out.println(poolFactory.getNumActive() + "##" + poolFactory.getNumIdle());
			socket3 = poolFactory.getConnection(UDP_SOCKET);
			System.out.println(poolFactory.getNumActive() + "##" + poolFactory.getNumIdle());
			socket4 = poolFactory.getConnection(UDP_SOCKET);
			System.out.println(poolFactory.getNumActive() + "##" + poolFactory.getNumIdle());
			socket5 = poolFactory.getConnection(UDP_SOCKET);
			System.out.println(poolFactory.getNumActive() + "##" + poolFactory.getNumIdle());
			System.out.println("socket1 : " + socket1 + ",type : " + socket1.getSendBufferSize());
			System.out.println("socket2 : " + socket2 + ",type : " + socket2.getSendBufferSize());
			System.out.println("socket3 : " + socket3 + ",type : " + socket3.getSendBufferSize());
			System.out.println("socket4 : " + socket4 + ",type : " + socket4.getSendBufferSize());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("####################");
			System.out.println("##in finally phase##");
			System.out.println("####################");
			if (socket1 != null) {
				poolFactory.releaseConnection(TCP_SOCKET, socket1);
			}
			System.out.println(poolFactory.getNumActive() + "##" + poolFactory.getNumIdle());
			if (socket2 != null) {
				poolFactory.releaseConnection(TCP_SOCKET, socket2);
			}
			System.out.println(poolFactory.getNumActive() + "##" + poolFactory.getNumIdle());
			if (socket3 != null) {
				poolFactory.releaseConnection("666", socket5);
			}
			System.out.println(poolFactory.getNumActive() + "###" + poolFactory.getNumIdle());
			if (socket4 != null) {
				poolFactory.releaseConnection("666", socket5);
			}
			System.out.println(poolFactory.getNumActive() + "###" + poolFactory.getNumIdle());
		}

	}
	 
}
