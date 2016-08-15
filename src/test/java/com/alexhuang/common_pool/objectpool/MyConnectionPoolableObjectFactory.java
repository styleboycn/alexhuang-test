package com.alexhuang.common_pool.objectpool;

import org.apache.commons.pool.PoolableObjectFactory;

import com.alexhuang.common_pool.MyConnection;
import com.alexhuang.common_pool.MyLogger;

public class MyConnectionPoolableObjectFactory implements PoolableObjectFactory {

	private static int count = 0;  
	
	public Object makeObject() throws Exception {
		MyConnection myConn = new MyConnection("conn_" + (++count));
		myConn.connect();
		MyLogger.info("execute makeObject(), object name : " + myConn.getName());
		return myConn;
	}

	public void activateObject(Object obj) throws Exception {
		MyConnection myConn = (MyConnection)obj;  
		MyLogger.info("execute activateObject(), object name : " + myConn.getName());
	}

	public boolean validateObject(Object obj) {
        MyConnection myConn = (MyConnection)obj;
        MyLogger.info("execute validateObject(), object name : " + myConn.getName());
        return myConn.isConnected(); 
	}
	
	public void destroyObject(Object obj) throws Exception {
        MyConnection myConn = (MyConnection)obj;  
        MyLogger.info("execute destroyObject(), object name : " + myConn.getName());
        myConn.close();  
	}
	
	public void passivateObject(Object obj) throws Exception {
		MyConnection myConn = (MyConnection)obj;  
		MyLogger.info("execute passivateObject(), object name : " + myConn.getName());	
	}

}
