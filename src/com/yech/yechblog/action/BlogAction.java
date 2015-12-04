package com.yech.yechblog.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
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
import com.yech.yechblog.service.CommentService;
import com.yech.yechblog.service.MessageService;
import com.yech.yechblog.service.QuestionService;
import com.yech.yechblog.service.RelationService;
import com.yech.yechblog.service.ReplyService;
import com.yech.yechblog.service.TagService;
import com.yech.yechblog.service.UserService;
import com.yech.yechblog.util.AddressUtil;
import com.yech.yechblog.util.Global;
import com.yech.yechblog.util.StringUtil;

/**
 * BlogAction
 * 
 * @author Administrator
 */
@Controller
@Scope("prototype")
public class BlogAction extends BaseAction<Blog> implements UserAware,
		ServletRequestAware,ApplicationAware,SessionAware,ServletResponseAware
{

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

	@Resource
	private CommentService commentService;

	@Resource
	private UserService userService;
	// 接收 User 对象
	private User user;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map<String, Object> application;
	private Map<String, Object> sessionMap;
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}
	@Override
	public void setApplication(Map<String, Object> arg0) {
		this.application = arg0;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}
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
	// 具有相同标签的博客列表
	private List<Blog> similarBlogList;
	// 当前用户的动态(评论了谁、赞了谁、收藏了谁...)
	private List<Message> allMessages;
	// 当前用户的所有收藏
	private List<Collection> allCollections;
	// 根据用户搜索匹配的博客列表
	private List<Blog> matchedBlogList;
	// 我关注的人列表
	private List<Relation> allRelations;
	// 所有关注我的人
	private List<Relation> allFocusMe;
	// 当前用户提的问题列表
	private List<Question> allQuestions;
	// 用户地址(根据ip得到的地址) Map<"ip","地址(国，省，市)">
	private Map<String, String> userAddr = new HashMap<String, String>();
	// 保存用户信息(Map<"用户名",Map<"ip","地址">>)
	private static Map<String, Map<String, String>> userInfo = new HashMap<String, Map<String, String>>();
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
		// // 去掉CKEditor自动在文本上添加的<p></p>标签
		// model.setContent(model.getContent().replace("<p>", "")
		// .replace("</p>", ""));
		// 如果没写summary
		if (model.getSummary().trim().equals("")) {
			// 截取内容的前200个字符作为博客的summary
			model.setSummary(model.getContent().substring(0, 400));
		}
		model.setAllowComment(true);// 默认允许评论
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
		// 查询当前评论的所有回复
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
		autoLogin();//如果记住了密码就自动登录
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
		if (blogCount == 0) {
			pageCount = 1; // 为了在页面上不显示“第1页/共0页”这种效果
		}
		return "allBlogList";
	}

	private String email = "";
	private String password = "";
	
	/**
	 * 自动登录
	 */
	public void autoLogin() {
		// 获取当前站点的所有Cookie
		Cookie[] cookies = request.getCookies();
		// 对cookies中的数据进行遍历，找到用户名、密码的数据
		if(cookies != null){
			for (int i = 0; i < cookies.length; i++) {
				if ("email".equals(cookies[i].getName())) {
					email = cookies[i].getValue();
				} else if ("password".equals(cookies[i].getName())) {
					password = cookies[i].getValue();
				}
			}
		}
		User user = userService
				.validateLoginInfo(email,password);
		if (user != null) {
			// 1、获取当前的在线人数，从application中获取
			Integer count = (Integer) application.get("count");
			if (count == null) {
				count = 0;
			}
			// 2、使当前的在线人数+1
			count++;
			String remoteAddr = request.getRemoteAddr() == null ? "" : request
					.getRemoteAddr();
			try {
				// 在map中加入ip对应的地址
				userAddr.clear();// 先清空
				userAddr.put(remoteAddr,
						AddressUtil.getAddresses(remoteAddr, "utf-8"));
				// 添加到userInfo的map中
				userInfo.put(user.getUsername(), userAddr);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			application.put("userInfo", userInfo);
			application.put("count", count);
			sessionMap.put("user", user);// 将 user 信息放到session域
			Global.user = user;
		}
	}

	/**
	 * 当前用户博客列表分页显示
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
		if (blogCount == 0) {
			pageCount = 1; // 为了在页面上不显示“第1页/共0页”这种效果
		}
		return "myBlogList";
	}

	/**
	 * 去个人主页
	 * 
	 * @return
	 */
	public String toPersonalPage() {
		// 去个人主页的时候查询当前用户的动态(第一页展示15条)
//		allMessages = messageService.queryMessagePage(user,1,15);
//		myBlogList = blogService.findMyBlogs(user);
//		allCollections = collectionService.findMyCollections(user);
//		allRelations = relationService.queryAllRelations(user);
		allFocusMe = relationService.queryAllMyFocus(user);
//		allQuestions = questionService.queryAllMyQuestions(user);
		return "personalPage";
	}

	/**
	 * 个人中心中的功能
	 * 用于将问问题进行分页展示(在 js 中 ajax 请求调用)
	 */
	public void questionPagination(){
		int countPerPage = 15;// 每页显示15条
		if (pageIndex == null) {
			pageIndex = "1";
		}
		currentPageIndex = Integer.parseInt(pageIndex);
		int questionCount = questionService.getMyQuestionCount(user.getId());//查询问题总数
		// 显示在当前页的博客
		List<Question> questionList = 
				questionService.queryMyPage(user.getId(), currentPageIndex,countPerPage);
		// 总页数
		pageCount = (questionCount % countPerPage == 0 ? questionCount / countPerPage
				: (questionCount / countPerPage + 1));
		if (questionCount == 0) {
			pageCount = 1; // 为了在页面上不显示“第1页/共0页”这种效果
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		for(Question question : questionList){
			buffer.append("{\"questionTitle\":\""+question.getTitle()+"\",")
			  .append("\"questionId\":"+question.getId()+",")
			  .append("\"createTime\":\""+question.getCreateTime()+"\",")
			  .append("\"readCount\":"+question.getReadCount()+",")
			  .append("\"answerSize\":"+question.getAnswers().size()+",")
			  .append("\"pageCount\":"+pageCount+"},");
		}
		buffer = buffer.deleteCharAt(buffer.length()-1); //去掉最后一个逗号
		buffer.append("]");
		//使用 Json 传递数据到前台
		try {
			if(questionList != null){
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(buffer.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 个人中心中的功能
	 * 用于将收藏进行分页展示(在 js 中 ajax 请求调用)
	 * @return
	 */
	public void collectionPagination(){
		int countPerPage = 15;// 每页显示15条
		if (pageIndex == null) {
			pageIndex = "1";
		}
		currentPageIndex = Integer.parseInt(pageIndex);
		int collectionCount = collectionService.getMyCollectionCount(user);//查询收藏的博客总数
		// 显示在当前页的博客
		List<Collection> collectionList = collectionService.queryMyPage(user, currentPageIndex,
				countPerPage);
		// 总页数
		pageCount = (collectionCount % countPerPage == 0 ? collectionCount / countPerPage
				: (collectionCount / countPerPage + 1));
		if (collectionCount == 0) {
			pageCount = 1; // 为了在页面上不显示“第1页/共0页”这种效果
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		for(Collection collection : collectionList){
			buffer.append("{\"blogTitle\":\""+collection.getBlog().getTitle()+"\",")
			  .append("\"blogId\":"+collection.getBlog().getId()+",")
			  .append("\"collectTime\":\""+collection.getCollectTime()+"\",")
			  .append("\"pageCount\":"+pageCount+"},");
		}
		buffer = buffer.deleteCharAt(buffer.length()-1); //去掉最后一个逗号
		buffer.append("]");
		//使用 Json 传递数据到前台
		try {
			if(collectionList != null){
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(buffer.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 个人中心中的功能
	 * 用于将博客进行分页展示(在 js 中 ajax 请求调用)
	 * @return
	 */
	public void blogPagination() {
		int countPerPage = 15;// 每页显示15条
		if (pageIndex == null) {
			pageIndex = "1";
		}
		currentPageIndex = Integer.parseInt(pageIndex);
		int blogCount = blogService.getMyBlogCount(user);// 查询博客总数
		// 显示在当前页的博客
		List<Blog> blogList = blogService.queryMyPage(user, currentPageIndex,
				countPerPage);
		// 总页数
		pageCount = (blogCount % countPerPage == 0 ? blogCount / countPerPage
				: (blogCount / countPerPage + 1));
		if (blogCount == 0) {
			pageCount = 1; // 为了在页面上不显示“第1页/共0页”这种效果
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		for(Blog blog : blogList){
			buffer.append("{\"blogTitle\":\""+blog.getTitle()+"\",")
				  .append("\"blogId\":"+blog.getId()+",")
				  .append("\"createTime\":\""+blog.getCreateTime()+"\",")
				  .append("\"readCount\":"+blog.getReadCount()+",")
				  .append("\"commentSize\":"+blog.getComments().size()+",")
				  .append("\"allowComment\":"+blog.getAllowComment()+",")
				  .append("\"pageCount\":"+pageCount+"},");
		}
		buffer = buffer.deleteCharAt(buffer.length()-1); //去掉最后一个逗号
		buffer.append("]");
		//使用 Json 传递数据到前台
		try {
			if(blogList != null){
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(buffer.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 个人中心中的功能
	 * 用于将消息进行分页展示(在 js 中 ajax 请求调用)
	 * @return
	 */
	public void messagePagination(){
		int countPerPage = 15;// 每页显示15条
		if (pageIndex == null) {
			pageIndex = "1";
		}
		currentPageIndex = Integer.parseInt(pageIndex);
		int messageCount = messageService.getMessageCount(user);// 查询消息总数
		// 显示在当前页的博客
		List<Message> messageList = messageService.queryMessagePage(user,currentPageIndex, countPerPage);
		// 总页数
		pageCount = (messageCount % countPerPage == 0 ? messageCount / countPerPage
				: (messageCount / countPerPage + 1));
		if (messageCount == 0) {
			pageCount = 1; // 为了在页面上不显示“第1页/共0页”这种效果
		}
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		for(Message message : messageList){
			buffer.append("{\"comment\":"+message.getComment()+",")
				  .append("\"love\":"+message.getLove()+",")
				  .append("\"collect\":"+message.getCollect()+",")
				  .append("\"share\":"+message.getShare()+",")
				  .append("\"reply\":"+message.getReply()+",")
				  .append("\"focus\":"+message.getFocus()+",")
				  .append("\"answer\":"+message.getAnswer()+",")
				  .append("\"addAsk\":"+message.getAddAsk()+",")
				  .append("\"otherId\":"+message.getOther().getId()+",")
				  .append("\"otherName\":\""+message.getOther().getUsername()+"\",");
				  if(message.getBlog() != null){
					  buffer.append("\"blogId\":"+message.getBlog().getId()+",")
					  		.append("\"blogTitle\":\""+message.getBlog().getTitle()+"\",");
				  }else {
					  buffer.append("\"blogId\":"+0+",")
					  	.append("\"blogTitle\":\"unknow\",");
				  }
				  if(message.getQuestion() != null){
					  buffer.append("\"questionId\":"+message.getQuestion().getId()+",")
					  		.append("\"questionTitle\":\""+message.getQuestion().getTitle()+"\",");
				  } else {
					  buffer.append("\"questionId\":"+0+",")
					  		.append("\"questionTitle\":\"unknow\",");
				  }
				  buffer.append("\"createTime\":\""+message.getCreateTime()+"\",")
				  		.append("\"pageCount\":"+pageCount)	//返回总的页数便于前端使用
				  		.append("},");
		}
		buffer = buffer.deleteCharAt(buffer.length()-1); //去掉最后一个逗号
		buffer.append("]");
		//使用 Json 传递数据到前台
		try {
			if(messageList != null){
				response.setCharacterEncoding("utf-8");
				response.getWriter().print(buffer.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	 * 
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
		if (blogCount == 0) {
			pageCount = 1; // 为了在页面上不显示“第1页/共0页”这种效果
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
	 * 删除blog(逻辑删除) 处理 ajax 请求 将删除后的信息以流的方式返回，以便 ajax 代码调用(struts2 中 ajax的用法)
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
			if (!collectionService.queryBlogInCollection(user, bid)) {
				Blog blog2 = blogService.getBlogById(bid);
				Blog blog = new Blog();
				blog.setId(bid);
				Collection collection = new Collection();
				collection.setBlog(blog);
				collection.setDeleted(false);// 未删除
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm");
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
	 * 
	 * @return
	 */
	public String removeTheCollection() {
		try {
			collectionService.removeBlogFromCollections(user, bid);
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

	// 搜索条件
	private String searchCondition;

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	/**
	 * 根据用户的输入搜索博客
	 * 
	 * @return
	 */
	public String searchBlog() {

		int countPerPage = 5;// 每页显示5条
		if (pageIndex == null) {
			pageIndex = "1";
		}
		currentPageIndex = Integer.parseInt(pageIndex);
		int blogCount = blogService.getMatchedBlogCount(searchCondition);// 查询匹配的博客总数
		System.out.println("llll" + blogCount);
		// 显示在当前页的博客
		matchedBlogList = blogService.searchBlogByCondition(currentPageIndex,
				countPerPage, searchCondition);
		// 总页数
		pageCount = (blogCount % countPerPage == 0 ? blogCount / countPerPage
				: (blogCount / countPerPage + 1));

		if (blogCount == 0) {
			pageCount = 1; // 为了在页面上不显示“第1页/共0页”这种效果
		}
		return "toMatchedBlogPage";
	}

	/**
	 * 改变博客的评论权限(是否可评论)
	 * 
	 * @return
	 */
	public String changeAllowState() {
		Blog blog = blogService.getBlogById(bid);
		if (blog.getAllowComment()) { // 如果博客现在是可评论的
			// 设置为不可评论
			blogService.changeBlogAllowState(bid, false);
		} else {
			// 设置为可评论
			blogService.changeBlogAllowState(bid, true);
		}
		return "redirectToPersonalPage";
	}
}
