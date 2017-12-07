package com.util.sql;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import com.base.BaseDao;
import com.base.BaseEntity;
import com.base.impl.BaseImpl;
import com.dao.BookDao;
import com.entity.Book;

public class ProxySql implements InvocationHandler {
	private BaseDao dao = new BaseImpl();

	public static Object newInstance(@SuppressWarnings("rawtypes") Class[] interfaces) {
		return Proxy.newProxyInstance(ProxySql.class.getClassLoader(), interfaces, new ProxySql());
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return in(proxy, method, args);
	}
     
	private <T extends BaseEntity> Object in(Object proxy, Method method, Object[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		Class<?> returnType = method.getReturnType();
		String sql = "";
		if (returnType.getName().contains("java.util.List")) {
			Annotation annotation[] = method.getAnnotations();
			if (annotation.length > 0) {
				if ("com.annotation.Sql".equals(annotation[0].annotationType().getName())) {
					Method[] ms = annotation[0].annotationType().getDeclaredMethods();
					Class<?> clazz = Class.forName(ms[0].invoke(annotation[0], null).toString().split(" ")[1]);
					sql = ms[1].invoke(annotation[0], null).toString();
					return dao.getList(sql, (Class<T>) clazz, (BaseEntity) args[0]);
				}
			}
			return dao.getList(args[0].toString(), (Class<T>) args[1], (BaseEntity) args[2]);
		}
		return null;
	}

	private Class<?> clazz;

	@SuppressWarnings("unchecked")
	public <T extends BaseEntity> Class<T> getClazz() {
		return (Class<T>) clazz;
	}

	public <T extends BaseEntity> void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public static void main(String[] args) {

		BookDao example = (BookDao) ProxySql.newInstance(new Class[] { BookDao.class });
		// aduit bean 1 在接口执行的时候就会去调invoke 方法
		List<Book> list = example.getList("select * from book ",Book.class,null);
		System.out.println(list.get(0));
	}

}
