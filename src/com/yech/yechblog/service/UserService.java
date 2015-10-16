package com.yech.yechblog.service;

import com.yech.yechblog.entity.User;

public interface UserService extends BaseService<User> {

	/**
	 * 判断 email 是否被占用
	 */
	public boolean isRegisted(String email);
	
	/**
	 * 验证登录信息
	 */
	public User validateLoginInfo(String email,String pswByMD5);

	/**
	 * 上传头像
	 * @param string
	 */
	public void updateUserImgPath(Integer id, String string);
}
