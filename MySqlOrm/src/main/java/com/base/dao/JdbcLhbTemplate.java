package com.base.dao;

import java.util.List;

import com.base.BaseEntity;

public class JdbcLhbTemplate implements JdbcOperations {
	

	public <T> List<T> query(String sql, Class<T> clazz, BaseEntity baseEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T queryForObject(String sql, Class<T> clazz, BaseEntity baseEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	public int update(String sql, BaseEntity baseEntity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(String sql, BaseEntity baseEntity) {
		// TODO Auto-generated method stub
		return 0;
	}
       
}
