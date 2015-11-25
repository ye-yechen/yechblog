<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0, user-scalable=no">
<title>阅读全文</title>
<link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="css/myStyle.css" rel="stylesheet">
<link href="xhEditor/prettify/prettify.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="xhEditor/prettify/prettify.js"></script>
<style type="text/css">
body{
	background-image:url(image/bg2.jpg);
}
.bgimage {
	background-image: url(image/bg.jpg);
	background-position: 40% 40%;
	background-repeat: no-repeat;
	background-size: cover;
}
</style>
<script type="text/javascript">
	$(function(){
		//设置评论区的可见性
		$("#comment").click(function(){
			if($("#comment_zone").is(":hidden")){
				$("#comment_zone").show();
			} else {
				$("#comment_zone").hide();
			}
		});
	})
</script>
</head>
<body onload="prettyPrint()">
	<jsp:include page="/views/navbar.jsp"></jsp:include>
	<section class="content-wrap">
		<div class="container">
			<div class="row">
				<main class="col-md-9 main-content"> 
					<s:set var="b" value="model"></s:set>
					<article id="#b.id" class="post tag-spark">
						<div class="post-head">
							<h1 class="post-title">
								<s:property value="#b.title" />
							</h1>
							<div class="post-meta">
								<span class="author">作者：<a href="UserAction_toOtherHomePage?userId=<s:property value='#b.user.id'/>"><s:property value="#b.user.username" /></a></span>
								<span class="post-date"><s:property value="#b.createTime" /></span>
							</div>
						</div>
						<div class="post-content">
							<p><s:property value="#b.content" escapeHtml="false"/></p>
						</div>
						<!-- 如果博客允许评论 -->
						<s:if test="#b.allowComment">
							<div class="pull-right share">
								<button id="comment" class="btn btn-default">评论</button>
								<!--  <a id="comment" href="#">评论</a>-->
							</div>
							<s:if test="allComments == null || allComments.size() == 0">
								<div class="post-footer clearfix"><span>当前没有任何评论!赶紧去抢沙发!</span></div>
							</s:if>
						</s:if>	
						<s:else>
							<div class="post-footer clearfix"><span>博主关闭了此博客的评论功能!</span></div>
						</s:else>
							<!-- 迭代评论列表 -->
							<s:iterator var="c" value="allComments">
							<div class="post-footer2">
								<div class="post-comment">
									<span class="author"><a href="UserAction_toOtherHomePage?userId=<s:property value='#c.user.id'/>"><s:property value="#c.user.username" /></a>:</span>
									<p><s:property value="#c.content" /></p>
								</div>
							</div>
							</s:iterator>
							<!-- 迭代评论的回复列表 -->
							<s:iterator var="r" value="allReplies">
								<s:iterator var="v" value="#r.value">
									<div class="post-footer2">
									<div class="post-comment">
										<span class="author">
											<a href="UserAction_toOtherHomePage?userId=<s:property value='#v.self.id'/>"><s:property value="#v.self.username" /></a>&nbsp;回复
											<a href="UserAction_toOtherHomePage?userId=<s:property value='#v.other.id'/>"><s:property value="#v.other.username" /></a>&nbsp;:&nbsp;&nbsp;
										</span>
										<p><s:property value="#v.content" /></p>
									</div>
									</div>
								</s:iterator>
							</s:iterator>
						<form action="CommentAction_addComment?bid=<s:property value='#b.id' />" method="post" style="display: none;" id="comment_zone">
							 <div class="form-group">
								<textarea class="form-control"  rows="10" cols="20" name="content" placeholder="说说你的观点..."></textarea>
								<div class="col-sm-offset-10">
									<button type="submit" class="btn btn-default">发表</button>
								</div>
							</div>
						</form>
					</article>
				</main>
				<aside class="col-md-3 sidebar">
			
			<div class="widget">
				<h4 class="title">
					<span class="content">YechBlog</span>
				</h4>
					<span class="mylabel">
						<s:if test="#session['user'] != null">
							<span class="welcome">欢迎:</span>&nbsp;&nbsp;&nbsp;&nbsp;
							<span class="name">
								<a href="BlogAction_toPersonalPage"><s:property value="#session['user'].username" /></a>
								<a href="UserAction_toMessageCenter">
									<span class="badge badge-important">
										<s:property value="#session['user'].messages.size()"/>
									</span>
								</a>
							</span>&nbsp;&nbsp;
						</s:if>
						<s:else>
							<div class="content download">
								<a href="LoginAction_toLoginPage" class="btn btn-default btn-block" onclick="">
									<span class="">去登录</span>
								</a>
							</div>
						</s:else>
					</span>
			</div>
			</aside>
			</div>
		</div>
	</section>
</body>
</html>