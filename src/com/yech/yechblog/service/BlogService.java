package com.yech.yechblog.service;

import java.util.List;

import com.yech.yechblog.entity.Blog;
import com.yech.yechblog.entity.User;

/**
 * 
 * @author Administrator
 *
 */
public interface BlogService{

	/**
	 * 查找所有博客
	 * @param user 
	 */
	public List<Blog> batchFindEntityByHQL(User user);

	/**
	 * 新建/编辑博客
	 * @return
	 */
	public void saveOrUpdateBlog(Blog model);

}
