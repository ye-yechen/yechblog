package com.yech.yechblog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yech.yechblog.dao.BaseDao;
import com.yech.yechblog.entity.Question;
import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.QuestionService;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

	@Resource(name="questionDao")
	private BaseDao<Question> questionDao;
	/**
	 * 统计question总数
	 */
	@Override
	public int getQuestionCount() {
		String hql = "select count(*) from Question q where q.deleted = 0";
		return  ((Long)(questionDao.uniqueResult(hql))).intValue();
	}
	
	/**
	 * 查询指定页的question总数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Question> queryPage(int currentPageIndex, int countPerPage) {
		String hql = "SELECT * FROM questions where deleted = 0 order by create_time desc LIMIT ?,?";
		List<Question> questions = questionDao.listResult(Question.class, hql, 
											(currentPageIndex-1) * countPerPage,countPerPage);
		for(Question question : questions){
			question.getUser().getUsername();
			question.getUser().getId();
			question.getAnswers().size();//查询答案的个数
		}
		return questions;
	}

	/**
	 * 保存问题
	 */
	@Override
	public void saveOrUpdateQuestion(Question model) {
		questionDao.saveOrUpdateEntity(model);
	}

	/**
	 * 查看问题详情
	 */
	@Override
	public Question readDetail(Integer qid) {
		Question question = questionDao.getEntity(qid);
		question.getUser().getUsername();
		return question;
	}

	/**
	 * 查询具有相同分类的问题总数
	 */
	@Override
	public int getSimilarQuestionCount(String categoryName) {
		String hql = "select count(t.tagName) from Tag t where t.tagName = ?";
		return  ((Long)(questionDao.uniqueResult(hql,categoryName))).intValue();
	}

	/**
	 * 在具有相同分类的问题中查询指定页的question总数
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Question> querySimilarQuestionPage(String categoryName,
			int currentPageIndex, int countPerPage) {
		String sql = "select * from questions q where q.category = ? "
				+ "and q.deleted = 0 order by q.create_time desc limit ?,?";
		List<Question> questions = 
				questionDao.listResult(Question.class, sql, categoryName,
						(currentPageIndex-1) * countPerPage,countPerPage);
		for(Question question : questions){
			question.getUser().getUsername();
			question.getAnswers().size();//查询当前问题的回答次数
		}
		return questions;
	}

	/**
	 * 根据问题id获取question
	 */
	@Override
	public Question getQuestionById(Integer qid) {
		Question question = questionDao.getEntity(qid);
		question.getUser().getUsername();
		return question;
	}

	/**
	 * 查询当前用户所有问题列表
	 * @return
	 */
	@Override
	public List<Question> queryAllMyQuestions(User user) {
		String hql = "from Question q where q.user.id = ? "
				+ "and q.deleted = 0 order by q.createTime desc";
		List<Question> questions = 
				questionDao.batchFindEntityByHQL(hql, user.getId());
		for(Question question : questions){
			question.getAnswers().size();
			question.getUser().getUsername();
		}
		return questions;
	}

	/**
	 * 删除问题
	 */
	@Override
	public void deleteQuestion(Integer qid) {
		String hql = "update Question q set q.deleted = 1 where q.id = ?";
		questionDao.batchUpdateEntityByHQL(hql, qid);
	}

	/**
	 * 更新问题
	 */
	@Override
	public void updateQuestion(Question question) {
		questionDao.updateEntity(question);
	}

	/**
	 * 根据id查询此用户的所有问题
	 */
	@Override
	public List<Question> queryHisQuestions(Integer userId) {
		String hql = "from Question q where q.user.id = ? and q.deleted = 0 "
				+ "order by q.createTime desc";
		List<Question> questions = questionDao.batchFindEntityByHQL(hql, userId);
		for(Question question : questions){
			question.getAnswers().size();
			question.getUser().getUsername();
		}
		return questions;
	}

	/**
	 * 查询所有问题
	 * @return
	 */
	@Override
	public List<Question> queryAllQuestions() {
		String hql = "from Question q where q.deleted = 0";
		List<Question> questions = questionDao.batchFindEntityByHQL(hql);
		questions.size();
		return questions;
	}

	/**
	 * 查询当前用户的问题总数
	 */
	@Override
	public int getMyQuestionCount(Integer userId) {
		String hql = "select count(*) from Question q where q.deleted = 0 "
					+ "and q.user.id = ?";
		return  ((Long)(questionDao.uniqueResult(hql,userId))).intValue();
	}

	/**
	 * 在当前用户问题中查询指定页的question总数
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Question> queryMyPage(Integer userId, int currentPageIndex,
			int countPerPage) {
		String hql = "SELECT * FROM questions WHERE userid = ? and deleted = 0 "
				+ "order by create_time desc LIMIT ?,?";
		List<Question> questions = questionDao.listResult(Question.class, hql, userId,
				(currentPageIndex-1) * countPerPage,countPerPage);
		for(Question question : questions){
			question.getAnswers().size();
			question.getUser().getUsername();
		}
		return questions;
	}

}
