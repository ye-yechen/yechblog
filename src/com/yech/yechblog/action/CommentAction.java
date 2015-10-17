package com.yech.yechblog.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yech.yechblog.aware.UserAware;
import com.yech.yechblog.entity.Blog;
import com.yech.yechblog.entity.Comment;
import com.yech.yechblog.entity.Message;
import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.BlogService;
import com.yech.yechblog.service.MessageService;

/**
 * CommentAction
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class CommentAction extends BaseAction<Comment> implements UserAware{

	private static final long serialVersionUID = 1462140807513502797L;

	/**
	 * 注入 SurveyService
	 */
	@Resource
	private BlogService blogService;
	
	@Resource
	private MessageService messageService;
	// 接收 User 对象
	private User user;
	@Override
	public void setUser(User user) {
		this.user = user;
	}
	// 接收页面的bid(blog id)
	private Integer bid;

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}
	
	/**
	 * 为当前博客添加评论
	 * @return
	 */
	public String addComment(){
		//设置 blog 和 comment 的关联关系
		model.setUser(user);
		
		Blog blog2 = blogService.getBlogById(bid);//为了得到此博客的作者
		Blog blog = new Blog();
		blog.setId(bid);
		model.setBlog(blog);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		model.setCommentTime(format.format(new Date()));
		blog.getComments().add(model);
		blogService.addComment(model);
		
		//将评论消息添加到 Message 中
		Message message = new Message();
		message.setContent(model.getContent());
		message.setSelf(user);
		message.setOther(blog2.getUser());
		message.setStatus(true);//1代表未读
		
		message.setCreateTime(format.format(new Date()));
		messageService.saveMessage(message);
		return "toDetailReadPage";
	}
	
}
