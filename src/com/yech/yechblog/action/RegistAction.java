package com.yech.yechblog.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.UserService;
import com.yech.yechblog.util.DataUtil;
import com.yech.yechblog.util.SendMailUtil;
import com.yech.yechblog.util.ValidateUtil;

@Controller
@Scope("prototype")
public class RegistAction extends BaseAction<User> implements ServletRequestAware{
	
	private static final long serialVersionUID = 3871358626902449552L;
	
	/**
	 * 注入 UserService
	 */
	@Resource
	private UserService userService;

	private HttpServletRequest request;
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

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
		model.setImage("/image/personImg.jpg");//保存默认图片
		model.setStatus(false);//设置没有验证
		model.setValidateCode(DataUtil.md5("VaLiDaTeCoDe"));
		userService.saveEntity(model);
		sendMessage();//发送验证邮件
		return "BlogAction";//重定向进入首页博客列表
	}
	
	private String email;
	private String vcode;
	private Integer userId;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	/**
	 * 处理邮箱验证
	 * @return
	 */
	public String doMessageValidate(){
		model = userService.getEntity(userId);
		//验证激活账号，修改账号状态  
		if(vcode.equals(model.getValidateCode())){
			model.setStatus(true);
			model.setValidateCode("");
			userService.setValidate(model);//更新status为1，validate为""
			request.setAttribute("msg", "<h3>激活成功！正在为您跳转到yechblog首页</h3>");
		} else {
			request.setAttribute("msg", "<h3>已经激活过了,不要重复激活!</h3>");
			System.out.println("已经激活过了,不要重复激活!");
		}
		return "temp";
	}
	/**
	 * 发送验证邮件
	 * @return
	 */
	public void sendMessage(){
		if(model != null && !model.getStatus()){ //没有验证
			String email = model.getEmail().trim();
			String code = DataUtil.md5("VaLiDaTeCoDe");
			StringBuffer content = new StringBuffer("亲爱的"+model.getUsername()+" :您好!<br>");
			 content.append("&nbsp;&nbsp;&nbsp;&nbsp;感谢您注册 yechblog，您只要点击下面的链接，激活您的账号，便可以享受 yechblog 各项服务。")
			 .append("<br><br>")
//			 .append("<a style='font-size:16px;' "
//					 	+ "href='http://localhost:8080/yechblog/RegistAction_doMessageValidate?")
//			 .append("email=" + email + "&vcode=" + code+"&userId="+model.getId() +"'>")
			 .append("<span style='font-size:16px;'>")
			 .append("http://114.215.92.22:8080/yechblog/RegistAction_doMessageValidate?email="+ email + "&vcode=" + code+"&userId="+model.getId())
			 .append("<span>")
			 .append("<br><br><br><br>")
			 .append("&nbsp;&nbsp;此链接只能激活一次，请尽快激活!");
			 SendMailUtil.send(email, content.toString());//开始发送邮件
		}
	}
	//接收页面传过来的验证码
	private String identifyCode;
	
	public String getIdentifyCode() {
		return identifyCode;
	}

	public void setIdentifyCode(String identifyCode) {
		this.identifyCode = identifyCode;
	}

	/**
	 * 校验(重写 ActionSupport 父类的方法进行校验)
	 */
	public void validateDoRegist(){
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
		if(!identifyCode.toLowerCase().equals(((String)(ActionContext.getContext().getSession().get("code"))).toLowerCase())){
			addFieldError("identifyCode", "验证码不对!");
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
