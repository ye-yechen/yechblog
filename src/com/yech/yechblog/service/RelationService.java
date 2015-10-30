package com.yech.yechblog.service;

import java.util.List;

import com.yech.yechblog.entity.Relation;
import com.yech.yechblog.entity.User;

public interface RelationService {

	//保存关系
	public void saveRelation(Relation model);

	/**
	 * 查询relation表是否有这个关系
	 * @return
	 */
	public boolean queryRelation(User user, User other);

	/**
	 * 查询当前用户所有的关系
	 * @return
	 */
	public List<Relation> queryAllRelations(User user);

}
