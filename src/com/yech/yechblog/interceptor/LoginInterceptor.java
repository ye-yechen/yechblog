package com.yech.yechblog.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.yech.yechblog.action.BaseAction;
import com.yech.yechblog.action.IdentifyCodeAction;
import com.yech.yechblog.action.LoginAction;
import com.yech.yechblog.action.RegistAction;
import com.yech.yechblog.action.UserAction;
import com.yech.yechblog.aware.UserAware;
import com.yech.yechblog.entity.User;
import com.yech.yechblog.util.Global;

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

	@SuppressWarnings({ "rawtypes", "unchecked"})
	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
			//生成验证码的Action,直接放行
			if(arg0.getAction() instanceof IdentifyCodeAction){
				return arg0.invoke();
			}
			
			BaseAction action = (BaseAction) arg0.getAction();
			if(action instanceof LoginAction
					|| action instanceof RegistAction ){
				//不拦截注册和登录action，直接放行
				return arg0.invoke();
			}
			//取出 session 中的 user 信息
			User user = 
					(User) arg0.getInvocationContext().getSession().get("user");
			if(user == null){
				HttpServletRequest request = 
						(HttpServletRequest)arg0.getInvocationContext().
								get(ServletActionContext.HTTP_REQUEST);
				HttpServletResponse response = 
						(HttpServletResponse) arg0.getInvocationContext().
								get(ServletActionContext.HTTP_RESPONSE);
				//如果是ajax请求
				if(request.getHeader("X-Requested-With") != null 
						&& request.getHeader("X-Requested-With").
						equalsIgnoreCase("XMLHttpRequest")){
					PrintWriter out = response.getWriter();
					out.print("isNotLogin");//返回一个标识给前端
					out.flush();
					out.close();
					return null;
				}
				
				Global.originUrl = request.getHeader("referer");
				Global.originUrl = 
						Global.originUrl.substring(Global.originUrl.lastIndexOf("/")+1);
				//去登录
				return "login";
			} else {
				//如果 action 实现了 UserAware 接口,则为此 action 注入 user
				if(action instanceof UserAware){
					((UserAware)action).setUser(user);
					return arg0.invoke();
				} else if(action instanceof UserAction){
					action.model = user;
					return arg0.invoke();
				}
			}
		return null;
	}
}
