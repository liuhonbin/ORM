package com.base;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class BaseEntity implements Serializable {
    
	private static final long serialVersionUID = 1L;

	/**
	  * conditionParam
	  * @Title: conditionParam(构造一个map对象)
	  * @Description: 构造一个map对象,改实体类中所有不为空的属性和值得map对象
	  * @param  NoSuchMethodException
	  * @param  SecurityException
	  * @param  IllegalAccessException
	  * @param  IllegalArgumentException
	  * @param  InvocationTargetException    设定文件
	  * @return Map<String,Object>    返回类型
	  * @throws
	  */
	public Map<String, Object> conditionParam() throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		@SuppressWarnings("unchecked")
		Class<BaseEntity> clazz = (Class<BaseEntity>) this.getClass();
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] field = clazz.getDeclaredFields();
		for (Field f : field) {
			String modName = "get" + Character.toUpperCase(f.getName().charAt(0)) + f.getName().substring(1);
			// 拿到方法
			Method method = clazz.getMethod(modName);
			Class<?> type = method.getReturnType();
			if (type.getName() == ("java.lang.String")) {
				// 通过反射执行entity的 get方法拿到value
				String val = (String) method.invoke(this);
				// 若前台传过来的值不为空，则追加该属性
				if (!StringUtils.isEmpty(val)) {
					map.put(f.getName(), val);
				}
			} else if (type.getName() == ("int") || type.getName() == ("java.lang.Integer")) {
				Integer val = (Integer) method.invoke(this);
				if (val!=null&&val != 0) {
					map.put(f.getName(), val);
				}
			}
		}
		return map;
	}
}
