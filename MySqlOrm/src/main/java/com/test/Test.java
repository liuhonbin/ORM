package com.test;

import java.util.List;

import com.base.BaseDao;
import com.base.impl.BaseImpl;
import com.dao.BookDao;
import com.entity.Book;
import com.util.JdbcLhbTemplate;

public class Test {

	public static void main(String[] args) throws ClassNotFoundException {
		// BaseDao dao = new BaseImpl();
		// Book book = new Book();
		// book.setBook_name("我的测试数据68");
		// List<Book> list = dao.getList("select * from book where book_name =
		// #{book_name}", Book.class, book);
		// System.out.println(list.get(0).toString());
		// String string = "s sssss s";

		
		
		BookDao dao = JdbcLhbTemplate.execute(BookDao.class);
		Book book = new Book();
		book.setBook_name("我的测试数据68");
		List<Book> list = dao.getList(book);
		System.out.println(list.get(0).toString());
		
		
	}

}
