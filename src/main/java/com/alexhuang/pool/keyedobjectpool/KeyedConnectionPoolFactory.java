package com.alexhuang.pool.keyedobjectpool;

import com.alexhuang.pool.objectpool.ConnectionFactory;
import org.apache.commons.pool.KeyedPoolableObjectFactory;
import org.apache.commons.pool.impl.GenericKeyedObjectPool;

import java.net.Socket;

public class KeyedConnectionPoolFactory {
	private GenericKeyedObjectPool pool;

	public KeyedConnectionPoolFactory(GenericKeyedObjectPool.Config config, String ip,
									  int port) {
	    //创建对象工厂
		KeyedConnectionFactory factory = new KeyedConnectionFactory(ip, port);
		//使用工厂对象(factory)作为参数，创建对象池(pool)
		pool = new GenericKeyedObjectPool(factory, config);
	}

	public Socket getConnection(Object key) throws Exception {
		return (Socket) pool.borrowObject(key);
	}

	public void releaseConnection(String key, Socket socket) {
		if (socket != null) {
			try {
				pool.returnObject(key, socket);
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
