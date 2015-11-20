package com.yech.yechblog.service;

import java.util.List;

import com.yech.yechblog.entity.FeedBack;

public interface FeedBackService extends BaseService<FeedBack> {

	/**
	 * 保存反馈
	 * @param feedBack
	 */
	public void saveFeedBack(FeedBack feedBack);

	/**
	 * 查询所有反馈
	 * @return
	 */
	public List<FeedBack> findAllFeedBacks();

}
