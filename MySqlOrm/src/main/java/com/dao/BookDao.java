package com.dao;

import java.util.List;

import com.annotation.Sql;
import com.base.BaseEntity;
import com.entity.Book;

public interface BookDao {
       
	  List<Book> getList(String sql, Class<?> clazz, BaseEntity baseEntity);
      
	  @Sql(sql_value="select * from book ",type=Book.class)	
      List<Book> getList(BaseEntity baseEntity);
   }
