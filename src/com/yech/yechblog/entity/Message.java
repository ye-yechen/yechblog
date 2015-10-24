package com.yech.yechblog.entity;


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
	private Blog blog;//消息是关于哪篇博客
	private String createTime;
	private Boolean comment=false; //评论
	private Boolean love=false;//赞
	private Boolean collect=false;//收藏
	private Boolean share=false; //分享
	private Boolean reply=false;//回复评论
	
	public Boolean getReply() {
		return reply;
	}
	public void setReply(Boolean reply) {
		this.reply = reply;
	}
	public Boolean getComment() {
		return comment;
	}
	public void setComment(Boolean comment) {
		this.comment = comment;
	}
	
	public Boolean getLove() {
		return love;
	}
	public void setLove(Boolean love) {
		this.love = love;
	}
	public Boolean getCollect() {
		return collect;
	}
	public void setCollect(Boolean collect) {
		this.collect = collect;
	}
	public Boolean getShare() {
		return share;
	}
	public void setShare(Boolean share) {
		this.share = share;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
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
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
}
