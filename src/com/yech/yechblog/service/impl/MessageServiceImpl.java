package com.yech.yechblog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yech.yechblog.dao.BaseDao;
import com.yech.yechblog.entity.Message;
import com.yech.yechblog.service.MessageService;

@Service("messageService")
public class MessageServiceImpl extends BaseServiceImpl<Message>
										implements  MessageService{

	/**
	 * 重写该方法，目的是为了覆盖超类中方法的注解，指明注入指定的 Dao 对象,否则Spring
	 * 无法确定注入哪个 Dao( 因为有多个满足条件的 Dao(多个BaseDao的实现类))
	 */
	@Resource(name="messageDao")
	public void setBaseDao(BaseDao<Message> baseDao) {
		super.setBaseDao(baseDao);
	}
}
