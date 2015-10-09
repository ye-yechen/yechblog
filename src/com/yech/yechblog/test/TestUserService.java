package com.yech.yechblog.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.UserService;

/**
 * ≤‚ ‘ UserService
 * @author Administrator
 *
 */
public class TestUserService {

	private static UserService userService;
	/**
	 * ≤Â»Î”√ªß
	 */
	@BeforeClass
	public static void iniUserService(){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		userService = (UserService) context.getBean("userService");
	}
	
	@Test
	public void insertUser(){
		User user = new User();
		user.setEmail("123@qq.com");
		user.setPassword("123");
		user.setNickName("bmob");
		user.setNotes("ssss");
		userService.saveEntity(user);
	}
	
}
