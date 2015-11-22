package com.yech.yechblog.util;

import com.yech.yechblog.entity.User;

public class Global {

	public static User user = null;
	
	/**
	 * 页面跳转前的路径(用于登录后跳转回登录前的页面)
	 * 之所以要写出全局静态变量，是因为一般的登录是经过LoginAction的toLoginPage方法到达登录
	 * 页面的，因此可以在toLoginPage方法中保存登录前的页面url，但是还有一种情况就是进行写操作时登录
	 * 拦截器发现用户没有登录，直接将页面重定向到登录页面的，此时没有经过toLoginPage方法，
	 * 因此无法保存登录前的页面
	 */
	public static String originUrl = "";
}
