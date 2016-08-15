package com.alexhuang.common_pool;

public class MyConnection {  
      
    private String name;  
    private boolean connected;  
  
    public MyConnection(String name) {  
        this.name = name;  
    }  
  
    public void connect() {  
        this.connected = true;  
        MyLogger.info("execute MyConnection connect(), this object : " + this + ", object name ： " + this.name);
        MyLogger.info("\n\n");
    }  
  
    public void close() {  
        this.connected = false;
        MyLogger.info("execute MyConnection connect(), this close : " + this + ", object name ： " + this.name);
        MyLogger.info("\n\n");
    }  
  
    public boolean isConnected() {  
        return this.connected;  
    }  
      
    public String getName() {  
        return this.name;  
    }  
      
    public void print() {  
        MyLogger.info("execute MyConnection connect(), this object : " + this + ", object name ： " + this.name);  
    }  
}  