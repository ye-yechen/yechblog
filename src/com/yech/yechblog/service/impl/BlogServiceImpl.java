package com.yech.yechblog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yech.yechblog.dao.BaseDao;
import com.yech.yechblog.entity.Blog;
import com.yech.yechblog.entity.Comment;
import com.yech.yechblog.entity.Message;
import com.yech.yechblog.entity.Reply;
import com.yech.yechblog.entity.Tag;
import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.BlogService;

@Service("blogService")
public class BlogServiceImpl implements BlogService {

	@Resource(name="blogDao")
	private BaseDao<Blog> blogDao;

	@Resource(name="commentDao")
	private BaseDao<Comment> commentDao;
	
	@Resource(name="messageDao")
	private BaseDao<Message> messageDao;
	
	@Resource(name="replyDao")
	private BaseDao<Reply> replyDao;
	/**
	 * 查找所有博客
	 */
	@Override
	public List<Blog> findMyBlogs(User user) {
		String hql = "from Blog b where b.user.id = ? and b.deleted = 0 "
				+ "order by b.createTime desc";
		List<Blog> blogs = blogDao.batchFindEntityByHQL(hql,user.getId());
		//遍历blog所属的user，避免懒加载
		for(Blog blog : blogs){
			blog.getComments().size();
			blog.getUser().getUsername();
			blog.getId();
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

	/**
	 * 查询所有用户博客
	 * @return
	 */
	@Override
	public List<Blog> findAllBlogs() {
		String hql = "from Blog b where b.deleted = 0";
		List<Blog> blogs = blogDao.batchFindEntityByHQL(hql);
		//遍历blog所属的user，避免懒加载
		for(Blog blog : blogs){
			blog.getUser().getUsername();
		}
		return blogs;
	}

	/**
	 * 阅读全文
	 */
	@Override
	public Blog readDetail(Integer bid) {
		
		Blog blog =  blogDao.getEntity(bid);
		blog.getComments().size();
		//获取blog所属的user，避免懒加载
		blog.getUser().getUsername();
		return blog;
	}

	/**
	 * 为当前博客添加评论
	 * @param bid
	 */
	@Override
	public void addComment(Comment comment) {
		commentDao.saveOrUpdateEntity(comment);
	}

	/**
	 * 查询当前博客所有评论
	 */
	@Override
	public List<Comment> queryAllComments(Integer bid) {
		String hql = "from Comment c where c.blog.id = ?";
		List<Comment> comments = commentDao.batchFindEntityByHQL(hql, bid);
		for(Comment comment : comments){
			comment.getUser().getUsername();
		}
		return comments;
	}

	/**
	 * 统计博客总数
	 */
	@Override
	public int getBlogCount() {
		String hql = "select count(*) from Blog b where b.deleted = 0";
		return  ((Long)(blogDao.uniqueResult(hql))).intValue();
	}

	/**
	 * 查询指定页的bolg总数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> queryPage(int currentPageIndex, int countPerPage) {
		String hql = "SELECT * FROM blogs where deleted = 0 order by create_time desc LIMIT ?,?";
		List<Blog> blogs = (List<Blog>) blogDao.listResult(Blog.class,hql,
				(currentPageIndex-1) * countPerPage,countPerPage);
		for(Blog blog : blogs){
			blog.getUser().getUsername();
			blog.getTags().size();
		}
		return blogs;
	}
	
	/**
	 * 在当前用户博客中查询指定页的bolg总数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> queryMyPage(User user,int currentPageIndex, int countPerPage) {
		String hql = "SELECT * FROM blogs WHERE userid = ? and deleted = 0 order by create_time desc LIMIT ?,?";
		List<Blog> blogs = (List<Blog>) blogDao.listResult(Blog.class,hql,user.getId(), 
				(currentPageIndex-1) * countPerPage,countPerPage);
		for(Blog blog : blogs){
			blog.getUser().getUsername();
			blog.getTags().size();
		}
		return blogs;
	}

	/**
	 * 统计当前用户博客总数
	 * @return
	 */
	@Override
	public int getMyBlogCount(User user) {
		String hql = "select count(*) from Blog b where b.user.id = ? and b.deleted = 0";
		return  ((Long)(blogDao.uniqueResult(hql,user.getId()))).intValue();
	}

	/**
	 * 根据 id 查出博客
	 * @return
	 */
	@Override
	public Blog getBlogById(Integer bid) {
		Blog blog = blogDao.getEntity(bid);
		blog.getUser().getUsername();
		for(Tag tag : blog.getTags()){
			tag.getTagName();
		}
		return blog;
	}

	/**
	 * 根据传入的tagName 查找含有此标签的博客
	 * @return
	 */
	@Override
	public List<Blog> queryBlogsByTagName(String tagName) {
		String hql = "select t.blogs from Tag t where t.tagName = ? order by t.createTime desc";
		List<Blog> blogs = blogDao.batchFindEntityByHQL(hql, tagName);
		for(Blog blog : blogs){
			//避免懒加载
			blog.getUser().getUsername();
			blog.getComments().size();
			blog.getTags().size();
		}
		return blogs;
	}

	/**
	 * 查询具有 tagName 标签的博客总数，用于分页
	 * @return
	 */
	@Override
	public int getSimilarBlogCount(String tagName) {
		String hql = "select count(t.tagName) from Tag t where t.tagName = ?";
		return  ((Long)(blogDao.uniqueResult(hql,tagName))).intValue();
	}

	/**
	 * 在具有相同标签的博客中查询指定页的bolg总数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> querySimilarBlogPage(String tagName,
			int currentPageIndex, int countPerPage) {
		String sql = "select b.*,t.* from tags_blogs as tb "
				+ "left join tags as t on t.id = tb.t_id "
				+ "left join blogs as b on b.id = tb.b_id "
				+ "where t.tag_name = ? and b.deleted = 0 "
				+ "order by t.create_time desc limit ?,?";
		List<Blog> blogs = (List<Blog>) blogDao.listResult(Blog.class,sql,tagName, 
				(currentPageIndex-1) * countPerPage,countPerPage);
		for(Blog blog : blogs){
			blog.getUser().getUsername();
			blog.getComments().size();
			blog.getTags().size();
		}
		return blogs;
	}

	/**
	 * 更新blog
	 */
	@Override
	public void updateBlog(Blog model) {
		blogDao.updateEntity(model);
	}

	/**
	 * 删除blog(逻辑删除)
	 * 同时删除评论、回复、标签、消息
	 */
	@Override
	public void deleteBlog(Integer bid) {
		//删除标签
//		String hql = "delete from tags_blogs where tb.b_id = ?";
//		blogDao.executeSQL(hql, bid);
		
		//删除消息
		String hql = "delete from Message m where m.blog.id = ?";
		messageDao.batchUpdateEntityByHQL(hql, bid);
		
		//删除回复
		hql = "delete from Reply r where r.comment.id in (select c.id "
				+ "from Comment c where c.blog.id = ?)";
		replyDao.batchUpdateEntityByHQL(hql, bid);
		
		//删除评论
		hql = "delete from Comment c where c.blog.id = ?";
		commentDao.batchUpdateEntityByHQL(hql, bid);
	
		//删除博客
		hql = "update Blog b set b.deleted = 1 where b.id = ?";
		blogDao.batchUpdateEntityByHQL(hql, bid);
	}

	/**
	 * 根据条件搜索匹配的博客
	 * @param searchCondition
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Blog> searchBlogByCondition(int currentPageIndex,int countPerPage,String searchCondition) {
		String hql = "SELECT * FROM blogs b where b.deleted = 0 and "
				+ "b.title like ? order by b.create_time desc LIMIT ?,?";
		List<Blog> blogs = 
				blogDao.listResult(Blog.class, hql,"%"+searchCondition+"%",
						(currentPageIndex-1) * countPerPage,countPerPage);
		for(Blog blog : blogs){
			blog.getUser().getUsername();
			blog.getComments().size();
			blog.getTags().size();
		}
		return blogs;
	}

	/**
	 * 查询匹配的博客数量
	 * @return
	 */
	@Override
	public int getMatchedBlogCount(String searchCondition) {
		String hql = "select count(*) from Blog b "
				+ "where b.title like ? and b.deleted = 0";
		return  ((Long)(blogDao.uniqueResult(hql,"%"+searchCondition+"%"))).intValue();
	}

	/**
	 * 根据用户id查询他的博客
	 * @return
	 */
	@Override
	public List<Blog> queryHisBlogs(Integer userId) {
		String hql = "from Blog b where b.user.id = ? and b.deleted = 0";
		List<Blog> blogs = blogDao.batchFindEntityByHQL(hql, userId);
		for(Blog blog : blogs){
			blog.getId();
			blog.getUser().getUsername();
			blog.getComments().size();
		}
		return blogs;
	}

	/**
	 * 改变博客的评论权限(是否可评论)
	 * @param bid
	 */
	@Override
	public void changeBlogAllowState(Integer bid,Boolean state) {
		String hql = "update Blog b set b.allowComment = ? where b.id = ?";
		blogDao.batchUpdateEntityByHQL(hql, state,bid);
	}

}
