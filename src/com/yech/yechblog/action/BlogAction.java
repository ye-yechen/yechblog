package com.yech.yechblog.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yech.yechblog.aware.UserAware;
import com.yech.yechblog.entity.Blog;
import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.BlogService;

/**
 * BlogAction
 * 
 * @author Administrator
 */
@Controller
@Scope("prototype")
public class BlogAction extends BaseAction<Blog> implements UserAware {

	private static final long serialVersionUID = 5190914411419980760L;
	/**
	 * 注入 SurveyService
	 */
	@Resource
	private BlogService blogService;

	// 接收 User 对象
	private User user;

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	// 博客列表
	private List<Blog> blogList;

	public List<Blog> getBlogList() {
		return blogList;
	}

	public void setBlogList(List<Blog> blogList) {
		this.blogList = blogList;
	}

	// 错误页指定
	private String inputPage;

	public String getInputPage() {
		return inputPage;
	}

	public void setInputPage(String inputPage) {
		this.inputPage = inputPage;
	}

	/**
	 * 查询当前用户所有博客
	 */
	public String queryAllBlogs() {
		blogList = blogService.batchFindEntityByHQL(user);
		return "allBlogsList";
	}

	/**
	 * 去到写博客界面
	 * @return
	 */
	public String toWriteBlogPage(){
		return "toWriteBlogPage";
	}
	/**
	 * 新建博客
	 */
	public String newBlog() {
		model.setUser(user);
		blogService.saveOrUpdateBlog(model);
		return "writeBlog";
	}
}
