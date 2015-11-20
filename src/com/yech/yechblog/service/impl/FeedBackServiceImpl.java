package com.yech.yechblog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yech.yechblog.dao.BaseDao;
import com.yech.yechblog.entity.FeedBack;
import com.yech.yechblog.service.FeedBackService;

@Service("feedBackService")
public class FeedBackServiceImpl extends BaseServiceImpl<FeedBack> 
									implements FeedBackService {

	@Override
	@Resource(name="feedBackDao")
	public void setBaseDao(BaseDao<FeedBack> baseDao) {
		super.setBaseDao(baseDao);
	}
	
	/**
	 * 保存反馈
	 */
	@Override
	public void saveFeedBack(FeedBack feedBack) {
		this.saveEntity(feedBack);
	}
	
	/**
	 * 查询所有反馈
	 * @return
	 */
	@Override
	public List<FeedBack> findAllFeedBacks() {
		String hql = "from FeedBack";
		List<FeedBack> feedBacks = this.batchFindEntityByHQL(hql);
		for(FeedBack feedBack : feedBacks){
			feedBack.getUser().getUsername();
		}
		return feedBacks;
	}

	
}
