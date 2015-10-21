package com.yech.yechblog.service;

import java.util.List;

import com.yech.yechblog.entity.Blog;
import com.yech.yechblog.entity.Comment;
import com.yech.yechblog.entity.User;

/**
 * 
 * @author Administrator
 *
 */
public interface BlogService{

	/**
	 * 查找当前用户所有博客
	 * @param user 
	 */
	public List<Blog> findMyBlogs(User user);

	/**
	 * 新建/编辑博客
	 * @return
	 */
	public void saveOrUpdateBlog(Blog model);

	/**
	 * 查询所有用户博客
	 * @return
	 */
	public List<Blog> findAllBlogs();

	/**
	 * 阅读全文
	 * @param model
	 */
	public Blog readDetail(Integer bid);

	/**
	 * 为当前博客添加评论
	 * @param model
	 */
	public void addComment(Comment model);

	/**
	 * 查询当前博客所有评论
	 */
	public List<Comment> queryAllComments(Integer bid);

	/**
	 * 统计博客总数
	 * @return
	 */
	public int getBlogCount();
	
	/**
	 * 在所有博客中查询指定页的bolg总数
	 * @return
	 */
	public List<Blog> queryPage(int currentPageIndex,int countPerPage);

	/**
	 * 在当前用户博客中查询指定页的bolg总数
	 * @return
	 */
	public List<Blog> queryMyPage(User user,int currentPageIndex, int countPerPage);
	/**
	 * 统计当前用户博客总数
	 * @return
	 */
	public int getMyBlogCount(User user);

	/**
	 * 根据 id 查出博客
	 * @return
	 */
	public Blog getBlogById(Integer bid);

	/**
	 * 根据传入的tagName 查找含有此标签的博客
	 * @return
	 */
	public List<Blog> queryBlogsByTagName(String tagName);
	
}
