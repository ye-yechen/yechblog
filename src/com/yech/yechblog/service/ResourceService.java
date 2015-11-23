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

	/**
	 * 根据资源id查找资源
	 */
	public Resource getResourceById(Integer rid);

	/**
	 * 更新资源
	 * @param model
	 */
	public void updateResource(Resource model);

}
