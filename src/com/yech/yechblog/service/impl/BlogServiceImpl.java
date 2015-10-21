package com.yech.yechblog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yech.yechblog.dao.BaseDao;
import com.yech.yechblog.entity.Blog;
import com.yech.yechblog.entity.Comment;
import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.BlogService;

@Service("blogService")
public class BlogServiceImpl implements BlogService {

	@Resource(name="blogDao")
	private BaseDao<Blog> blogDao;

	@Resource(name="commentDao")
	private BaseDao<Comment> commentDao;
	/**
	 * 查找所有博客
	 */
	@Override
	public List<Blog> findMyBlogs(User user) {
		String hql = "from Blog b where b.user.id = ?";
		List<Blog> blogs = blogDao.batchFindEntityByHQL(hql,user.getId());
		//遍历blog所属的user，避免懒加载
		for(Blog blog : blogs){
			blog.getUser().getUsername();
		}
		return blogs;
	}

	/**
	 * 新建/编辑博客
	 * @return
	 */
	@Override
	public void saveOrUpdateBlog(Blog blog) {
		blogDao.saveOrUpdateEntity(blog);
	}

	/**
	 * 查询所有用户博客
	 * @return
	 */
	@Override
	public List<Blog> findAllBlogs() {
		String hql = "from Blog";
		List<Blog> blogs = blogDao.batchFindEntityByHQL(hql);
		//遍历blog所属的user，避免懒加载
		for(Blog blog : blogs){
			blog.getUser().getUsername();
		}
		return blogs;
	}

	/**
	 * 阅读全文
	 */
	@Override
	public Blog readDetail(Integer bid) {
		
		Blog blog =  blogDao.getEntity(bid);
		blog.getComments().size();
		//获取blog所属的user，避免懒加载
		blog.getUser().getUsername();
		return blog;
	}

	/**
	 * 为当前博客添加评论
	 * @param bid
	 */
	@Override
	public void addComment(Comment comment) {
		commentDao.saveOrUpdateEntity(comment);
	}

	/**
	 * 查询当前博客所有评论
	 */
	@Override
	public List<Comment> queryAllComments(Integer bid) {
		String hql = "from Comment c where c.blog.id = ?";
		List<Comment> comments = commentDao.batchFindEntityByHQL(hql, bid);
		for(Comment comment : comments){
			comment.getUser().getUsername();
		}
		return comments;
	}

	/**
	 * 统计博客总数
	 */
	@Override
	public int getBlogCount() {
		String hql = "select count(*) from Blog";
		return  ((Long)(blogDao.uniqueResult(hql))).intValue();
	}

	/**
	 * 查询指定页的bolg总数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> queryPage(int currentPageIndex, int countPerPage) {
		String hql = "SELECT * FROM blogs LIMIT ?,?";
		List<Blog> blogs = (List<Blog>) blogDao.listResult(Blog.class,hql, (currentPageIndex-1) * countPerPage,
							countPerPage);
		for(Blog blog : blogs){
			blog.getUser().getUsername();
			blog.getTags().size();
		}
		return blogs;
	}
	
	/**
	 * 在当前用户博客中查询指定页的bolg总数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> queryMyPage(User user,int currentPageIndex, int countPerPage) {
		String hql = "SELECT * FROM blogs WHERE userid = ? LIMIT ?,?";
		List<Blog> blogs = (List<Blog>) blogDao.listResult(Blog.class,hql,user.getId(), (currentPageIndex-1) * countPerPage,
				currentPageIndex * countPerPage);
		for(Blog blog : blogs){
			blog.getUser().getUsername();
		}
		return blogs;
	}

	/**
	 * 统计当前用户博客总数
	 * @return
	 */
	@Override
	public int getMyBlogCount(User user) {
		String hql = "select count(*) from Blog b where b.user.id = ?";
		return  ((Long)(blogDao.uniqueResult(hql,user.getId()))).intValue();
	}

	/**
	 * 根据 id 查出博客
	 * @return
	 */
	@Override
	public Blog getBlogById(Integer bid) {
		Blog blog = blogDao.getEntity(bid);
		blog.getUser().getUsername();
		return blog;
	}

	/**
	 * 根据传入的tagName 查找含有此标签的博客
	 * @return
	 */
	@Override
	public List<Blog> queryBlogsByTagName(String tagName) {
		String hql = "select t.blogs from Tag t where t.tagName = ?";
		List<Blog> blogs = blogDao.batchFindEntityByHQL(hql, tagName);
		for(Blog blog : blogs){
			blog.getComments().size();
			blog.getTags().size();
		}
		return blogs;
	}

}
