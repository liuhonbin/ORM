package com.pool.baseface;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionPool {
	// 获得连接
	Connection getConnection();

	// 获得当前连接
	Connection getCurrentConnecton();

	// 回收连接
	void releaseConn(Connection conn) throws SQLException;

	// 销毁清空
	void destroy();

	// 连接池是活动状态
	boolean isActive();

	// 定时器，检查连接池
	void cheackPool();
}
