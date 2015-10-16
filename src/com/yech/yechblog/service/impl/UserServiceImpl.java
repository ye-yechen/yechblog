package com.yech.yechblog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yech.yechblog.dao.BaseDao;
import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.UserService;
import com.yech.yechblog.util.ValidateUtil;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> 
						implements UserService {

	/**
	 * 重写该方法，目的是为了覆盖超类中方法的注解，指明注入指定的 Dao 对象,否则Spring
	 * 无法确定注入哪个 Dao( 因为有多个满足条件的 Dao(多个BaseDao的实现类))
	 */
	@Resource(name="userDao")
	public void setBaseDao(BaseDao<User> baseDao) {
		super.setBaseDao(baseDao);
	}
	
	/**
	 * 验证邮箱是否被占用
	 */
	@Override
	public boolean isRegisted(String email) {
		String hql = "from User u where u.email = ?";
		List<User> list = this.batchFindEntityByHQL(hql, email);
		return ValidateUtil.isValidate(list);
	}

	/**
	 * 验证登录信息
	 */
	@Override
	public User validateLoginInfo(String email, String pswByMD5) {
		String hql = "FROM User u WHERE u.email = ? AND u.password = ?";
		List<User> list = batchFindEntityByHQL(hql, email,pswByMD5);
		return ValidateUtil.isValidate(list)?list.get(0):null;
	}

	/**
	 * 上传头像
	 */
	@Override
	public void updateUserImgPath(Integer id, String path) {
		String hql = "UPDATE User u SET u.image = ? WHERE u.id = ?";
		this.batchUpdateEntityByHQL(hql,path,id);
	}
}
