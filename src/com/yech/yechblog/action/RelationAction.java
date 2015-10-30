package com.yech.yechblog.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yech.yechblog.aware.UserAware;
import com.yech.yechblog.entity.Message;
import com.yech.yechblog.entity.Relation;
import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.MessageService;
import com.yech.yechblog.service.RelationService;
import com.yech.yechblog.service.UserService;
/**
 * RelationAction
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class RelationAction extends BaseAction<Relation> implements UserAware{

	private static final long serialVersionUID = 9162299802568974005L;

	@Resource
	private UserService userService;
	
	@Resource
	private RelationService relationService;
	
	@Resource
	private MessageService messageService;
	private User user;

	@Override
	public void setUser(User user) {
		this.user = user;
	}
	//以流的方式返回
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}
	
	//接收页面传过来的userId，即将要关注的人
	private Integer userId;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 添加关注
	 * 处理 ajax 请求
	 * 将关注后的信息以流的方式返回，以便 ajax 代码调用(struts2 中 ajax的用法)
	 * 关注成功返回 1，否则返回0
	 * @return
	 */
	public String addFocus(){
		try {
			User other = userService.getEntity(userId);
			//已经关注过了
			if(!relationService.queryRelation(user,other)){
				model.setSelf(user); 
				model.setOther(other);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				model.setCreateTime(format.format(new Date()));
				relationService.saveRelation(model);
				
				// 将收藏消息添加到 Message 中
				Message message = new Message();
				message.setContent("关注了");
				message.setSelf(user);
				message.setOther(other);
				//消息类型是关注
				message.setStatus(true);// 1代表未读
				message.setCreateTime(format.format(new Date()));
				messageService.saveMessage(message);
				inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
			} else {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			}
		} catch (Exception e) {
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "ajax-success";
	}

	
}
