<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0, user-scalable=no">
<title>详细消息</title>
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
	<jsp:include page="/views/navbar.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<main class="col-md-12 main-content">
				<div class="post">
					<s:set var="m" value="model"></s:set>
					<article id="#m.id" class="post tag-spark">
						<div class="post-head">
							<div class="pull-left blog-message">
								<a><s:property value="#session['user'].username" /></a>
								&nbsp;发表于&nbsp;&nbsp;<s:property value="#m.blog.createTime" />
							</div>
						</div>
						<div class="post-content">
							<s:property value="#m.blog.title" />
						</div>
						<div class="message-info">
							<s:property value="#m.blog.content" />
						</div>
						<s:if test="comments.size() == null">
							<div class="pull-left">
								暂且没有消息!
							</div>
						</s:if>
						<s:iterator var="c" value="comments">
							<div class="">
								<a><s:property value="#c.user.username" /></a>&nbsp;评论于
								&nbsp;<s:property value="#c.commentTime" />
							</div>
							<div class="">
								<s:property value="#c.content" />
							</div>
						</s:iterator>
					</article>
				</div>
			</main>
		</div>
	</div>
</body>
</html>