package com.yech.yechblog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yech.yechblog.dao.BaseDao;
import com.yech.yechblog.entity.Blog;
import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.BlogService;

@Service("blogService")
public class BlogServiceImpl implements BlogService {

	@Resource(name="blogDao")
	private BaseDao<Blog> blogDao;

	/**
	 * 查找所有博客
	 */
	@Override
	public List<Blog> batchFindEntityByHQL(User user) {
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

}
