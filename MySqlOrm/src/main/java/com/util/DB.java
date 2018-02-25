package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	public String DBDRIVER = "com.mysql.jdbc.Driver";
	public String DBURL = "jdbc:mysql://127.0.0.1:3306/xs";
	public String DBUSER = "root";
	public String DBPASS = "123456";	
	
	public String DBDRIVER_2 = "com.mysql.jdbc.Driver";
	public String DBURL_2 = "jdbc:mysql://47.93.22.26:3306/xs?useUnicode=true&characterEncoding=UTF8";
	public String DBUSER_2 = "test";
	public String DBPASS_2 = "123456";	
	
	/**
	 * 加载驱动
	 */
	public DB(){
		try {
			Class.forName(DBDRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("驱动加载失败!");
		}
	}
	
	
	
	/**
	 * 连接数据库
	 * @return
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("正在连接备用数据库!");
			try {
				conn = DriverManager.getConnection(DBURL_2, DBUSER_2, DBPASS_2);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println("数据库连接异常!");
			}
		}
		return conn;
	}
	
	/**
	 * 关闭数据库
	 */
	public void close(ResultSet rs,Statement stmt,Connection conn){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
