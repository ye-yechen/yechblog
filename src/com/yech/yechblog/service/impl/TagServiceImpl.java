package com.yech.yechblog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yech.yechblog.dao.BaseDao;
import com.yech.yechblog.entity.Tag;
import com.yech.yechblog.service.TagService;

@Service("tagService")
public class TagServiceImpl implements TagService{

	@Resource(name="tagDao")
	private BaseDao<Tag> tagDao;
	
	@Override
	public void saveTag(Tag blogTag) {
		tagDao.saveEntity(blogTag);
	}

	/**
	 * 根据博客id 查询此博客所属的标签
	 * @param bid
	 */
	@Override
	public List<Tag> queryBlogTags(Integer bid) {
		String hql = "select b.tags from Blog b where b.id = ?";
		return tagDao.batchFindEntityByHQL(hql, bid);
	}

}
