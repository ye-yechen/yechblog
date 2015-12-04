package com.yech.yechblog.service;

import java.util.List;

import com.yech.yechblog.entity.Collection;
import com.yech.yechblog.entity.User;

public interface CollectionService {

	public void saveCollection(Collection collection);

	/**
	 * 查找当前用户所有的收藏
	 * @return
	 */
	public List<Collection> findMyCollections(User user);

	/**
	 * 查询是否有id为bid的collection
	 * @return
	 */
	public boolean queryBlogInCollection(User user,Integer bid);

	public void removeBlogFromCollections(User user,Integer bid);

	/**
	 * 统计收藏的博客总数
	 */
	public int getMyCollectionCount(User user);

	/**
	 * 查询指定页收藏的博客总数
	 */
	public List<Collection> queryMyPage(User user, int currentPageIndex,
			int countPerPage);

}
