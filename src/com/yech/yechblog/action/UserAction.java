package com.yech.yechblog.action;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ServletContextAware;

import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.UserService;
import com.yech.yechblog.util.ValidateUtil;

/**
 * UserAction
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> implements ServletContextAware{

	private static final long serialVersionUID = 3575939345060413099L;

	private File userImg; //上传的文件
	private String userImgFileName; //文件名，必须以 FileName 结尾
	
	private ServletContext servletContext; //接收 ServletContext 对象
	
	@Resource
	private UserService userService;
	@Override
	public void setServletContext(ServletContext arg0) {
		this.servletContext = arg0;
	}

//
//	/**
//	 * 编辑用户信息
//	 * @return
//	 */
//	public String editInfo(){
//		return "";
//	}
	
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
	 * @return
	 */
	public String addImage(){
		System.out.println("imageFileName = "+userImgFileName);
		if(ValidateUtil.isValidate(userImgFileName)){ //文件名是否有效
			//1.实现上传
			//得到 uplode 文件夹在服务器上的真实路径
			String dir = servletContext.getRealPath("/upload");
			//文件扩展名
			String ext = 
					userImgFileName.substring(userImgFileName.lastIndexOf("."));
			//纳秒时间作为文件名(防止重名)
			long l = System.nanoTime();
			//新文件路径
			File newFile = new File(dir,l+ext);
			System.out.println(",,,"+newFile);
			//文件另存为
			//userImg.renameTo(newFile);
			try {
				FileUtils.copyFile(userImg, newFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//2.更新路径
			userService.updateUserImgPath(model.getId(),"/upload/"+l+ext);
			model.setImage("/upload/"+l+ext);
		}
		return "personalPage";
	}
	
	/**
	 * 判断图片是否存在,在 jsp 中调用
	 * @return
	 */
	public boolean photoExists(){
		String path = model.getImage();
		if(ValidateUtil.isValidate(path)){
			String absPath = servletContext.getRealPath(path);//绝对路径
			File file = new File(absPath);
			System.out.println(path);
			return file.exists();
		}
		return false;
	}
}
