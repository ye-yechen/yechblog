package com.yech.yechblog.schedule;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yech.yechblog.entity.Message;
import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.MessageService;
import com.yech.yechblog.util.Global;

@Service
public class ScheduleMessage {

	@Resource
	private MessageService messageService;

	/**
	 * 定时任务，每隔5分钟获取一次数据库 message 表中的消息
	 */
	public void scheduleTask() {
		System.out.println("任务开始了...");
		User user = Global.user;
		System.out.println("user=" + user.getUsername());
		if (user != null) {
			List<Message> messages = messageService.getMyUnReadMessages(user);
			List<Message> userMessages = user.getMessages();
			for (Message message : messages) {
				int i=0;
				for(i=0;i<userMessages.size();i++){
					if(message.getId().equals(userMessages.get(i).getId())){
						break;
					}
				}
				if(i == user.getMessages().size())
					user.getMessages().add(message);
			}
		}
	}
}
