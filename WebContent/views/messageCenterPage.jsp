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
								<a href="#"><s:property value="#m.self.username"/></a>评论了您的博客
									&nbsp;&nbsp;
									<a href="MessageAction_toDetailMessage?bid=<s:property value='#m.blog.id' />&mid=<s:property value='#m.id' />">查看</a>
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
									<a href="#"><s:property value="#om.self.username"/></a>评论了您的博客
										&nbsp;&nbsp;
										<a href="MessageAction_toDetailMessage?bid=<s:property value='#om.blog.id' />&mid=<s:property value='#om.id' />">查看</a>
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