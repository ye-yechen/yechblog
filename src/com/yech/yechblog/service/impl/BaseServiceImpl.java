package com.yech.yechblog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.yech.yechblog.dao.BaseDao;
import com.yech.yechblog.service.BaseService;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> baseDao;

	/**
	 * 使用 setter 注入，便于子类覆盖
	 */
	@Resource
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void saveEntity(T t) {
		baseDao.saveEntity(t);
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		baseDao.saveOrUpdateEntity(t);
	}

	@Override
	public void updateEntity(T t) {
		baseDao.updateEntity(t);
	}

	@Override
	public void deleteEntity(T t) {
		baseDao.deleteEntity(t);
	}

	@Override
	public void batchUpdateEntityByHQL(String hql, Object... objects) {
		baseDao.batchUpdateEntityByHQL(hql, objects);
	}

	@Override
	public void executeSQL(String sql, Object... objects) {
		baseDao.executeSQL(sql, objects);
	}

	@Override
	public T loadEntity(Integer id) {
		return baseDao.loadEntity(id);
	}

	@Override
	public T getEntity(Integer id) {
		return baseDao.getEntity(id);
	}

	@Override
	public List<T> batchFindEntityByHQL(String hql, Object... objects) {
		return baseDao.batchFindEntityByHQL(hql, objects);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List executeSQLQuery(Class clazz, String sql, Object... objects) {
		return baseDao.executeSQLQuery(clazz, sql, objects);
	}

	// 单值检索，确保查询结果有且只有一条记录
	public Object uniqueResult(String hql, Object... objects) {
		return baseDao.uniqueResult(hql, objects);
	}
}
