package com.yech.yechblog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yech.yechblog.dao.BaseDao;
import com.yech.yechblog.entity.Message;
import com.yech.yechblog.entity.User;
import com.yech.yechblog.service.MessageService;

@Service("messageService")
public class MessageServiceImpl implements  MessageService{

	/**
	 * 重写该方法，目的是为了覆盖超类中方法的注解，指明注入指定的 Dao 对象,否则Spring
	 * 无法确定注入哪个 Dao( 因为有多个满足条件的 Dao(多个BaseDao的实现类))
	 */
	@Resource(name="messageDao")
	private BaseDao<Message> messageDao;

	/**
	 * 保存消息
	 */
	@Override
	public void saveMessage(Message message) {
		messageDao.saveEntity(message);
	}
	
	/**
	 * 获取当前用户接收到的未读的消息
	 * @param user
	 */
	@Override
	public List<Message> getMyUnReadMessages(User user) {
		String hql = "from Message m where m.other.id = ? and m.self.id != ? "
				+ "and m.status = ? order by m.createTime desc";
		List<Message> messages = 
				messageDao.batchFindEntityByHQL(hql, user.getId(), user.getId(),true);
		for(Message message : messages){
			message.getOther().getUsername();
			message.getSelf().getUsername();
		}
		return messages;
	}

	/**
	 * 将 message 表的 status 字段改为 0，标为已读
	 * @param mid
	 */
	@Override
	public void changeMessageStatus(Integer mid) {
		String hql = "update Message m set m.status = ? where m.id = ?";
		messageDao.batchUpdateEntityByHQL(hql,false,mid);
	}

	/**
	 * 根据 id 得到消息
	 * @param mid
	 */
	@Override
	public Message getMessageById(Integer mid) {
		Message message =  messageDao.getEntity(mid);
		if(!message.getFocus() && !message.getAnswer() && !message.getAddAsk()){ //关注类型的消息没有对应的博客
			message.getBlog().getId();
		}else if (message.getAnswer() || message.getAddAsk()) { //answer类型的消息需要查出对应的question,避免懒加载
			message.getQuestion().getId();
		}
		return message;
	}

	/**
	 * 获取已经读过的旧消息
	 */
	@Override
	public List<Message> getOldMessages(User user) {
		String hql = "from Message m where m.other.id = ? and m.self.id != ? "
				+ "and m.status = ? order by m.createTime desc";
		List<Message> messages = 
				messageDao.batchFindEntityByHQL(hql, user.getId(), user.getId(),false);
		for(Message message : messages){//关注和answer类型的消息没有对应的博客
			if(!message.getFocus() && !message.getAnswer() && !message.getAddAsk()){
				message.getBlog().getId();
			} else if (message.getAnswer() || message.getAddAsk()) { //answer类型的消息需要查出对应的question,避免懒加载
				message.getQuestion().getId();
			}
			message.getOther().getUsername();
			message.getSelf().getUsername();
		}
		return messages;
	}

	/**
	 * 查询当前用户的动态
	 * @return
	 */
	@Override
	public List<Message> queryUserActivities(User user) {
		String hql = "from Message m where m.self.id = ? "
				+ "order by m.createTime desc";
		List<Message> messages = 
				messageDao.batchFindEntityByHQL(hql, user.getId());
		for(Message message : messages){
			//关注、answer、追问类型的消息没有对应的博客
			if(!message.getFocus() && !message.getAnswer() && !message.getAddAsk()){ 
				message.getBlog().getId();
				message.getBlog().getTitle();
			}else if (message.getAnswer() || message.getAddAsk()) { //answer类型的消息需要查出对应的question,避免懒加载
				message.getQuestion().getId();
				message.getQuestion().getTitle();
			}
			message.getOther().getUsername();
			message.getSelf().getUsername();
		}
		return messages;
	}

	/**
	 * 查询消息总数
	 * @return
	 */
	@Override
	public int getMessageCount(User user) {
		String hql = "select count(m.id) from Message m where m.self.id = ?";
		return  ((Long)(messageDao.uniqueResult(hql,user.getId()))).intValue();
	}

	/**
	 * 显示在当前页的消息列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Message> queryMessagePage(User user,int currentPageIndex, int countPerPage) {
		String hql = "SELECT * FROM messages where self = ? order by create_time desc LIMIT ?,?";
		List<Message> messages = (List<Message>) messageDao.listResult(Message.class,hql,user.getId(),
				(currentPageIndex-1) * countPerPage,countPerPage);
		for(Message message : messages){
			//关注、answer、追问类型的消息没有对应的博客
			if(!message.getFocus() && !message.getAnswer() && !message.getAddAsk()){ 
				message.getBlog().getId();
				message.getBlog().getTitle();
			}else if (message.getAnswer() || message.getAddAsk()) { //answer类型的消息需要查出对应的question,避免懒加载
				message.getQuestion().getId();
				message.getQuestion().getTitle();
			}
			message.getOther().getUsername();
			message.getSelf().getUsername();
		}
		return messages;
	}

}
