package com.base.dao;

import java.util.List;

import com.base.BaseEntity;

public interface JdbcOperations {

	<T> List<T> query(String sql, Class<T> clazz, BaseEntity baseEntity);

	<T> T queryForObject(String sql, Class<T> clazz, BaseEntity baseEntity);

	int update(String sql, BaseEntity baseEntity);

	int delete(String sql, BaseEntity baseEntity);

}
