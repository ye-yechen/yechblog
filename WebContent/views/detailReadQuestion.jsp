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
<title>问题详情</title>
<link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="css/myStyle.css" rel="stylesheet">

<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<style type="text/css">
</style>
<script type="text/javascript">
	$(function(){
		//设置评论区的可见性
		$("#answer").click(function(){
			if($("#answer_zone").is(":hidden")){
				$("#answer_zone").show();
			} else {
				$("#answer_zone").hide();
			}
		});
		$("button[type='submit']").click(function(){
			if($.trim($("textarea[name='content']").val()) == ''){
				alert("评论内容不能为空!");
				return false;
			}
			$("#answer_zone").submit();
		});
	})
</script>
</head>
<body>
	<nav role="navigation" class="navbar navbar-default">
		  <ul class="nav navbar-nav">
		  	  <li><a href="#">话题</a></li>
		  	  <li><a href="QuestionAction_pagination">发现</a></li>
		  	  <li><a href="BlogAction_pagination">博客</a></li>
		  	  <li><a href="QuestionAction_toAskQuestionPage">提问题</a></li>
		  </ul>
	 </nav>
	<section class="content-wrap">
		<div class="container">
			<div class="row">
				<main class="col-md-9 main-content"> 
					<s:set var="q" value="model"></s:set>
					<article id="#q.id" class="post tag-spark">
						<div class="post-head">
							<h1 class="post-title">
								<s:property value="#q.title" />
							</h1>
							<div class="post-meta">
								<span class="author">提问者：<a href="UserAction_toOtherHomePage?userId=<s:property value='#q.user.id'/>"><s:property value="#q.user.username" /></a></span>
								<span class="post-date"><s:property value="#q.createTime" /></span>
							</div>
						</div>
						<div class="post-content">
							<p><s:property value="#q.content" escapeHtml="false"/></p>
						</div>
						<div class="pull-right share">
							<button id="answer" class="btn btn-default">回答</button>
							<!--  <a id="answer" href="#">回答</a>-->
						</div>
						
						<s:if test="allAnswers == null || allAnswers.size() == 0">
							<div class="post-footer clearfix"><span>当前没有任何回答!赶紧去帮帮ta!</span></div>
						</s:if>
						<s:else>
							<!-- 迭代评论列表 -->
							<s:iterator var="c" value="allAnswers">
							<div class="post-footer2">
								<div class="post-comment">
									<span class="author"><a href="UserAction_toOtherHomePage?userId=<s:property value='#c.user.id'/>"><s:property value="#c.user.username" /></a>:</span>
									<p><s:property value="#c.content" /></p>
								</div>
							</div>
							</s:iterator>
							<!-- 迭代评论的回复列表 -->
							<s:iterator var="r" value="allQuestionReplies">
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
						</s:else>
						
						<form action="AnswerAction_addAnswer?qid=<s:property value='#q.id' />" method="post" style="display: none;" id="answer_zone">
							 <div class="form-group">
								<textarea class="form-control"  rows="10" cols="20" name="content" placeholder="说说你的解决方案..."></textarea>
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