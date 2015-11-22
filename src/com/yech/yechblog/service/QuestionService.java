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

	/**
	 * 删除问题
	 * @param qid
	 */
	public void deleteQuestion(Integer qid);

	/**
	 * 更新问题
	 * @param model
	 */
	public void updateQuestion(Question model);

	/**
	 * 根据id查询此用户的所有问题
	 */
	public List<Question> queryHisQuestions(Integer userId);

	/**
	 * 查询所有问题
	 * @return
	 */
	public List<Question> queryAllQuestions();

	/**
	 * 查询当前用户的问题列表
	 */
	public int getMyQuestionCount(Integer userId);

	/**
	 * 在当前用户问题中查询指定页的question总数
	 */
	public List<Question> queryMyPage(Integer userId, int currentPageIndex,
			int countPerPage);

}
