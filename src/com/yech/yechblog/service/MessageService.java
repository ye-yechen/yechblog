package com.yech.yechblog.service;

import java.util.List;

import com.yech.yechblog.entity.Message;
import com.yech.yechblog.entity.User;

public interface MessageService{

	/**
	 * 获取当前用户接收到的未读的消息
	 * @param user
	 */
	public List<Message> getMyUnReadMessages(User user);

	/**
	 * 将 message 表的 status 字段改为 0，标为已读
	 * @param mid
	 */
	public void changeMessageStatus(Integer mid);

	/**
	 * 保存消息
	 * @param message
	 */
	public void saveMessage(Message message);

	/**
	 * 根据 id 得到消息
	 * @param mid
	 */
	public Message getMessageById(Integer mid);

}
