package com.yech.yechblog.service;

import java.util.List;

import com.yech.yechblog.entity.Question;
import com.yech.yechblog.entity.User;

public interface QuestionService {

	/**
	 * 统计question总数
	 */
	public int getQuestionCount();

	/**
	 * 查询指定页的question总数
	 * @return
	 */
	public List<Question> queryPage(int currentPageIndex, int countPerPage);

	/**
	 * 保存问题
	 */
	public void saveOrUpdateQuestion(Question model);

	/**
	 * 查看问题详情
	 */
	public Question readDetail(Integer qid);

	/**
	 * 查询具有相同分类的问题总数
	 */
	public int getSimilarQuestionCount(String categoryName);

	/**
	 * 在具有相同分类的问题中查询指定页的question总数
	 */
	public List<Question> querySimilarQuestionPage(String categoryName,
			int currentPageIndex, int countPerPage);

	/**
	 * 根据问题id获取question
	 */
	public Question getQuestionById(Integer qid);

	/**
	 * 查询当前用户所有问题列表
	 * @return
	 */
	public List<Question> queryAllMyQuestions(User user);
	
}
