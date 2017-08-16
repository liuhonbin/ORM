package com.test;

import java.util.List;

import com.base.BaseDao;
import com.base.impl.BaseImpl;
import com.entity.Book;

public class Test {

	     public static void main(String[] args) {
			BaseDao dao = new BaseImpl();
			List<Book> list  = dao.getList("select * from book", Book.class, null);
			System.out.println(list.get(0).toString());
		}
	
	
  }
