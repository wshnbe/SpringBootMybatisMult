package com.basjoo.SpringBootMybatisMult.module.user;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.basjoo.SpringBootMybatisMult.error.AllException;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/get")
	public List<User> getUser() {

		List<User> all = Collections.synchronizedList(userService.findAll1());
		all.addAll(userService.findAll2());
		all.addAll(userService.findAll3());
		return all;
	}

	@RequestMapping(value = "/add",method =RequestMethod.GET)
	public void addUsers() throws AllException {
		userService.addUser();
	}
}
