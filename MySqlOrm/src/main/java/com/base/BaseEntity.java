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
	  * @Title: conditionParam(����һ��map����)
	  * @Description: ����һ��map����,��ʵ���������в�Ϊ�յ����Ժ�ֵ��map����
	  * @param  NoSuchMethodException
	  * @param  SecurityException
	  * @param  IllegalAccessException
	  * @param  IllegalArgumentException
	  * @param  InvocationTargetException    �趨�ļ�
	  * @return Map<String,Object>    ��������
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
			// �õ�����
			Method method = clazz.getMethod(modName);
			Class<?> type = method.getReturnType();
			if (type.getName() == ("java.lang.String")) {
				// ͨ������ִ��entity�� get�����õ�value
				String val = (String) method.invoke(this);
				// ��ǰ̨��������ֵ��Ϊ�գ���׷�Ӹ�����
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
