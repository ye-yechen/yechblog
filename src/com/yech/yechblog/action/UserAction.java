package com.yech.yechblog.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ServletContextAware;

import com.yech.yechblog.entity.Blog;
import com.yech.yechblog.entity.Message;
import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.BlogService;
import com.yech.yechblog.service.MessageService;
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
public class UserAction extends BaseAction<User> implements ServletContextAware
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
	@Override
	public void setServletContext(ServletContext arg0) {
		this.servletContext = arg0;
	}

	/**
	 * 编辑用户信息
	 * 
	 * @return
	 */
	public String editInfo() {
		return "";
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
	 * 上传头像
	 * 
	 * @return
	 */
	public String addImage() {
		System.out.println("imageFileName = " + userImgFileName);
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
			System.out.println(",,," + newFile);
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
				message.getBlog().getId();
				//查出message所对应的blog
				Blog blog = blogService.getBlogById(message.getBlog().getId());
				message.setBlog(blog);
			}
		}
		if (oldMessages.size() > 0) {
			for (Message message : oldMessages) {
				//查出message所对应的blog
				Blog blog = blogService.getBlogById(message.getBlog().getId());
				message.setBlog(blog);
			}
		}
		return "toMessageCenterPage";
	}
}
