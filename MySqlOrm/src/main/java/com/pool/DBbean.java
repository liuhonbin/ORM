package com.pool;

public class DBbean {
	// ���ӳ�����
	private String driverName;
	private String url;
	private String userName;
	private String password;
	// ���ӳ�����
	private String poolName;
	private int minConnections = 1; // ���гأ���С������
	private int maxConnections = 10; // ���гأ����������

	private int initConnections = 5;// ��ʼ��������

	private long connTimeOut = 1000;// �ظ�������ӵ�Ƶ��

	private int maxActiveConnections = 100;// ���������������������ݿ��Ӧ

	private long connectionTimeOut = 1000 * 60 * 20;// ���ӳ�ʱʱ�䣬Ĭ��20����

	private boolean isCurrentConnection = true; // �Ƿ��õ�ǰ���ӣ�Ĭ��true

	private boolean isCheakPool = true; // �Ƿ�ʱ������ӳ�
	private long lazyCheck = 1000 * 60 * 60;// �ӳٶ���ʱ���ʼ ���
	private long periodCheck = 1000 * 60 * 60;// ���Ƶ��

	public DBbean(String driverName, String url, String userName, String password, String poolName) {
		super();
		this.driverName = driverName;
		this.url = url;
		this.userName = userName;
		this.password = password;
		this.poolName = poolName;
	}

	public DBbean() {
	}

	public String getDriverName() {
		if (driverName == null) {
			driverName = this.getDriverName() + "_" + this.getUrl();
		}
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPoolName() {
		return poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	public int getMinConnections() {
		return minConnections;
	}

	/**
	 * ���гأ���С������ Ĭ��ֵ 1
	 * @param minConnections
	 */
	public void setMinConnections(int minConnections) {
		this.minConnections = minConnections;
	}

	public int getMaxConnections() {
		return maxConnections;
	}

	/**
	 * ���гأ���������� Ĭ��ֵ 20
	 * @param minConnections
	 */
	public void setMaxConnections(int maxConnections) {
		this.maxConnections = maxConnections;
	}

	public int getInitConnections() {
		return initConnections;
	}

	public void setInitConnections(int initConnections) {
		this.initConnections = initConnections;
	}

	public int getMaxActiveConnections() {
		return maxActiveConnections;
	}

	public void setMaxActiveConnections(int maxActiveConnections) {
		this.maxActiveConnections = maxActiveConnections;
	}

	public long getConnTimeOut() {
		return connTimeOut;
	}

	public void setConnTimeOut(long connTimeOut) {
		this.connTimeOut = connTimeOut;
	}

	public long getConnectionTimeOut() {
		return connectionTimeOut;
	}

	public void setConnectionTimeOut(long connectionTimeOut) {
		this.connectionTimeOut = connectionTimeOut;
	}

	public boolean isCurrentConnection() {
		return isCurrentConnection;
	}

	public void setCurrentConnection(boolean isCurrentConnection) {
		this.isCurrentConnection = isCurrentConnection;
	}

	public long getLazyCheck() {
		return lazyCheck;
	}

	public void setLazyCheck(long lazyCheck) {
		this.lazyCheck = lazyCheck;
	}

	public long getPeriodCheck() {
		return periodCheck;
	}

	public void setPeriodCheck(long periodCheck) {
		this.periodCheck = periodCheck;
	}

	public boolean isCheakPool() {
		return isCheakPool;
	}

	public void setCheakPool(boolean isCheakPool) {
		this.isCheakPool = isCheakPool;
	}

}
