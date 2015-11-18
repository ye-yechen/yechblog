package com.yech.yechblog.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yech.yechblog.aware.UserAware;
import com.yech.yechblog.entity.Answer;
import com.yech.yechblog.entity.Blog;
import com.yech.yechblog.entity.Comment;
import com.yech.yechblog.entity.Message;
import com.yech.yechblog.entity.Question;
import com.yech.yechblog.entity.Reply;
import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.AnswerService;
import com.yech.yechblog.service.CommentService;
import com.yech.yechblog.service.MessageService;
import com.yech.yechblog.service.ReplyService;
import com.yech.yechblog.service.UserService;

/**
 * ReplyAction
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply> implements UserAware {

	private static final long serialVersionUID = -6448012278102029221L;

	@Resource
	private ReplyService replyService;

	@Resource
	private CommentService commentService;

	@Resource
	private MessageService messageService;

	@Resource
	private AnswerService answerService;
	
	@Resource
	private UserService userService;
	// 注入当前登录的用户(即回复的人)
	private User user;

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	// 接收页面传过来的comment id
	private Integer cid;
	// 接收页面传过来的answer id
	private Integer aid;

	//接收页面传过来的 userId
	private Integer userId;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	/**
	 * 添加一条评论回复
	 * 
	 * @return
	 */
	public String addReply() {

		// 添加回复到当前评论
		Comment comment2 = commentService.getCommentById(cid);
		Blog blog = comment2.getBlog();
		Comment comment = new Comment();
		comment.setId(cid);
		// 设置回复与评论之间的关联关系
		model.setComment(comment);
		model.setSelf(user);
		if(userId == null){ //userId为空，表示是回复别人的回答
			model.setOther(comment2.getUser());
		} else{
			//userId不为空，表示是回复别人的追问，此时model.setOther(User)中的user
			//就不是comment2.getUser()了，主要是为了维护关联关系的正确性
			User other = userService.getEntity(userId);
			model.setOther(other);
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		model.setReplyTime(format.format(new Date()));
		replyService.saveReply(model);

		// 将评论消息添加到 Message 中
		Message message = new Message();
		message.setContent(model.getContent());
		message.setSelf(user);
		if(userId == null){ //userId为空，表示是回复别人的回答
			message.setOther(comment2.getUser());
		} else{
			//userId不为空，表示是回复别人的追问，此时model.setOther(User)中的user
			//就不是comment2.getUser()了，主要是为了维护关联关系的正确性
			User other = userService.getEntity(userId);
			message.setOther(other);
		}
		message.setStatus(true);// 1代表未读
		message.setReply(true);// 1代表是回复评论
		message.setBlog(blog);
		message.setCreateTime(format.format(new Date()));
		messageService.saveMessage(message);
		return "toMessageCenterPage";
	}

	/**
	 * 添加一条问题答案的追问
	 * 
	 * @return
	 */
	public String addQuestionReply() {
		// 添加追问到当前answer
		Answer answer2 = answerService.getAnswerById(aid);
		Question question = answer2.getQuestion();
		Answer answer = new Answer();
		answer.setId(aid);
		// 设置追问与answer之间的关联关系
		model.setAnswer(answer);
		model.setSelf(user);
		if(userId == null){ //userId为空，表示是回复别人的回答
			model.setOther(answer2.getUser());
		} else{
			//userId不为空，表示是回复别人的追问，此时model.setOther(User)中的user
			//就不是comment2.getUser()了，主要是为了维护关联关系的正确性
			User other = userService.getEntity(userId);
			model.setOther(other);
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		model.setReplyTime(format.format(new Date()));
		replyService.saveReply(model);

		// 将追问消息添加到 Message 中
		Message message = new Message();
		message.setContent(model.getContent());
		message.setSelf(user);
		if(userId == null){ //userId为空，表示是回复别人的回答
			message.setOther(answer2.getUser());
		} else {
			//userId不为空，表示是回复别人的追问，此时model.setOther(User)中的user
			//就不是comment2.getUser()了，主要是为了维护关联关系的正确性
			User other = userService.getEntity(userId);
			message.setOther(other);
		}
		message.setStatus(true);// 1代表未读
		message.setAddAsk(true);// 1代表是追问
		message.setQuestion(question);
		message.setCreateTime(format.format(new Date()));
		messageService.saveMessage(message);
		return "toMessageCenterPage";
	}
}
