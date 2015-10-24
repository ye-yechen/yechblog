package com.yech.yechblog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yech.yechblog.dao.BaseDao;
import com.yech.yechblog.entity.Comment;
import com.yech.yechblog.service.CommentService;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Resource(name="commentDao")
	private BaseDao<Comment> commentDao;
	/**
	 * ¸ù¾Ý id ²éÑ¯comment
	 * @return
	 */
	@Override
	public Comment getCommentById(Integer cid) {
		Comment comment = commentDao.getEntity(cid);
		comment.getBlog().getId();
		comment.getBlog().getUser().getUsername();
		return comment;
	}

}
