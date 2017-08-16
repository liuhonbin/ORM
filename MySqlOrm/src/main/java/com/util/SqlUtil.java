package com.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Map.Entry;

import com.base.BaseEntity;

public class SqlUtil {
  
	/**
	 * sql 语句参数处理      #{XXXX}方式处理
	 * @param sql
	 * @param baseEntity
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static String sqlPrament(String sql, BaseEntity baseEntity) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String str = null;
		if ( baseEntity != null) {
			Map<String, Object> p = baseEntity.conditionParam();
			for (Entry<String, Object> entry : p.entrySet()) {
				String key = entry.getKey();
				if (sql.indexOf("#{\\s*" + key + "\\s*}") > 0) {
					str = sql.replace("#{\\s*" + key + "\\s*}", "'" + p.get(key).toString() + "'");
					sql = str;
				}
			}
		}else{
			return sql;
		}
		return str;
	}

   }
