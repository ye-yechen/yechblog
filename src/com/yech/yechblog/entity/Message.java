package com.yech.yechblog.entity;

import java.sql.Timestamp;

/**
 * Message : 消息类，用于动态提醒
 * @author Administrator
 *
 */
public class Message {

	private Integer id;
	private String content;
	private User self;	//消息来自谁
	private User other; //消息发给谁
	private Boolean status; //状态 0已读 1未读
	private Timestamp createTime = new Timestamp(System.currentTimeMillis());
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
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public User getSelf() {
		return self;
	}
	public void setSelf(User self) {
		this.self = self;
	}
	public User getOther() {
		return other;
	}
	public void setOther(User other) {
		this.other = other;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
}
