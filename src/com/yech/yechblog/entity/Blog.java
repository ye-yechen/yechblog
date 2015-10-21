package com.yech.yechblog.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 博客类
 * @author Administrator
 *
 */
public class Blog{

	// ID
	private Integer id;

	// 文章内容
	private String content;

	// 文章标题
	private String title;

	// 文章分类
	private Integer category;

	// 创建时间
	private String createTime;
	
	//评论
	//建立 Blog 到 Comment 到一对多关系
	private Set<Comment> comments = new HashSet<Comment>();
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	// 建立 Blog 到 User 之间 多对一 关联关系
	private User user;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	// 是否已删除,默认0表未删除,1表示删除
	private Integer deleted;

	// 阅读数量
	private Integer readCount;
	
	//blog 到 Tag 的多对多关系
	private Set<Tag> tags = new HashSet<Tag>();
	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
}
