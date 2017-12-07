package com.util.sql;

import java.lang.reflect.Method;

import com.base.BaseDao;
import com.base.BaseEntity;
import com.base.impl.BaseImpl;
import com.entity.Book;

import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;

public class ProxyCgbliSql implements InvocationHandler {

	private BaseDao dao;

	public ProxyCgbliSql() {
		// TODO Auto-generated constructor stub
		dao = new BaseImpl();
	}

	
	public static Object newInstance(@SuppressWarnings("rawtypes") Class[] interfaces) {
        return Proxy.newProxyInstance(ProxyCgbliSql.class.getClassLoader(), interfaces, new ProxyCgbliSql());    
    }  


	public Object invoke(Object object, Method method, Object[] args) throws Throwable {
		Class<?> returnType = method.getReturnType();
		if (returnType.getName().contains("java.util.List")) {
			return dao.getList(args[0].toString(), Book.class, (BaseEntity) args[2]);
		}
		return null;
	}

	
	
}
