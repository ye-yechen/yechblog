package com.yech.yechblog.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 问题(问答社区)
 * 
 * @author Administrator
 *
 */
public class Question {

	private Integer id;
	private String title;
	private String content;
	private String createTime;
	private Integer readCount;// 问题浏览次数
	private Boolean deleted;// 是否删除
	private String category;// 问题所属分类

	// 问题到答案的一对多映射
	private Set<Answer> answers = new HashSet<Answer>();

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	private User user;// 提问者

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
