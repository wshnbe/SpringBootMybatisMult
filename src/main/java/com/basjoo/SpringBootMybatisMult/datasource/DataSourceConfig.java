package com.basjoo.SpringBootMybatisMult.datasource;

import java.util.HashMap;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class DataSourceConfig {

	@Primary
	@Bean(name="dataSource") //返回对象实例
	@ConfigurationProperties(prefix="spring.c3p0.datasource.master") //指定配置文件中的属性的读取
	public DataSource dataSource(){
		return DataSourceBuilder.create().type(ComboPooledDataSource.class).build();
	}
	
	@Bean(name="slaveDataSource1")
	@ConfigurationProperties(prefix="spring.c3p0.datasource.s1")
	public DataSource slaveDataSource1(){
		return DataSourceBuilder.create().type(ComboPooledDataSource.class).build();
	}
	
	@Bean(name="slaveDataSource2")
	@ConfigurationProperties(prefix="spring.c3p0.datasource.s2")
	public DataSource slaveDataSource2(){
		return DataSourceBuilder.create().type(ComboPooledDataSource.class).build();
	}
	
	/* 这是只返回一个SqlSessionFactoryBean 对象和springboot底层对于mybatis的封装处理有关，这里先这样使用 */
	@Bean
	public SqlSessionFactoryBean getSqlSessionFactoryBean(){
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(getDataSource());
		return sqlSessionFactory;
	}
	
	/* 事务管理器 */
	@Bean
	public PlatformTransactionManager getTransactionManager(){
		return new DataSourceTransactionManager(getDataSource());
	}
	
	/* 动态数据源 */
	@Bean(name="dynamicDB")
	public DataSource getDataSource(){
		DynamicDataSource dynamic = new DynamicDataSource();
		dynamic.setDefaultTargetDataSource(dataSource());
		HashMap<Object, Object> dbs = new HashMap<Object,Object>(5);
		dbs.put("ds-master", dataSource());
		dbs.put("ds-slave1", slaveDataSource1());
		dbs.put("ds-slave2", slaveDataSource2());
		dynamic.setTargetDataSources(dbs);
		return dynamic;
	}
}
