package com.pool;

import java.sql.Connection;

import com.pool.baseface.IConnectionPool;

/** 
 * ģ���߳�������ȥ������� 
 * @author Ran 
 * 
 */  
public class ThreadConnection implements Runnable{  
    private IConnectionPool pool;  
    
    
    public void run() {  
        pool = ConnectionPoolManager.getInstance().getPool("testPool");  
    }  
      
    public Connection getConnection(){  
        Connection conn = null;  
        if(pool != null && pool.isActive()){  
            conn = pool.getConnection();  
        }  
        return conn;  
    }  
      
    public Connection getCurrentConnection(){  
        Connection conn = null;  
        if(pool != null && pool.isActive()){  
            conn = pool.getCurrentConnecton(); 
        }  
        return conn;  
    }  
}  
