package com.pool.baseface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import com.pool.DBbean;

public class ConnectionPool implements IConnectionPool {
	// ���ӳ���������  
    private DBbean dbBean;  
    private boolean isActive = false; // ���ӳػ״̬  
    private int contActive = 0;// ��¼�������ܵ�������  
      
    // ��������  
    private List<Connection> freeConnection = new Vector<Connection>();  
    // �����  
    private List<Connection> activeConnection = new Vector<Connection>();  
    // ���̺߳����Ӱ󶨣���֤������ͳһִ��  
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();  
      
    public ConnectionPool(DBbean dbBean) {  
        super();  
        this.dbBean = dbBean;  
        init();  
        cheackPool();  
    }  
  
    // ��ʼ��  
    public void init() {  
        try {  
            Class.forName(dbBean.getDriverName());  
            for (int i = 0; i < dbBean.getInitConnections(); i++) {  
                Connection conn;  
                conn = newConnection();  
                // ��ʼ����С������  
                if (conn != null) {  
                    freeConnection.add(conn);  
                    contActive++;  
                }  
            }  
            isActive = true;  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
      
    // ��õ�ǰ����  
    public Connection getCurrentConnecton(){  
        // Ĭ���߳�����ȡ  
        Connection conn = threadLocal.get();  
        if(!isValid(conn)){  
            conn = getConnection();  
        }  
        return conn;  
    }  
  
    // �������  
    public synchronized Connection getConnection() {  
        Connection conn = null;  
        try {  
            // �ж��Ƿ񳬹��������������  
            if(contActive < this.dbBean.getMaxActiveConnections()){  
                if (freeConnection.size() > 0) {  
                    conn = freeConnection.get(0);  
                    if (conn != null) {  
                        threadLocal.set(conn);  
                    }  
                    freeConnection.remove(0);  
                } else {  
                    conn = newConnection();  
                }  
                  
            }else{  
                // �����������,ֱ�����»������  
                wait(this.dbBean.getConnTimeOut());  
                conn = getConnection();  
            }  
            if (isValid(conn)) {  
                activeConnection.add(conn);  
                contActive ++;  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        return conn;  
    }  
  
    // ���������  
    private synchronized Connection newConnection()  
            throws ClassNotFoundException, SQLException {  
        Connection conn = null;  
        if (dbBean != null) {  
            Class.forName(dbBean.getDriverName());  
            conn = DriverManager.getConnection(dbBean.getUrl(),  
                    dbBean.getUserName(), dbBean.getPassword());  
        }  
        return conn;  
    }  
  
    // �ͷ�����  
    public synchronized void releaseConn(Connection conn) throws SQLException {  
        if (isValid(conn)&& !(freeConnection.size() > dbBean.getMaxConnections())) {  
            freeConnection.add(conn);  
            activeConnection.remove(conn);  
            contActive --;  
            threadLocal.remove();  
            // �������������ȴ����̣߳�ȥ������  
            notifyAll();  
        }  
    }  
  
    // �ж������Ƿ����  
    private boolean isValid(Connection conn) {  
        try {  
            if (conn == null || conn.isClosed()) {  
                return false;  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return true;  
    }  
  
    // �������ӳ�  
    public synchronized void destroy() {  
        for (Connection conn : freeConnection) {  
            try {  
                if (isValid(conn)) {  
                    conn.close();  
                }  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
        for (Connection conn : activeConnection) {  
            try {  
                if (isValid(conn)) {  
                    conn.close();  
                }  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
        isActive = false;  
        contActive = 0;  
    }  
  
    // ���ӳ�״̬  
    public boolean isActive() {
        return isActive;  
    }  
      
    // ��ʱ������ӳ����  
    public void cheackPool() {  
        if(dbBean.isCheakPool()){  
            new Timer().schedule(new TimerTask() {  
            @Override  
            public void run() {  
            // 1.���߳����������״̬  
            // 2.���ӳ���С ���������  
            // 3.����״̬���м�飬��Ϊ���ﻹ��Ҫд�����̹߳�����࣬��ʱ�Ͳ������  
            System.out.println("���߳���������"+freeConnection.size());  
            System.out.println("�����������"+activeConnection.size());  
            System.out.println("�ܵ���������"+contActive);  
                }  
            },dbBean.getLazyCheck(),dbBean.getPeriodCheck());  
        }  
    }  

}
