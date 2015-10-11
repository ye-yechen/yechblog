package com.yech.yechblog.action;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.UserService;
import com.yech.yechblog.util.DataUtil;
import com.yech.yechblog.util.ValidateUtil;

@Controller
@Scope("prototype")
public class RegistAction extends BaseAction<User> {
	
	private static final long serialVersionUID = 3871358626902449552L;
	
	/**
	 * 注入 UserService
	 */
	@Resource
	private UserService userService;

	//去到注册页面
	@SkipValidation //跳过校验
	public String toRegistPage(){
		return "registPage";
	}
	
	/**
	 * 进行用户注册
	 * @return
	 */
	public String doRegist(){
		//密码加密
		model.setPassword(DataUtil.md5(model.getPassword()));
		userService.saveEntity(model);
		return SUCCESS;
	}
	/**
	 * 校验(重写 ActionSupport 父类的方法进行校验)
	 */
	public void validate(){
		//1.email 非空
		String email = model.getEmail();
		if(!ValidateUtil.isValidate(email)){
			addFieldError("email", "email 不能为空!");
		}
		if(!ValidateUtil.isValidate(model.getUsername())){
			addFieldError("username", "username 不能为空!");
		}
		if(!ValidateUtil.isValidate(model.getPassword())){
			addFieldError("password", "password 不能为空!");
		}
		if(hasErrors()){
			return;
		}
		//2.email被占用
		if(userService.isRegisted(model.getEmail())){
			addFieldError("email", "email 已经存在!");
		}
	}
}
