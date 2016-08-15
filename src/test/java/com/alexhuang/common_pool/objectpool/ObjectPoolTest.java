package com.alexhuang.common_pool.objectpool;

import org.apache.commons.pool.PoolableObjectFactory;
import org.apache.commons.pool.impl.StackObjectPool;

import com.alexhuang.common_pool.MyConnection;
import com.alexhuang.common_pool.MyLogger;

public class ObjectPoolTest {

	 public static void main(String[] args) throws Exception {  
	        PoolableObjectFactory factory = new MyConnectionPoolableObjectFactory();  
	        StackObjectPool pool = new StackObjectPool(factory);  
	        try {
	            for (int i = 0; i < 2; i++) {
	            	MyLogger.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"); 
	                MyConnection myConn = (MyConnection)pool.borrowObject();  
	                try {  
	                    myConn.print();
	                } catch(Exception ex) {  
	                    pool.invalidateObject(myConn);  
	                } finally {  
	                    pool.returnObject(myConn);  
	                }
	                MyLogger.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	                MyLogger.info("\n\n");
	            }  
	            
	            
	            MyLogger.info("================================================");  
	            for (int i = 0; i < 2; i++) {  
	                MyConnection myConn1 = (MyConnection)pool.borrowObject();  
	                MyConnection myConn2 = (MyConnection)pool.borrowObject();  
	                myConn1.print();  
	                myConn2.print();  
	                pool.returnObject(myConn1);  
	                pool.returnObject(myConn2);  
	            }  
	        } finally {  
	            try {  
	                pool.close();  
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        } 
	 }
}
