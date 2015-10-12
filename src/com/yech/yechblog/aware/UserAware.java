package com.yech.yechblog.aware;

import com.yech.yechblog.entity.User;


/**
 *	为需要User信息的类传入User对象
 */
public interface UserAware {
	public void setUser(User user);
}
