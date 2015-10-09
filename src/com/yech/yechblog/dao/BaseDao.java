package com.yech.yechblog.dao;

import java.util.List;

/**
 * BaseDao
 * @author Administrator
 */
public interface BaseDao<T> {

	// 写操作
	public void saveEntity(T t);
	public void saveOrUpdateEntity(T t);
	public void updateEntity(T t);
	public void deleteEntity(T t);
	public void batchUpdateEntityByHQL(String hql, Object... objects);
	// 执行原生的 sql 语句
	public void executeSQL(String sql, Object... objects);
	// 读操作
	public T loadEntity(Integer id);
	public T getEntity(Integer id);
	public List<T> batchFindEntityByHQL(String hql, Object... objects);
	//执行原生的sql查询(clazz 指定是否封装成实体)
	@SuppressWarnings("rawtypes")
	public List executeSQLQuery(Class clazz,String sql,Object ...objects);
}
