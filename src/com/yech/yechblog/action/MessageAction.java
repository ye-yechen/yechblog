package com.yech.yechblog.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yech.yechblog.aware.UserAware;
import com.yech.yechblog.entity.Answer;
import com.yech.yechblog.entity.Comment;
import com.yech.yechblog.entity.Message;
import com.yech.yechblog.entity.Reply;
import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.AnswerService;
import com.yech.yechblog.service.BlogService;
import com.yech.yechblog.service.MessageService;
import com.yech.yechblog.service.QuestionService;
import com.yech.yechblog.service.ReplyService;

/**
 * CommentAction
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class MessageAction extends BaseAction<Message> implements UserAware {

	private static final long serialVersionUID = -5672583535553188385L;

	@Resource
	private MessageService messageService;

	@Resource
	private BlogService blogService;

	@Resource
	private ReplyService replyService;
	
	@Resource
	private AnswerService answerService;
	
	@Resource
	private QuestionService questionService;
	//所有的评论类型的消息
	private List<Comment> comments;
	//所有的answer类型的消息
	private List<Answer> answers;
	
	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	//所有回复类型的消息
	//评论的回复--- Map<评论id,对应评论的回复>
	private Map<Integer, List<Reply>> allReplies = 
					new HashMap<Integer, List<Reply>>();

	private Map<Integer, List<Reply>> allQuestionReplies = 
					new HashMap<Integer, List<Reply>>();
	
	public Map<Integer, List<Reply>> getAllQuestionReplies() {
		return allQuestionReplies;
	}

	public void setAllQuestionReplies(Map<Integer, List<Reply>> allQuestionReplies) {
		this.allQuestionReplies = allQuestionReplies;
	}

	public Map<Integer, List<Reply>> getAllReplies() {
		return allReplies;
	}

	public void setAllReplies(Map<Integer, List<Reply>> allReplies) {
		this.allReplies = allReplies;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	// 接收页面传过来的 message 的 id
	private Integer mid;
	private Integer bid;//博客id
	private Integer qid;//问题id
	
	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	// 不能去掉，否则被LoginInteceptor 拦截了
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

	/**
	 * 去到此条消息的详细页面
	 * 
	 * @return
	 */
	public String toDetailMessage() {
		// 将 message 表中的 status 字段设为 0 ，表示已读
		messageService.changeMessageStatus(mid);
		model = messageService.getMessageById(mid);
		if(model.getAnswer() || model.getAddAsk()){ //是 answer 类型消息
			answers = answerService.queryAllAnswers(qid);
			for(Answer answer : answers){
				List<Reply> replies = replyService.queryAllQuestionReplies(answer.getId());
				allQuestionReplies.put(answer.getId(), replies);
			}
			model.setQuestion(questionService.getQuestionById(qid));
		} else { //不是answer类型的消息
			comments = blogService.queryAllComments(bid);// 查询评论信息，用于在详细消息页面显示
			for(Comment comment : comments){ //查询每个评论对应的回复
				List<Reply> replies = replyService.queryAllReplies(comment.getId());
				allReplies.put(comment.getId(), replies);
			}
			model.setBlog(blogService.getBlogById(bid));
		}
		return "toDetailMessagePage";
	}

}
