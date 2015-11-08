package com.yech.yechblog.service;

import java.util.List;

import com.yech.yechblog.entity.Answer;

public interface AnswerService {

	/**
	 * 保存答案
	 */
	public void saveAnswer(Answer model);

	/**
	 * 查询当前问题的所有回答
	 */
	public List<Answer> queryAllAnswers(Integer qid);

	/**
	 * 根据id查询answer
	 */
	public Answer getAnswerById(Integer aid);

}
