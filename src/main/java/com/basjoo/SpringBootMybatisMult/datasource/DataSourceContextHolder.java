package com.basjoo.SpringBootMybatisMult.datasource;

/**
 * 数据源连接池 控制器
 * @author wulinli
 *
 */
public class DataSourceContextHolder {

	/* 设置默认数据源 */
	public static final String DEFAULT_DS = "ds-master";
	
	private static final ThreadLocal<String> handler = new ThreadLocal<String>();
	
	public static void  setDB(String db){
		handler.set(db);
	}
	
	public static String getDB(){
		return handler.get();
	}
	
	public static void clearDB(){
		handler.remove();
	}
}
