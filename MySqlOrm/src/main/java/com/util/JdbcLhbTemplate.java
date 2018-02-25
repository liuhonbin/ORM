package com.util;

import com.util.sql.ProxySql;

public class JdbcLhbTemplate {

	public JdbcLhbTemplate() {
	}

	@SuppressWarnings("unchecked")
	public static <T> T execute(Class<T> clazz) {
		return (T) ProxySql.newInstance(clazz);
	}
}
