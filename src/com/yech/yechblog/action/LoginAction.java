package com.yech.yechblog.action;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.UserService;
import com.yech.yechblog.util.DataUtil;

/**
 * 登录Action
 * 
 * @author Administrator
 */
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<User> implements SessionAware{

	private static final long serialVersionUID = 6769670387253184703L;

	//是否记住密码
	private boolean remember;
	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	// 接收 session 的 map
	private Map<String, Object> sessionMap;
	/**
	 * 注入 UserService
	 */
	@Resource
	private UserService userService;
	
	/**
	 * 到达登录界面
	 * @return
	 */
	@SkipValidation
	public String toLoginPage(){
		return "loginPage";
	}
	
	/**
	 * 进行登录处理
	 * @return
	 */
	public String doLogin(){
		return "success";
	}
	
	public void validate(){
		User user = 
				userService.validateLoginInfo(model.getEmail(),DataUtil.md5(model.getPassword()));
		if(user == null){
			addActionError("email 或 password 错误!");
		} else {
			if(isRemember()){ //记住了密码
			}
			sessionMap.put("user", user);//将 user 信息放到session域
		}
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}

}
