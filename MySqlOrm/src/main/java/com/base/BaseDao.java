package com.base;

import java.util.List;

public interface BaseDao {
     
	
	<T extends BaseEntity> List<T> getList(String sql,Class<T> clazz,BaseEntity baseEntity);
	
	<T extends BaseEntity> T getOne(String sql,Class<T> clazz,BaseEntity baseEntity);  
	   
	int update (String sql,BaseEntity baseEntity); 
	
	int delete (String sql,BaseEntity baseEntity); 
  }
