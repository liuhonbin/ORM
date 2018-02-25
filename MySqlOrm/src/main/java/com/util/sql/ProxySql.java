package com.util.sql;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.annotation.Delete;
import com.annotation.Sql;
import com.annotation.Update;
import com.base.BaseDao;
import com.base.BaseEntity;
import com.base.impl.BaseImpl;

public class ProxySql implements InvocationHandler {
	private BaseDao dao = new BaseImpl();

	public static Object newInstance(Class<?> clazz) {
		@SuppressWarnings("rawtypes")
		Class[] interfaces = new Class[1];
		interfaces[0] = clazz;
		return Proxy.newProxyInstance(ProxySql.class.getClassLoader(), interfaces, new ProxySql());
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return in(proxy, method, args);
	}

	private Object in(Object proxy, Method method, Object[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		Class<?> returnType = method.getReturnType();
		Annotation annotation[] = method.getAnnotations();
		if (returnType.getName().contains("java.util.List")) {
			if (annotation.length > 0) {
				for (Annotation an : annotation) {
					if (an instanceof Sql) {
						Sql sql_object = (com.annotation.Sql) an;
						return dao.getList(sql_object.sql(), sql_object.return_type(), (BaseEntity) args[0]);
					}

				}
			}
		} else {
			if (annotation.length > 0) {
				for (Annotation an : annotation) {
					if (an instanceof Update) {
						Update update_object = (Update) an;
						return dao.update(update_object.sql(), (BaseEntity) args[0]);
					} else if (an instanceof Delete) {
						Delete delete_object = (Delete) an;
						return dao.delete(delete_object.sql(), (BaseEntity) args[0]);
					} else if (an instanceof Sql) {
						Sql sql_object = (com.annotation.Sql) an;
						return dao.queryForObject(sql_object.sql(), sql_object.return_type(), (BaseEntity) args[0]);
					}
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {

		// BookDao example = (BookDao) ProxySql.newInstance(BookDao.class);
		// // aduit bean 1 在接口执行的时候就会去调invoke 方法
		// List<Book> list = example.getList("select * from book ", Book.class, null);
		// System.out.println(list.get(0));
	}

}
