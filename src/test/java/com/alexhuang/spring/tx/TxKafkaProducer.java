package com.alexhuang.spring.tx;


public class TxKafkaProducer implements ITransaction {

    private static ThreadLocal<String> holder = new ThreadLocal<String>();
    
    public void send(String msg) {
    	holder.set(msg);
    }
	
	@Override
	public void commit() {
		// TODO Auto-generated method stub
		String threadLocalString = holder.get();
		try {
			System.out.println("TxKafkaProducer commit, data is " + threadLocalString + "\n");
		} finally {
			holder.remove();
		}
	}

	@Override
	public void rollback() {
		// TODO Auto-generated method stub
		System.out.println("TxKafkaProducer will rollback");
		holder.remove();
	}

}
