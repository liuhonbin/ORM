package com.util.sql;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.annotation.Delete;
import com.annotation.Sql;
import com.annotation.Update;
import com.base.BaseDao;
import com.base.BaseEntity;
import com.base.impl.BaseImpl;
import com.entity.Book;

import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;

public class ProxyCgbliSql implements InvocationHandler {

	private BaseDao dao = new BaseImpl();

	public ProxyCgbliSql() {
		// TODO Auto-generated constructor stub
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

}
