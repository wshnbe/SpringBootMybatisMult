package com.basjoo.SpringBootMybatisMult.module.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.basjoo.SpringBootMybatisMult.datasource.anno.DS;
import com.basjoo.SpringBootMybatisMult.error.AllException;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	@DS("ds-slave2")
	@Transactional(propagation=Propagation.REQUIRED) //使用事务注解,并声明事务传播机制
	public List<User> findAll1(){
		return userMapper.findAll();
	}
	
	@DS("ds-slave1")
	public List<User> findAll2(){
		return userMapper.findAll();
	}
	
	@DS("ds-master")
	public List<User> findAll3(){
		return userMapper.findAll();
	}
	
	@DS("ds-master")
	@Transactional(propagation=Propagation.REQUIRED) 
	public void addUser(){
		userMapper.insertByParam(80, "80");
		userMapper.insertByParam(90, "90");
		int a = 10/0;
		userMapper.insertByParam(100, "100");
	}
}
