package com.yech.yechblog.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yech.yechblog.aware.UserAware;
import com.yech.yechblog.entity.Blog;
import com.yech.yechblog.entity.Comment;
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

	// 当前用户的博客列表
	private List<Blog> myBlogList;

	// 所有博客列表
	private List<Blog> allBlogList;

	// 当前博客的评论列表
	private List<Comment> allComments;

	public List<Comment> getAllComments() {
		return allComments;
	}

	public void setAllComments(List<Comment> allComments) {
		this.allComments = allComments;
	}

	public List<Blog> getAllBlogList() {
		return allBlogList;
	}

	public void setAllBlogList(List<Blog> allBlogList) {
		this.allBlogList = allBlogList;
	}

	public List<Blog> getMyBlogList() {
		return myBlogList;
	}

	public void setMyBlogList(List<Blog> myBlogList) {
		this.myBlogList = myBlogList;
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
	 * 查询所有用户的博客，用于在首页显示
	 * 
	 * @return
	 */
	public String queryAllBlogs() {
		allBlogList = blogService.findAllBlogs();
		return "allBlogList";
	}

	/**
	 * 查询当前用户所有博客
	 */
	public String queryMyBlogs() {
		myBlogList = blogService.findMyBlogs(user);
		return "myBlogList";
	}

	/**
	 * 去到写博客界面
	 * 
	 * @return
	 */
	public String toWriteBlogPage() {
		return "toWriteBlogPage";
	}

	/**
	 * 新建博客
	 */
	public String newBlog() {
		// 去掉CKEditor自动在文本上添加的<p></p>标签
		model.setContent(model.getContent().replace("<p>", "")
				.replace("</p>", ""));
		model.setUser(user);
		blogService.saveOrUpdateBlog(model);
		return "BlogAction";
	}

	// 接收页面的bid(blog id)
	private Integer bid;

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	/**
	 * 阅读全文
	 */
	public String readDetail() {
		model = blogService.readDetail(bid);
		allComments = blogService.queryAllComments(bid);
		return "detailReadPage";
	}
}
