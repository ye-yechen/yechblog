package com.yech.yechblog.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ServletContextAware;

import com.yech.yechblog.entity.Blog;
import com.yech.yechblog.entity.FeedBack;
import com.yech.yechblog.entity.Message;
import com.yech.yechblog.entity.Question;
import com.yech.yechblog.entity.Relation;
import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.BlogService;
import com.yech.yechblog.service.FeedBackService;
import com.yech.yechblog.service.MessageService;
import com.yech.yechblog.service.QuestionService;
import com.yech.yechblog.service.RelationService;
import com.yech.yechblog.service.UserService;
import com.yech.yechblog.util.ValidateUtil;

/**
 * UserAction
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> 
					implements ServletContextAware,ServletResponseAware,ServletRequestAware
{

	private static final long serialVersionUID = 3575939345060413099L;

	private File userImg; // 上传的文件
	private String userImgFileName; // 文件名，必须以 FileName 结尾

	private ServletContext servletContext; // 接收 ServletContext 对象

	@Resource
	private UserService userService;

	@Resource
	private MessageService messageService;

	@Resource
	private BlogService blogService;
	
	@Resource
	private RelationService relationService;
	
	@Resource
	private QuestionService questionService;
	
	@Resource
	private FeedBackService feedBackService;
	//他的博客列表
	private List<Blog> hisBlogs;
	//他关注的人
	private List<Relation> hisRelations;
	//关注他的人
	private List<Relation> focusHims;
	//他的问题列表
	private List<Question> hisQuestions;
	
	public List<Question> getHisQuestions() {
		return hisQuestions;
	}

	public void setHisQuestions(List<Question> hisQuestions) {
		this.hisQuestions = hisQuestions;
	}

	public List<Relation> getFocusHims() {
		return focusHims;
	}

	public void setFocusHims(List<Relation> focusHims) {
		this.focusHims = focusHims;
	}

	public List<Relation> getHisRelations() {
		return hisRelations;
	}

	public void setHisRelations(List<Relation> hisRelations) {
		this.hisRelations = hisRelations;
	}

	public List<Blog> getHisBlogs() {
		return hisBlogs;
	}

	public void setHisBlogs(List<Blog> hisBlogs) {
		this.hisBlogs = hisBlogs;
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		this.servletContext = arg0;
	}

	private HttpServletResponse response;
	private HttpServletRequest request;
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}
	
	/**
	 * 编辑用户信息
	 */
	public String editInfo() {
		model.setCountry("中国");
		model.setId(model.getId());
		userService.updateEntity(model);
		return "personalPage";
	}

	public File getUserImg() {
		return userImg;
	}

	public void setUserImg(File userImg) {
		this.userImg = userImg;
	}

	public String getUserImgFileName() {
		return userImgFileName;
	}

	public void setUserImgFileName(String userImgFileName) {
		this.userImgFileName = userImgFileName;
	}
	
	/**
	 * 去到“关于”页面
	 * @return
	 */
	public String toAboutPage(){
		return "toAboutPage";
	}

	/**
	 * 上传头像
	 * @return
	 */
	public String addImage() {
		if (ValidateUtil.isValidate(userImgFileName)) { // 文件名是否有效
			// 1.实现上传
			// 得到 uplode 文件夹在服务器上的真实路径
			String dir = servletContext.getRealPath("/upload");
			// 文件扩展名
			String ext = userImgFileName.substring(userImgFileName
					.lastIndexOf("."));
			// 纳秒时间作为文件名(防止重名)
			long l = System.nanoTime();
			// 新文件路径
			File newFile = new File(dir, l + ext);
			// 文件另存为
			// userImg.renameTo(newFile);
			try {
				FileUtils.copyFile(userImg, newFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 2.更新路径
			userService.updateUserImgPath(model.getId(), "/upload/" + l + ext);
			model.setImage("/upload/" + l + ext);
		}
		return "personalPage";
	}

	// 新的消息
	private List<Message> newMessages;
	// 已读过的旧消息
	private List<Message> oldMessages;

	public List<Message> getOldMessages() {
		return oldMessages;
	}

	public void setOldMessages(List<Message> oldMessages) {
		this.oldMessages = oldMessages;
	}

	public List<Message> getNewMessages() {
		return newMessages;
	}

	public void setNewMessages(List<Message> newMessages) {
		this.newMessages = newMessages;
	}

	/**
	 * 去到消息中心页面
	 * 
	 * @return
	 */
	public String toMessageCenter() {
		newMessages = model.getMessages();
		oldMessages = messageService.getOldMessages(model); // 获取已经读过的旧消息
		if (newMessages.size() > 0) {
			for (Message message : newMessages) {
				//不是关注、answer、追问的消息(因为这些消息类型没有对应的博客)
				if(!message.getFocus() && !message.getAnswer() && !message.getAddAsk()){ 
					message.getBlog().getId();
					//查出message所对应的blog
					Blog blog = blogService.getBlogById(message.getBlog().getId());
					message.setBlog(blog);
				}else if(!message.getAnswer() && !message.getAddAsk()){ //不是answer、追问类型的消息(即是关注类型的消息)
					//将 message 表中的 status 字段设为 0 ，表示已读
					messageService.changeMessageStatus(message.getId());
				} else { // 是answer类型的消息
					Question question = 
							questionService.getQuestionById(message.getQuestion().getId());
					message.setQuestion(question);
				}
			}
		}
		if (oldMessages.size() > 0) {
			for (Message message : oldMessages) {
				if(!message.getFocus() && !message.getAnswer() && !message.getAddAsk()){
					//查出message所对应的blog
					Blog blog = blogService.getBlogById(message.getBlog().getId());
					message.setBlog(blog);
				} else if(message.getAnswer() || message.getAddAsk()){
					Question question = 
							questionService.getQuestionById(message.getQuestion().getId());
					message.setQuestion(question);
				}
			}
		}
		return "toMessageCenterPage";
	}
	
	//接收页面传过来的用户id，根据id进入他的主页
	private Integer userId;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	//要进user的主页
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 去别人的主页
	 * @return
	 */
	public String toOtherHomePage(){
		user = userService.getEntity(userId);
		if(model.getId() != userId){	//所进的主页不是当前登录的用户的主页
			hisBlogs = blogService.queryHisBlogs(userId);
			hisRelations = relationService.queryAllRelations(user);
			focusHims = relationService.queryAllMyFocus(user);
			hisQuestions = questionService.queryHisQuestions(userId);
			return "toOtherHomePage";
		} else{	//要进的主页是当前登录用户的主页(即进自己的主页)
			//重定向到个人主页
			return "redirectToPersonalPage";
		}
	}
	
	//接收页面传过来的值
	private String friendName;
	//好友列表
	private List<User> friendsList;
	
	public List<User> getFriendsList() {
		return friendsList;
	}

	public void setFriendsList(List<User> friendsList) {
		this.friendsList = friendsList;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	/**
	 * 搜索好友（根据输入的好友名）
	 * @return
	 */
	public void searchFriends(){
		friendsList = userService.searchUserByName(friendName);
		JSONArray array = new JSONArray(friendsList);
		try {
			response.getWriter().print(array);
			System.out.println("array= "+array);
		} catch (Exception e) {
		}
//		return "ajax-success";
	}
	
	public String originUrl;
	public String getOriginUrl() {
		return originUrl;
	}
	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}
	
	//接收反馈页面的反馈内容
	private String fbContent;
	
	public String getFbContent() {
		return fbContent;
	}

	public void setFbContent(String fbContent) {
		this.fbContent = fbContent;
	}

	/**
	 * 用户反馈
	 */
	public String feedBack(){
		model = userService.getEntity(userId);
		originUrl = request.getHeader("referer");
		// http://localhost:8080/yechblog/BlogAction_pagination.action
		originUrl = 
				originUrl.substring(originUrl.lastIndexOf("/")+1);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		FeedBack feedBack = new FeedBack();
		feedBack.setContent(fbContent);
		feedBack.setState(false); //初始为为处理状态
		feedBack.setUser(model);
		feedBack.setFeedBackTime(format.format(new Date()));
		feedBackService.saveFeedBack(feedBack); //保存feedBack
		return "keepOriginUrl";
	}

}
