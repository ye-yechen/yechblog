package com.yech.yechblog.service;

import java.util.List;

import com.yech.yechblog.entity.Resource;

public interface ResourceService {

	/**
	 * ����Resource
	 * @param model
	 */
	public void saveResource(Resource model);

	/**
	 * ��ѯ������Դ
	 * @return
	 */
	public List<Resource> queryAllResources();

	/**
	 * ������Դid������Դ
	 */
	public Resource getResourceById(Integer rid);

	/**
	 * ������Դ
	 * @param model
	 */
	public void updateResource(Resource model);

}