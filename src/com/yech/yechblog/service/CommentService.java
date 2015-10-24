package com.yech.yechblog.service;

import com.yech.yechblog.entity.Comment;

public interface CommentService {

	/**
	 * ¸ù¾Ý id ²éÑ¯comment
	 * @return
	 */
	public Comment getCommentById(Integer cid);

}
