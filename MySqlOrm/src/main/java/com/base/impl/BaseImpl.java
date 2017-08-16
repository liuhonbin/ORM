package com.base.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.base.BaseDao;
import com.base.BaseEntity;
import com.util.DBUtil;
import com.util.DTOUtils;
import com.util.SqlUtil;

public class BaseImpl implements BaseDao {

	public <T extends BaseEntity> List<T> getList(String sql, Class<T> clazz, BaseEntity baseEntity) {
		List<T> list = new ArrayList<T>();
		String newSql = null;
		ResultSet rs = null;
		try {
			newSql = SqlUtil.sqlPrament(sql, baseEntity);
			rs = DBUtil.query(newSql);
			while (rs.next()) {
				list.add(DTOUtils.ReqBuildEntity(rs, clazz));
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public <T extends BaseEntity> T getOne(String sql, Class<T> clazz, BaseEntity baseEntity) {
		String newSql = null;
		ResultSet rs = null;
		try {
			newSql = SqlUtil.sqlPrament(sql, baseEntity);
			rs = DBUtil.query(newSql);
			while (rs.next()) {
				return DTOUtils.ReqBuildEntity(rs, clazz);
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int update(String sql, BaseEntity baseEntity) {
		// TODO Auto-generated method stub
		int a = 0;
		try {
			String new_sql = SqlUtil.sqlPrament(sql, baseEntity);
			a = DBUtil.other(new_sql);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	public int delete(String sql, BaseEntity baseEntity) {
		// TODO Auto-generated method stub
		int a = 0;
		try {
			String new_sql = SqlUtil.sqlPrament(sql, baseEntity);
			a = DBUtil.other(new_sql);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

}
