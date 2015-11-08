package com.yech.yechblog.entity;

/**
 * 答案(问答社区)
 * @author Administrator
 *
 */
public class Answer {

	private Integer id;
	private String content;
	private String answerTime;//回答时间
	private Question question;//所属的问题
	private User user;//回答者
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
