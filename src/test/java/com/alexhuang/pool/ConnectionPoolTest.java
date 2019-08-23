package com.alexhuang.pool;

import java.net.Socket;

import com.alexhuang.pool.objectpool.ConnectionPoolFactory;
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
			System.out.println("######################");
			System.out.println("##in execution phase##");
			System.out.println("######################");
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
			System.out.println("####################");
			System.out.println("##in finally phase##");
			System.out.println("####################");

			poolFactory.releaseConnection(socket1);
			System.out.println(poolFactory.getNumActive() + "##" + poolFactory.getNumIdle());

			poolFactory.releaseConnection(socket2);
			System.out.println(poolFactory.getNumActive() + "##" + poolFactory.getNumIdle());

			//socket3 = poolFactory.getConnection();
			//释放对象为空，如果在releaseConnection未处理好，会导致pool状态异常
			poolFactory.releaseConnection(socket3);
			System.out.println(poolFactory.getNumActive() + "####" + poolFactory.getNumIdle());
		}
	}
}
