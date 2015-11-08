package com.yech.yechblog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yech.yechblog.dao.BaseDao;
import com.yech.yechblog.entity.Reply;
import com.yech.yechblog.service.ReplyService;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

	@Resource(name="replyDao")
	private BaseDao<Reply> replyDao;
	
	/**
	 * 保存reply
	 */
	@Override
	public void saveReply(Reply reply) {
		replyDao.saveOrUpdateEntity(reply);
	}

	/**
	 * 根据当前评论id查询此评论的所有回复
	 * @return
	 */
	@Override
	public List<Reply> queryAllReplies(Integer cid) {
		String hql = "from Reply r where r.comment.id = ?";
		List<Reply> replies = replyDao.batchFindEntityByHQL(hql, cid);
		for(Reply reply : replies){
			reply.getSelf().getUsername();
			reply.getOther().getUsername();
		}
		return replies;
	}

	/**
	 * 根据当前answer id查询此答案的所有追问
	 * @return
	 */
	@Override
	public List<Reply> queryAllQuestionReplies(Integer aid) {
		String hql = "from Reply r where r.answer.id = ?";
		List<Reply> replies = replyDao.batchFindEntityByHQL(hql, aid);
		for(Reply reply : replies){
			reply.getSelf().getUsername();
			reply.getOther().getUsername();
		}
		return replies;
	}

}
