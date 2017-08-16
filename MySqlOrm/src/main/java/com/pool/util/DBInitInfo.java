package com.pool.util;

import java.util.ArrayList;
import java.util.List;

import com.pool.ConnectionPoolManager;
import com.pool.DBbean;
import com.pool.ThreadConnection;
import com.pool.baseface.IConnectionPool;

/** 
 * 初始化，模拟加载所有的配置文件 
 */
public class DBInitInfo {
	 public  static List<DBbean>  beans = null;  
	  static{  
	        beans = new ArrayList<DBbean>();  
	        // 这里数据 可以从xml 等配置文件进行获取  
	        // 为了测试，这里我直接写死  
	        DBbean beanOracle = new DBbean();  
	        beanOracle.setDriverName("com.mysql.jdbc.Driver");  
	        beanOracle.setUrl("jdbc:mysql://127.0.0.1:3306/xs");  
	        beanOracle.setUserName("root");  
	        beanOracle.setPassword("lhb123");  
	        beanOracle.setMinConnections(5);  
	        beanOracle.setMaxConnections(100);  
	        beanOracle.setPoolName("testPool");  
	        beans.add(beanOracle);  
	    }  
	  
	  // 初始化  
	    public static Thread init() {  
	        Thread t = new Thread(new Runnable() {  
	            public void run() {  
	                IConnectionPool  pool = initPool();  
	                while(pool == null || !pool.isActive()){  
	                    pool = initPool();  
	                }  
	            }  
	        });  
	        return t;  
	    }  
	      
	    public static IConnectionPool initPool(){  
	        return ConnectionPoolManager.getInstance().getPool("testPool");  
	    }  
	    
		
		public static void main(String[] args) {
			Thread t = init();  
	        t.start();  
	        try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ThreadConnection a = new ThreadConnection();
			Thread t1 = new Thread(a);
			t1.setPriority(10);
			t1.start();
			
			a.getCurrentConnection();
		}
}
