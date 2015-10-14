package com.yech.yechblog.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.yech.yechblog.action.BaseAction;
import com.yech.yechblog.action.LoginAction;
import com.yech.yechblog.action.RegistAction;
import com.yech.yechblog.aware.UserAware;
import com.yech.yechblog.entity.User;

/**
 * 登录拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = -113465086676835611L;

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		BaseAction action = (BaseAction) arg0.getAction();
		if(action instanceof LoginAction
				|| action instanceof RegistAction){
			//不拦截注册和登录action，直接放行
			return arg0.invoke();
		}
		//取出 session 中的 user 信息
		User user = (User) arg0.getInvocationContext().getSession().get("user");
		if(user == null){
			//去登录
			return "login";
		} else {
			//如果 action 实现了 UserAware 接口,则为此 action 注入 user
			if(action instanceof UserAware){
				((UserAware)action).setUser(user);
				return arg0.invoke();
			}
		}
		return null;
	}

}
