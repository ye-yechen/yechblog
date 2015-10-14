package com.yech.yechblog.entity;

import java.sql.Timestamp;

/**
 * 评论类
 * @author Administrator
 *
 */
public class Comment {
	private Integer id;
	private String content;//内容
	private User user;//评论人
	private Timestamp commentTime = new Timestamp(System.currentTimeMillis());;//评论时间
	private String imageUrl;//评论的图片的url
	//评论所对应的blog
	private Blog blog;
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Timestamp getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Timestamp commentTime) {
		this.commentTime = commentTime;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
