package com.util.sql;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.base.BaseDao;
import com.base.BaseEntity;
import com.base.impl.BaseImpl;
import com.dao.BookDao;
import com.entity.Book;

public class ProxySql implements InvocationHandler {
	private BaseDao dao = new BaseImpl();

	public static Object newInstance(Class<?> clazz) {
		Class[] interfaces = new Class[1];
		interfaces[0] = clazz;
		return Proxy.newProxyInstance(ProxySql.class.getClassLoader(), interfaces, new ProxySql());
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return in(proxy, method, args);
	}

	@SuppressWarnings("unused")
	private <T extends BaseEntity> Object in(Object proxy, Method method, Object[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		Class<?> returnType = method.getReturnType();
		String sql = "";
		Annotation annotation[] = method.getAnnotations();
		if (returnType.getName().contains("java.util.List")) {
			if (annotation.length > 0) {
				if ("com.annotation.Sql".equals(annotation[0].annotationType().getName())) {
					Method[] ms = annotation[0].annotationType().getDeclaredMethods();
					Class<?> clazz = Class.forName(ms[0].invoke(annotation[0], null).toString().split(" ")[1]);
					sql = ms[1].invoke(annotation[0], null).toString();
					return dao.getList(sql, (Class<T>) clazz, (BaseEntity) args[0]);
				}
			}
		} else {
			if (annotation.length > 0) {
				if ("com.annotation.Update".equals(annotation[0].annotationType().getName())) {
					Method[] ms = annotation[0].annotationType().getDeclaredMethods();
					Class<?> clazz = Class.forName(ms[0].invoke(annotation[0], null).toString().split(" ")[1]);
					sql = ms[1].invoke(annotation[0], null).toString();
					return dao.update(sql, (BaseEntity) args[0]);
				} else if ("com.annotation.Delete".equals(annotation[0].annotationType().getName())) {
					Method[] ms = annotation[0].annotationType().getDeclaredMethods();
					Class<?> clazz = Class.forName(ms[0].invoke(annotation[0], null).toString().split(" ")[1]);
					sql = ms[1].invoke(annotation[0], null).toString();
					return dao.delete(sql, (BaseEntity) args[0]);
				} else if ("com.annotation.Sql".equals(annotation[0].annotationType().getName())) {
					Method[] ms = annotation[0].annotationType().getDeclaredMethods();
					Class<?> clazz = Class.forName(ms[0].invoke(annotation[0], null).toString().split(" ")[1]);
					sql = ms[1].invoke(annotation[0], null).toString();
					return dao.queryForObject(sql, (Class<T>) clazz, (BaseEntity) args[0]);
				}
			}
		}
		return null;
	}

	private Class<?> clazz;

	@SuppressWarnings("unchecked")
	public <T> Class<T> getClazz() {
		return (Class<T>) clazz;
	}

	public <T> void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public static void main(String[] args) {

//		BookDao example = (BookDao) ProxySql.newInstance(BookDao.class);
//		// aduit bean 1 在接口执行的时候就会去调invoke 方法
//		List<Book> list = example.getList("select * from book ", Book.class, null);
//		System.out.println(list.get(0));
	}

}
