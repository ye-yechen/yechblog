package com.yech.yechblog.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yech.yechblog.aware.UserAware;
import com.yech.yechblog.entity.Comment;
import com.yech.yechblog.entity.Message;
import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.BlogService;
import com.yech.yechblog.service.MessageService;

/**
 * CommentAction
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class MessageAction extends BaseAction<Message> implements UserAware{

	private static final long serialVersionUID = -5672583535553188385L;
	
	@Resource
	private MessageService messageService;
	
	@Resource
	private BlogService blogService;
	//接收页面传过来的 message 的 id
	private Integer mid;
	private Integer bid;
	
	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	//不能去掉，否则被LoginInteceptor 拦截了
	private User user;
	
	@Override
	public void setUser(User user) {
		this.user = user;
	}
	
	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	private List<Comment> comments;
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * 去到此条消息的详细页面
	 * @return
	 */
	public String toDetailMessage(){
		//将 message 表中的 status 字段设为 0 ，表示已读
		messageService.changeMessageStatus(mid);
		comments = blogService.queryAllComments(bid);
		model = messageService.getMessageById(mid);
		model.setBlog(blogService.getBlogById(bid));
		return "toDetailMessagePage";
	}
	
}
