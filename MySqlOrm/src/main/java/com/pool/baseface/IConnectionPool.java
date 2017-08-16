package com.pool.baseface;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionPool {
	// �������
	Connection getConnection();

	// ��õ�ǰ����
	Connection getCurrentConnecton();

	// ��������
	void releaseConn(Connection conn) throws SQLException;

	// �������
	void destroy();

	// ���ӳ��ǻ״̬
	boolean isActive();

	// ��ʱ����������ӳ�
	void cheackPool();
}
