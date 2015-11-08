package com.yech.yechblog.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yech.yechblog.aware.UserAware;
import com.yech.yechblog.entity.Answer;
import com.yech.yechblog.entity.Message;
import com.yech.yechblog.entity.Question;
import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.AnswerService;
import com.yech.yechblog.service.MessageService;
import com.yech.yechblog.service.QuestionService;

@Controller
@Scope("prototype")
public class AnswerAction extends BaseAction<Answer> implements UserAware {

	private static final long serialVersionUID = 6669153036581044844L;

	@Resource
	private QuestionService questionService;

	@Resource
	private AnswerService answerService;
	
	@Resource
	private MessageService messageService;
	// 接收 User 对象
	private User user;

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	private Integer qid;

	public Integer getQid() {
		return qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	/**
	 * 为当question添加回答
	 */
	public String addAnswer() {
		// 设置回答与问题的关联关系
		model.setUser(user);
		Question question2 = questionService.getQuestionById(qid);//为了得到此问题的提问者
		Question question = new Question();
		question.setId(qid);
		model.setQuestion(question);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		model.setAnswerTime(format.format(new Date()));
		question.getAnswers().add(model);
		answerService.saveAnswer(model);

		// 将评论消息添加到 Message 中
		Message message = new Message();
		message.setContent(model.getContent());
		message.setSelf(user);
		message.setOther(question2.getUser());
		message.setAnswer(true);//动态类型是回答
		message.setStatus(true);// 1代表未读
		message.setQuestion(question2);
		message.setCreateTime(format.format(new Date()));
		messageService.saveMessage(message);
		return "toDetailReadPage";
	}
}
