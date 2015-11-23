package com.yech.yechblog.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ServletContextAware;

import com.yech.yechblog.aware.UserAware;
import com.yech.yechblog.entity.Resource;
import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.ResourceService;
import com.yech.yechblog.util.ValidateUtil;

@Controller
@Scope("prototype")
public class ResourceAction extends BaseAction<Resource> implements UserAware,
		ServletContextAware,ServletRequestAware
{

	private static final long serialVersionUID = 2916660768257015198L;

	@javax.annotation.Resource
	private ResourceService resourceService;

	private User user;
	private ServletContext servletContext;
	private HttpServletRequest request;
	@Override
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	// 保存登录前的页面用于登录后跳回
	private static String originUrl;

	public String getOriginUrl() {
		return originUrl;
	}
	
	//资源列表
	private List<Resource> resList;
	
	public List<Resource> getResList() {
		return resList;
	}

	public void setResList(List<Resource> resList) {
		this.resList = resList;
	}

	/**
	 * 去到上传资源页面
	 * 
	 * @return
	 */
	public String toUploadPage() {
		// 只有静态，才能在登录后回到之前的页面，不然 originUrl 就为null
		originUrl = request.getHeader("referer");
		originUrl = originUrl.substring(originUrl.lastIndexOf("/")+1);
		return "toUploadPage";
	}

	private File userRes; // 用户上传的资源
	private String userResFileName; // 文件名，必须以 FileName 结尾

	public File getUserRes() {
		return userRes;
	}

	public void setUserRes(File userRes) {
		this.userRes = userRes;
	}

	public String getUserResFileName() {
		return userResFileName;
	}

	public void setUserResFileName(String userResFileName) {
		this.userResFileName = userResFileName;
	}

	/**
	 * 上传资源
	 * @return
	 */
	public String uploadResource() {
		if (ValidateUtil.isValidate(userResFileName)) { // 文件名是否有效
			// 得到 uplode 文件夹在服务器上的真实路径
			String dir = servletContext.getRealPath("/upload");
			// 以当前用户（用户名+id）作为文件夹名，即每个用户上传的资源都存在单独的文件夹中
			String userResPath = dir + File.separator + user.getUsername() + user.getId()
					+ File.separator;
			File userFolder = new File(userResPath);
			if (!userFolder.exists()) { // 如果文件夹不存在就创建文件夹
				userFolder.mkdir();
			} // 如果文件夹存在就上传文件
				// 文件扩展名
			String ext = userResFileName.substring(userResFileName.lastIndexOf("."));
			// 纳秒时间作为文件名(防止重名)
			long l = System.nanoTime();
			// 新文件路径
			File newFile = new File(userFolder, l + ext);
			try {
				FileUtils.copyFile(userRes, newFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			model.setResName(userResFileName);
			model.setDownloadCount(0);//初始下载次数为0
			model.setResSuffix(ext);//设置资源后缀名
			model.setResUploadTime(format.format(new Date()));
			model.setResPath("/upload/" + user.getUsername() + user.getId() + "/" + l + ext);
			model.setUploadUser(user);
			resourceService.saveResource(model);
		}
		return "keepOriginUrl";
	}
	
	/**
	 * 去资源共享页面
	 * @return
	 */
	public String toResPage(){
		resList = resourceService.queryAllResources();
		return "toResPage";
	}
	public Integer rid;//接收页面传过来的resource的id
	private String contentType;//下载的结果类型
	private long contentLength;//下载的文件长度
	//此响应头指定响应是文件下载类型，一般取值为attachment;filename=""
	private String contentDisposition;
	private InputStream inputStream;
	
	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getContentType() {
		return contentType;
	}

	public long getContentLength() {
		return contentLength;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}
	/**
	 * 下载资源
	 * @return
	 */
	public String downloadResource(){
		model = resourceService.getResourceById(rid);
		String filename = servletContext.getRealPath(model.getResPath());
		String downName;
		try {
			//为文件名编码，防止中文乱码
			downName = URLEncoder.encode(model.getResName(), "utf-8");
			contentType = "application/octet-stream;charset=utf-8";
			contentDisposition = "attachment;filename="+downName;
			inputStream = new FileInputStream(filename);
			if(inputStream != null){ //代表可下载
				model.setDownloadCount(model.getDownloadCount()+1);//下载量+1
				resourceService.updateResource(model);//更新数据库中此资源
				contentLength = inputStream.available();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

}
