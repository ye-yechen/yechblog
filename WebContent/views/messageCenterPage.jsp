<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0, user-scalable=no">
<title>消息中心</title>
<link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="css/myStyle.css" rel="stylesheet">
<link type="text/css" href="css/noticeList.css" rel="stylesheet">

<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<style type="text/css">
body{
	background-image:url();
}
.bgimage {
	background-image: url(image/bg.jpg);
	background-position: 40% 40%;
	background-repeat: no-repeat;
	background-size: cover;
}
</style>
</head>
<body>
	<!-- 包含 导航栏 -->
	<jsp:include page="/views/navbar.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<main class="col-md-12 main-content">
				<ul class="nav nav-tabs">
 					 <li role="presentation" class="active"><a href="#">通知</a></li>
  					 <li role="presentation"><a href="#">私信</a></li>
  					 <li role="presentation"><a href="#">@ 我</a></li>
				</ul>
				<div class="post">
					<s:iterator var="m" value="newMessages">
						<ul class="notice-list">
							<li>
								<s:if test="#m.comment == true">
									<a href="#"><s:property value="#m.self.username"/></a>&nbsp;评论了您的的博客
										&nbsp;&nbsp;
								<a href="MessageAction_toDetailMessage?bid=<s:property value='#m.blog.id' />&mid=<s:property value='#m.id' />">查看</a>
								</s:if>
								<s:elseif test="#m.love == true">
									<a href="#"><s:property value="#m.self.username"/></a>&nbsp;赞了您的博客
										&nbsp;&nbsp;
								<a href="MessageAction_toDetailMessage?bid=<s:property value='#m.blog.id' />&mid=<s:property value='#m.id' />">查看</a>
								</s:elseif>
								<s:elseif test="#m.collect == true">
									<a href="#"><s:property value="#m.self.username"/></a>&nbsp;收藏了您的博客
										&nbsp;&nbsp;
								<a href="MessageAction_toDetailMessage?bid=<s:property value='#m.blog.id' />&mid=<s:property value='#m.id' />">查看</a>
								</s:elseif>
								<s:elseif test="#m.share == true">
									<a href="#"><s:property value="#m.self.username"/></a>&nbsp;分享了您的博客
										&nbsp;&nbsp;
								<a href="MessageAction_toDetailMessage?bid=<s:property value='#m.blog.id' />&mid=<s:property value='#m.id' />">查看</a>
								</s:elseif>
								
								<s:elseif test="#m.reply == true">
									<a href="#"><s:property value="#m.self.username"/></a>&nbsp;在
										<a href="MessageAction_toDetailMessage?bid=<s:property value='#m.blog.id' />&mid=<s:property value='#m.id' />">
											<s:property value="#m.blog.title"/>
										</a>
										中回复了您&nbsp;&nbsp;
								</s:elseif>
								
								<s:elseif test="#m.focus == true">
									<a href="#"><s:property value="#m.self.username"/></a>&nbsp;关注了您
								</s:elseif>
								
								<s:elseif test="#m.answer == true">
									<a href="#"><s:property value="#m.self.username"/></a>&nbsp;回答了您的问题
									<a href="MessageAction_toDetailMessage?qid=<s:property value='#m.question.id' />&mid=<s:property value='#m.id' />">
										<s:property value="#m.question.title"/>
									</a>
								</s:elseif>
								
								<s:elseif test="#m.addAsk == true">
									<a href="#"><s:property value="#m.self.username"/></a>&nbsp;在
										<a href="MessageAction_toDetailMessage?qid=<s:property value='#m.question.id' />&mid=<s:property value='#m.id' />">
											<s:property value="#m.question.title"/>
										</a>
										中追问了您&nbsp;&nbsp;
								</s:elseif>
								<div class="pull-right">
									<i><s:property value="#m.createTime"/></i>
								</div>
							</li>
						</ul>
					</s:iterator>
					<s:if test="newMessages == null || newMessages.size() == 0">
						<div>
							当前没有任何新的消息!
							<s:if test="oldMessages != null && oldMessages.size() != 0">
								以下部分是已读的旧消息
							</s:if>
						</div>
						<s:iterator var="om" value="oldMessages">
							<ul class="notice-list">
								<li>
									<s:if test="#om.comment == true">
									<a href="#"><s:property value="#om.self.username"/></a>&nbsp;评论了您的的博客
										&nbsp;&nbsp;
								<a href="MessageAction_toDetailMessage?bid=<s:property value='#om.blog.id' />&mid=<s:property value='#om.id' />">查看</a>
								</s:if>
								<s:elseif test="#om.love == true">
									<a href="#"><s:property value="#om.self.username"/></a>&nbsp;赞了您的博客
										&nbsp;&nbsp;
								<a href="MessageAction_toDetailMessage?bid=<s:property value='#om.blog.id' />&mid=<s:property value='#om.id' />">查看</a>
								</s:elseif>
								<s:elseif test="#om.collect == true">
									<a href="#"><s:property value="#om.self.username"/></a>&nbsp;收藏了您的博客
										&nbsp;&nbsp;
								<a href="MessageAction_toDetailMessage?bid=<s:property value='#om.blog.id' />&mid=<s:property value='#om.id' />">查看</a>
								</s:elseif>
								<s:elseif test="#om.share == true">
									<a href="#"><s:property value="#om.self.username"/></a>&nbsp;分享了您的博客
										&nbsp;&nbsp;
								<a href="MessageAction_toDetailMessage?bid=<s:property value='#om.blog.id' />&mid=<s:property value='#om.id' />">查看</a>
								</s:elseif>
								
								<s:elseif test="#om.reply == true">
									<a href="#"><s:property value="#om.self.username"/></a>&nbsp;在
										<a href="BlogAction_readDetail?bid=<s:property value='#om.blog.id'/>"><s:property value="#om.blog.title"/></a>
										中回复了您&nbsp;&nbsp;
										<a href="MessageAction_toDetailMessage?bid=<s:property value='#om.blog.id' />&mid=<s:property value='#om.id' />">
											查看
										</a>
								</s:elseif>
								
								<s:elseif test="#om.focus == true">
									<a href="#"><s:property value="#om.self.username"/></a>&nbsp;关注了您
								</s:elseif>
								
								<s:elseif test="#om.answer == true">
									<a href="#"><s:property value="#om.self.username"/></a>&nbsp;回答了您的问题
									<a href="MessageAction_toDetailMessage?qid=<s:property value='#om.question.id' />&mid=<s:property value='#om.id' />">
										<s:property value="#om.question.title"/>
									</a>
								</s:elseif>
								
								<s:elseif test="#om.addAsk == true">
									<a href="#"><s:property value="#om.self.username"/></a>&nbsp;在
										<a href="MessageAction_toDetailMessage?qid=<s:property value='#om.question.id' />&mid=<s:property value='#om.id' />">
											<s:property value="#om.question.title"/>
										</a>
										中追问了您&nbsp;&nbsp;
								</s:elseif>
									<div class="pull-right">
										<i><s:property value="#om.createTime"/></i>
									</div>
								</li>
							</ul>
						</s:iterator>
					</s:if>
				</div>
			</main>
		</div>
	</div>
</body>
</html>