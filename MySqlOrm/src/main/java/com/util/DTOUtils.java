package com.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;

import com.base.BaseEntity;





public class DTOUtils {
    //通过传入的class类名去反实列化去赋值
	public static <T extends BaseEntity> T ReqBuildEntity(ResultSet rs,Class<T> clazz){
		T t=null;
		try {
			//jdk 代理
			t=clazz.newInstance();
			Field fs[]=clazz.getDeclaredFields();
			for (Field f : fs) {
				f.setAccessible(true);
				if(f.getType().getSimpleName().equals("Integer")){
					f.set(t, rs.getInt(""+f.getName()));
					continue;
				}
				if(f.getType().getSimpleName().equals("Double")){
					f.set(t, rs.getDouble(""+f.getName()));
					continue;
				}
				if(f.getType().getSimpleName().equals("String")){
					f.set(t, rs.getString(""+f.getName()));
					continue;
				}
				
				if(f.getType().getSimpleName().equals("Date")){
					f.set(t, rs.getDate(""+f.getName()));
					continue;
				}
				
				if(f.getType().getSimpleName().equals("Timestamp")){
					f.set(t, rs.getTimestamp(""+f.getName()));
					continue;
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}
	
	
	
	
	
}
