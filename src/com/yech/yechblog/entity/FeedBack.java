package com.yech.yechblog.entity;

/**
 * 反馈
 * @author Administrator
 *
 */
public class FeedBack {

	private Integer id;
	private String content;
	private Boolean state; //反馈是否处理了
	private User user;//反馈人
	private String feedBackTime;//反馈时间
	private String soluteTime; //反馈解决时间
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
	public Boolean getState() {
		return state;
	}
	public void setState(Boolean state) {
		this.state = state;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getFeedBackTime() {
		return feedBackTime;
	}
	public void setFeedBackTime(String feedBackTime) {
		this.feedBackTime = feedBackTime;
	}
	public String getSoluteTime() {
		return soluteTime;
	}
	public void setSoluteTime(String soluteTime) {
		this.soluteTime = soluteTime;
	}
	
}
