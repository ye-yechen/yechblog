package com.yech.yechblog.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yech.yechblog.entity.Blog;
import com.yech.yechblog.entity.Question;
import com.yech.yechblog.entity.Tag;
import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.BlogService;
import com.yech.yechblog.service.QuestionService;
import com.yech.yechblog.service.UserService;
import com.yech.yechblog.util.AddressUtil;
import com.yech.yechblog.util.DataUtil;
import com.yech.yechblog.util.Global;

/**
 * 登录Action
 * 
 * @author Administrator
 */
@Controller
@Scope("prototype")
public class LoginAction extends BaseAction<User> implements SessionAware,
		ApplicationAware, ServletRequestAware
{

	private static final long serialVersionUID = 6769670387253184703L;

	// 是否记住密码
	private boolean remember;

	public boolean isRemember() {
		return remember;
	}

	public void setRemember(boolean remember) {
		this.remember = remember;
	}

	// 接收 session 的 map
	private Map<String, Object> sessionMap;
	// 接收 application 的map
	private Map<String, Object> application;

	// 用户地址(根据ip得到的地址) Map<"ip","地址(国，省，市)">
	private Map<String, String> userAddr = new HashMap<String, String>();
	// 保存用户信息(Map<"用户名",Map<"ip","地址">>)
	private static Map<String, Map<String, String>> userInfo = new HashMap<String, Map<String, String>>();
	private HttpServletRequest request;
	// 含某标签的博客数量 Map<"标签名",博客数量>
	private Map<String, Integer> blogNumsWithTag = new HashMap<String, Integer>();
	// 属于某分类的问题数量 Map<"分类名",问题数量>
	private Map<String, Integer> questionNumsWithTag = new HashMap<String, Integer>();

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}

	@Override
	public void setApplication(Map<String, Object> arg0) {
		this.application = arg0;
	}

	/**
	 * 注入 UserService
	 */
	@Resource
	private UserService userService;

	@Resource
	private BlogService blogService;

	@Resource
	private QuestionService questionService;

	// 保存登录前的页面用于登录后跳回
	private static String originUrl;

	public String getOriginUrl() {
		return originUrl;
	}

	// public void setOriginUrl(String originUrl) {
	// this.originUrl = originUrl;
	// }
	/**
	 * 到达登录界面
	 * 
	 * @return
	 */
	@SkipValidation
	public String toLoginPage() {
		// 只有静态，才能在登录后回到之前的页面，不然 originUrl 就为null
		originUrl = request.getHeader("referer");
		originUrl = originUrl.substring(originUrl.lastIndexOf("/"));
		return "loginPage";
	}

	/**
	 * 进行登录处理
	 * 
	 * @return
	 */
	public String doLogin() {
		// 得到请求头中的 referer 字段，用于登录后跳转到之前的页面
		return "keepOriginUrl";
	}

	/**
	 * 去到数据分析页面
	 * 
	 * @return
	 */
	@SkipValidation
	public String toDataAnalysePage() {
		getBlogNumsWithTag();
		getQuestionNumsWithCategory();
		int registedUserNums = userService.getRegistedUserNums();
		application.put("registedUserNums", registedUserNums);
		application.put("blogNumsWithTag", blogNumsWithTag);
		application.put("questionNumsWithTag", questionNumsWithTag);
		if (application.get("count") == null
				|| (Integer) (application.get("count")) == 0) {
			application.put("count", 0);
		}
		return "dataAnalysePage";
	}

	/**
	 * 得到含有某个标签的博客的数量(用于进行数据分析)
	 * 
	 * @return
	 */
	private void getBlogNumsWithTag() {
		List<Blog> blogs = blogService.findAllBlogs();// 得到所有blog
		// 得到含有某个标签的博客的数量(用于进行数据分析)
		for (int i = 0; i < blogs.size(); i++) {
			// 得到此博客的标签
			List<Tag> tags = new ArrayList<Tag>(blogs.get(i).getTags());
			for (int j = 0; j < tags.size(); j++) {
				// map里已经有此次遍历到的tag,直接将含有此tag的博客数+1
				if (blogNumsWithTag.keySet().contains(tags.get(j).getTagName())) {
					Integer num = blogNumsWithTag.get(tags.get(j).getTagName());
					blogNumsWithTag.put(tags.get(j).getTagName(), ++num);
				} else { // map里面不含此次遍历到的tag,设置含有此标签的博客数为1
					blogNumsWithTag.put(tags.get(j).getTagName(), 1);
				}
			}
		}
	}

	/**
	 * 得到含有某分类的问题的数量(用于进行数据分析)
	 */
	private void getQuestionNumsWithCategory() {
		List<Question> questions = questionService.queryAllQuestions();
		for (int i = 0; i < questions.size(); i++) {
			// map里已经有此次遍历得到的question的分类,直接将含有此分类的问题数+1
			if (questionNumsWithTag.keySet().contains(
					questions.get(i).getCategory())) {
				Integer num = questionNumsWithTag.get(questions.get(i)
						.getCategory());
				questionNumsWithTag.put(questions.get(i).getCategory(), ++num);
			} else {
				// map里面不含此次遍历到的question的分类,设置含有此此分类的问题数为1
				questionNumsWithTag.put(questions.get(i).getCategory(), 1);
			}
		}
	}

	/**
	 * 退出登录
	 */
	@SkipValidation
	public String logout() {
		// 1、在线人数-1：获取在线人数，若数量还>0，则-1
		Integer count = (Integer) application.get("count");
		User user = (User) sessionMap.get("user");
		if (count != null && count > 0) {
			count--;
			application.put("count", count);
			// 退出登录时移除此用户
			userInfo.remove(user.getUsername());
		}
		sessionMap.clear();
		// 2、session失效：强转为SessionMap，调用invalidate方法
		((SessionMap<String, Object>) sessionMap).invalidate();
		Global.user = null;
		// 只有静态，才能在登录后回到之前的页面，不然 originUrl 就为null
		originUrl = request.getHeader("referer");
		originUrl = originUrl.substring(originUrl.lastIndexOf("/"));
		return "keepOriginUrl";
	}

	public void validate() {
		User user = userService.validateLoginInfo(model.getEmail(),
				DataUtil.md5(model.getPassword()));
		if (user == null) {
			addActionError("email 或 password 错误!或者账号未激活!");
		} else {
			if (isRemember()) { // 记住了密码
			}
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

}
