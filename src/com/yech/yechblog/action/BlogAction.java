package com.yech.yechblog.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yech.yechblog.aware.UserAware;
import com.yech.yechblog.entity.Blog;
import com.yech.yechblog.entity.Collection;
import com.yech.yechblog.entity.Comment;
import com.yech.yechblog.entity.Message;
import com.yech.yechblog.entity.Question;
import com.yech.yechblog.entity.Relation;
import com.yech.yechblog.entity.Reply;
import com.yech.yechblog.entity.Tag;
import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.BlogService;
import com.yech.yechblog.service.CollectionService;
import com.yech.yechblog.service.MessageService;
import com.yech.yechblog.service.QuestionService;
import com.yech.yechblog.service.RelationService;
import com.yech.yechblog.service.ReplyService;
import com.yech.yechblog.service.TagService;
import com.yech.yechblog.util.StringUtil;

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

	@Resource
	private TagService tagService;

	@Resource
	private MessageService messageService;

	@Resource
	private ReplyService replyService;
	
	@Resource
	private CollectionService collectionService;
	
	@Resource
	private RelationService relationService;
	
	@Resource
	private QuestionService questionService;
	// 接收 User 对象
	private User user;

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	// 当前用户的博客列表
	private List<Blog> myBlogList;

	//
	// 所有博客列表
	private List<Blog> allBlogList;

	// 当前博客的评论列表
	private List<Comment> allComments;
	// 具有相同标签的博客列表
	private List<Blog> similarBlogList;
	// 当前用户的动态(评论了谁、赞了谁、收藏了谁...)
	private List<Message> allMessages;
	//当前用户的所有收藏
	private List<Collection> allCollections;
	//根据用户搜索匹配的博客列表
	private List<Blog> matchedBlogList;
	//我关注的人列表
	private List<Relation> allRelations;
	//所有关注我的人
	private List<Relation> allFocusMe;
	//当前用户提的问题列表
	private List<Question> allQuestions;
	
	public List<Question> getAllQuestions() {
		return allQuestions;
	}

	public void setAllQuestions(List<Question> allQuestions) {
		this.allQuestions = allQuestions;
	}

	public List<Relation> getAllFocusMe() {
		return allFocusMe;
	}

	public void setAllFocusMe(List<Relation> allFocusMe) {
		this.allFocusMe = allFocusMe;
	}

	public List<Relation> getAllRelations() {
		return allRelations;
	}

	public void setAllRelations(List<Relation> allRelations) {
		this.allRelations = allRelations;
	}

	public List<Blog> getMatchedBlogList() {
		return matchedBlogList;
	}

	public void setMatchedBlogList(List<Blog> matchedBlogList) {
		this.matchedBlogList = matchedBlogList;
	}

	public List<Collection> getAllCollections() {
		return allCollections;
	}

	public void setAllCollections(List<Collection> allCollections) {
		this.allCollections = allCollections;
	}

	// 当前博客对应评论的回复--- Map<评论id,对应评论的回复>
	private Map<Integer, List<Reply>> allReplies = new HashMap<Integer, List<Reply>>();

	public Map<Integer, List<Reply>> getAllReplies() {
		return allReplies;
	}

	public void setAllReplies(Map<Integer, List<Reply>> allReplies) {
		this.allReplies = allReplies;
	}

	public List<Message> getAllMessages() {
		return allMessages;
	}

	public void setAllMessages(List<Message> allMessages) {
		this.allMessages = allMessages;
	}

	public List<Blog> getSimilarBlogList() {
		return similarBlogList;
	}

	public void setSimilarBlogList(List<Blog> similarBlogList) {
		this.similarBlogList = similarBlogList;
	}

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

	// 接收页面传过来的博客的标签
	private String myTags;

	public String getMyTags() {
		return myTags;
	}

	public void setMyTags(String myTags) {
		this.myTags = myTags;
	}

	/**
	 * 新建博客
	 */
	public String newBlog() {
//		// 去掉CKEditor自动在文本上添加的<p></p>标签
//		model.setContent(model.getContent().replace("<p>", "")
//				.replace("</p>", ""));
		//如果没写summary
		if(model.getSummary().trim().equals("")){
			//截取内容的前200个字符作为博客的summary
			model.setSummary(model.getContent().substring(0, 400));
		}
		model.setAllowComment(true);//默认允许评论
		model.setUser(user);
		model.setReadCount(0);// 设置阅读次数
		model.setDeleted(0);// 设置未删除(逻辑删除)
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		model.setCreateTime(format.format(new Date()));
		String[] tagArr = StringUtil.str2Arr(myTags, ","); // 以逗号分割字符串
		Tag blogTag = null;
		if (tagArr != null && tagArr.length > 0) {
			for (String str : tagArr) {
				// 保存标签
				blogTag = new Tag();
				blogTag.setTagName(str);
				blogTag.setCreateTime(format.format(new Date()));
				blogTag.setTagDesc("*" + str + "*");
				blogTag.getBlogs().add(model);
				model.getTags().add(blogTag);
				blogService.saveOrUpdateBlog(model);
				tagService.saveTag(blogTag);
			}
		} else {
			blogService.saveOrUpdateBlog(model);
		}
		return "BlogAction";
	}

	// /**
	// * 查询博客所属的标签
	// * @return
	// */
	// public List<Tag> queryBlogTags(){
	// return tagService.queryBlogTags(bid);
	// }
	//
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
		model.setReadCount(model.getReadCount() + 1);
		blogService.saveOrUpdateBlog(model);// 更新博客的阅读次数
		allComments = blogService.queryAllComments(bid);
		//查询当前评论的所有回复
		for (Comment comment : allComments) {
			List<Reply> replies = replyService.queryAllReplies(comment.getId());
			allReplies.put(comment.getId(), replies);
		}
		return "detailReadPage";
	}

	// 接收页面参数，默认第一页
	private String pageIndex;

	// 当前页数
	private int currentPageIndex;
	// 总页数
	private int pageCount;

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	public void setCurrentPageIndex(int currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
	}

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * 所有博客列表分页显示
	 * 
	 * @return
	 */
	public String pagination() {
		int countPerPage = 5;// 每页显示5条
		if (pageIndex == null) {
			pageIndex = "1";
		}
		currentPageIndex = Integer.parseInt(pageIndex);
		int blogCount = blogService.getBlogCount();// 查询博客总数
		// 显示在当前页的博客
		allBlogList = blogService.queryPage(currentPageIndex, countPerPage);
		// 总页数
		pageCount = (blogCount % countPerPage == 0 ? blogCount / countPerPage
				: (blogCount / countPerPage + 1));
		if(blogCount == 0){
			pageCount = 1;	//为了在页面上不显示“第1页/共0页”这种效果
		}
		return "allBlogList";
	}

	/**
	 * 当前用户博客列表分页显示
	 * 
	 * @return
	 */
	public String myPagination() {
		int countPerPage = 5;// 每页显示5条
		if (pageIndex == null) {
			pageIndex = "1";
		}
		currentPageIndex = Integer.parseInt(pageIndex);
		int blogCount = blogService.getMyBlogCount(user);// 查询博客总数
		// 显示在当前页的博客
		myBlogList = blogService.queryMyPage(user, currentPageIndex,
				countPerPage);
		// 总页数
		pageCount = (blogCount % countPerPage == 0 ? blogCount / countPerPage
				: (blogCount / countPerPage + 1));
		if(blogCount == 0){
			pageCount = 1;	//为了在页面上不显示“第1页/共0页”这种效果
		}
		return "myBlogList";
	}

	/**
	 * 去个人主页
	 * 
	 * @return
	 */
	public String toPersonalPage() {
		// 去个人主页的时候查询当前用户的动态
		allMessages = messageService.queryUserActivities(user);
		myBlogList = blogService.findMyBlogs(user);
		allCollections = collectionService.findMyCollections(user);
		allRelations = relationService.queryAllRelations(user);
		allFocusMe = relationService.queryAllMyFocus(user);
		allQuestions = questionService.queryAllMyQuestions(user);
		return "personalPage";
	}

	public String tagName;

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	/**
	 * 根据传入的tagName 查找含有此标签的博客
	 * @return
	 */
	public String queryBlogsByTagName() {
		allBlogList = blogService.queryBlogsByTagName(tagName);
		return "similarBlogs";
	}

	/**
	 * 有相同标签的博客列表分页显示
	 * 
	 * @return
	 */
	public String similarBlogsPagination() {
		int countPerPage = 5;// 每页显示5条
		if (pageIndex == null) {
			pageIndex = "1";
		}
		currentPageIndex = Integer.parseInt(pageIndex);
		int blogCount = blogService.getSimilarBlogCount(tagName);// 查询具有相同标签的博客总数
		// 显示在当前页的博客
		similarBlogList = blogService.querySimilarBlogPage(tagName,
				currentPageIndex, countPerPage);
		// 总页数
		pageCount = (blogCount % countPerPage == 0 ? blogCount / countPerPage
				: (blogCount / countPerPage + 1));
		if(blogCount == 0){
			pageCount = 1;	//为了在页面上不显示“第1页/共0页”这种效果
		}
		return "similarBlogs";
	}

	/**
	 * 编辑博客
	 */
	public String editBlog() {
		// 得到要编辑的博客
		model = blogService.getBlogById(bid);
		return "editBlogPage";
	}

	/**
	 * 更新blog
	 */
	public String updateBlog() {
		model.setId(bid);
		model.setUser(user);// 保持关联关系
		model.setDeleted(0);
		model.setReadCount(0);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		model.setCreateTime(format.format(new Date()));
		blogService.updateBlog(model);
		return "redirectToPersonalPage";
	}

	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * 删除blog(逻辑删除)
	 * 处理 ajax 请求
	 * 将删除后的信息以流的方式返回，以便 ajax 代码调用(struts2 中 ajax的用法)
	 * 删除成功返回 1，否则返回0
	 */
	public String deleteBlog() {
		try {
			blogService.deleteBlog(bid);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (Exception e) {
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "ajax-success";
	}

	/**
	 * 将博客添加到我的收藏
	 * 
	 * @return
	 */
	public String addToCollections() {
		try {
			// 如果没有收藏这个博客
			if (!collectionService.queryBlogInCollection(user,bid)) {
				Blog blog2 = blogService.getBlogById(bid);
				Blog blog = new Blog();
				blog.setId(bid);
				Collection collection = new Collection();
				collection.setBlog(blog);
				collection.setDeleted(false);//未删除
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				collection.setCollectTime(format.format(new Date()));
				collection.setSelf(user);
				collection.setOther(blog2.getUser());
				collectionService.saveCollection(collection);
				
				// 将收藏消息添加到 Message 中
				Message message = new Message();
				message.setContent("收藏了博客!");
				message.setSelf(user);
				message.setOther(blog2.getUser());
				message.setCollect(true); // 消息是收藏
				message.setStatus(true);// 1代表未读
				message.setBlog(blog2);
				message.setCreateTime(format.format(new Date()));
				messageService.saveMessage(message);
				inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
			} else {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			}
			
		} catch (Exception e) {
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "ajax-success";
	}
	
	/**
	 * 删除收藏的博客
	 * @return
	 */
	public String removeTheCollection(){
		try {
			collectionService.removeBlogFromCollections(user,bid);
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (Exception e) {
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return "ajax-success";
	}
	
	//搜索条件
	private String searchCondition;
	
	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	/**
	 * 根据用户的输入搜索博客
	 * @return
	 */
	public String searchBlog(){
		
		int countPerPage = 5;// 每页显示5条
		if (pageIndex == null) {
			pageIndex = "1";
		}
		currentPageIndex = Integer.parseInt(pageIndex);
		int blogCount = blogService.getMatchedBlogCount(searchCondition);// 查询匹配的博客总数
		System.out.println("llll"+blogCount);
		// 显示在当前页的博客
		matchedBlogList = 
				blogService.searchBlogByCondition(currentPageIndex,
											countPerPage,searchCondition);
		// 总页数
		pageCount = (blogCount % countPerPage == 0 ? blogCount / countPerPage
				: (blogCount / countPerPage + 1));
		
		if(blogCount == 0){
			pageCount = 1;	//为了在页面上不显示“第1页/共0页”这种效果
		}
		return "toMatchedBlogPage";
	}
	
	/**
	 * 改变博客的评论权限(是否可评论)
	 * @return
	 */
	public String changeAllowState(){
		Blog blog = blogService.getBlogById(bid);
		if(blog.getAllowComment()){ //如果博客现在是可评论的
			//设置为不可评论
			blogService.changeBlogAllowState(bid,false);
		} else {
			//设置为可评论
			blogService.changeBlogAllowState(bid,true);
		}
		return "redirectToPersonalPage";
	}

}
