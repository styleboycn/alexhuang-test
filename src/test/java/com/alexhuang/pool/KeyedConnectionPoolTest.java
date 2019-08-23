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
		//testOnReturn如果为fales，则在returnObject时不会做对象合法性校验
		config.testOnReturn = true;
		KeyedConnectionPoolFactory poolFactory = new KeyedConnectionPoolFactory(config,
				"127.0.0.1", 6942);
		Socket socket1 = null;
		Socket socket2 = null;
		Socket socket3 = null;
		Socket socket4 = null;
		Socket socket5 = null;
		Socket socket6 = null;
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
			System.out.println("socket5 : " + socket4 + ",type : " + socket5.getSendBufferSize());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("####################");
			System.out.println("##in finally phase##");
			System.out.println("####################");

			poolFactory.releaseConnection(TCP_SOCKET, socket1);
			System.out.println(poolFactory.getNumActive() + "##" + poolFactory.getNumIdle());

			poolFactory.releaseConnection(TCP_SOCKET, socket2);
			System.out.println(poolFactory.getNumActive() + "##" + poolFactory.getNumIdle());

			poolFactory.releaseConnection(UDP_SOCKET, socket3);
			System.out.println(poolFactory.getNumActive() + "####" + poolFactory.getNumIdle());

			//使用请注意：释放一个对象到不存在的key，GenericKeyedObjectPool会创建这个key对象的存储对列(LinkedList)
			poolFactory.releaseConnection("666", socket3);
			System.out.println(poolFactory.getNumActive() + "####" + poolFactory.getNumIdle());
		}

	}
	 
}
