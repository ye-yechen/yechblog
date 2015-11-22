package com.yech.yechblog.service;

import java.util.List;

import com.yech.yechblog.entity.Resource;

public interface ResourceService {

	/**
	 * 保存Resource
	 * @param model
	 */
	public void saveResource(Resource model);

	/**
	 * 查询所有资源
	 * @return
	 */
	public List<Resource> queryAllResources();

}
