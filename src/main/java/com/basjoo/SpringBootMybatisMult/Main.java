package com.basjoo.SpringBootMybatisMult;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 系统启动类 
 * @author wulinli
 */

@SpringBootApplication
@EnableTransactionManagement
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
