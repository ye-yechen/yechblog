package com.yech.yechblog.service;

import java.util.List;

import com.yech.yechblog.entity.Tag;


public interface TagService{

	public void saveTag(Tag blogTag);

	/**
	 * 根据博客id 查询此博客所属的标签
	 * @param bid
	 */
	public List<Tag> queryBlogTags(Integer bid);

}
