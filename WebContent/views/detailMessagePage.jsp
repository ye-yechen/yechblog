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

<script type="text/javascript">
	$(function(){
		//设置每个回复区的可见性
		$("span a").click(function(){
			//为了区分每个不同的回复区
			var $id = $(this).attr('id'); //得到点击的id
			if($("#reply_zone_"+$id).is(":hidden")){
				$("#reply_zone_"+$id).show();
			} else {
				$("#reply_zone_"+$id).hide();
			}
		});
	})
</script>

</head>
<body>
	<jsp:include page="/views/navbar.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<main class="col-md-12 main-content">
				<div class="post">
					<s:set var="m" value="model"></s:set>
					<article id="#m.id" class="post tag-spark">
						<s:if test="#m.answer == false && #m.addAsk == false"> <!-- 不是answer类型的消息 -->
							<div class="post-head">
							<div class="pull-left blog-message">
								<a><s:property value="#session['user'].username" /></a>
								&nbsp;发表于&nbsp;&nbsp;<s:property value="#m.blog.createTime" />
							</div>
						</div>
						<div class="post-content">
							<s:property value="#m.blog.title" escapeHtml="false"/>
						</div>
						<div class="message-info">
							<s:property value="#m.blog.content" escapeHtml="false"/>
						</div>
						<s:if test="comments.size() == null">
							<div class="pull-left">
								暂且没有消息!
							</div>
						</s:if>
						<!-- 迭代评论列表 -->
						<s:iterator var="c" value="comments">
							<div class="">
								<a><s:property value="#c.user.username" /></a>&nbsp;评论于
								&nbsp;<s:property value="#c.commentTime" />
							</div>
							<div class="">
								<s:property value="#c.content" />
								<span class="pull-right"><a id="<s:property value='#c.id'/>">回复</a></span>
								<form action="ReplyAction_addReply?cid=<s:property value='#c.id'/>" method="post" style="display: none;" id="reply_zone_<s:property value='#c.id'/>">
									 <div class="form-group">
										<textarea class="form-control"  rows="10" cols="20" name="content" placeholder="回复别人是一种美德..."></textarea>
										<div class="col-sm-offset-10">
											<button type="submit" class="btn btn-default">确定</button>
										</div>
									</div>
								</form>
							</div>
						</s:iterator>
						<!-- 迭代评论的回复列表 -->
							<s:iterator var="r" value="allReplies">
								<s:iterator var="v" value="#r.value">
									<div class="post-footer2">
									<div class="post-comment">
										<span class="author">
											<a href="#"><s:property value="#v.self.username" /></a>&nbsp;回复
											<a href="#"><s:property value="#v.other.username" /></a>&nbsp;:&nbsp;&nbsp;
										</span>
										<p><s:property value="#v.content" /></p>
									</div>
									</div>
								</s:iterator>
							</s:iterator>
						</s:if>
						<s:elseif test="#m.answer == true || #m.addAsk == true"> <!-- 是 answer 类型的消息 -->
								<div class="post-head">
							<div class="pull-left blog-message">
								<a><s:property value="#session['user'].username" /></a>
								&nbsp;提问于&nbsp;&nbsp;<s:property value="#m.question.createTime" />
							</div>
						</div>
						<div class="post-content">
							<s:property value="#m.question.title"/>
						</div>
						<div class="message-info">
							<s:property value="#m.question.content" escapeHtml="false"/>
						</div>
						<s:if test="answers.size() == null">
							<div class="pull-left">
								暂且没有回答!
							</div>
						</s:if>
						<!-- 迭代回答列表 -->
						<s:iterator var="c" value="answers">
							<div class="">
								<a><s:property value="#c.user.username" /></a>&nbsp;回答于
								&nbsp;<s:property value="#c.answerTime" />
							</div>
							<div class="">
								<s:property value="#c.content" />
								<span class="pull-right"><a id="<s:property value='#c.id'/>">回复</a></span>
								<form action="ReplyAction_addQuestionReply?aid=<s:property value='#c.id'/>" method="post" style="display: none;" id="reply_zone_<s:property value='#c.id'/>">
									 <div class="form-group">
										<textarea class="form-control"  rows="10" cols="20" name="content" placeholder="回复  <s:property value='#c.user.username'/>..."></textarea>
										<div class="col-sm-offset-10">
											<button type="submit" class="btn btn-default">确定</button>
										</div>
									</div>
								</form>
							</div>
						</s:iterator>
						
						<!-- 迭代答案的追问列表 -->
							<s:iterator var="r" value="allQuestionReplies">
								<s:iterator var="v" value="#r.value">
									<div class="post-footer2">
									<div class="post-comment">
										<span class="author">
											<a href="#"><s:property value="#v.self.username" /></a>&nbsp;回复
											<a href="#"><s:property value="#v.other.username" /></a>&nbsp;:&nbsp;&nbsp;
										</span>
										<p><s:property value="#v.content" /></p>
										<s:if test="#v.other.username == #session['user'].username">
											<span class="pull-right"><a id="<s:property value='#v.id'/>">回复</a></span>
											<form action="ReplyAction_addQuestionReply?aid=<s:property value='#v.answer.id'/>&userId=<s:property value='#v.self.id'/>" method="post" style="display: none;" id="reply_zone_<s:property value='#v.id'/>">
												 <div class="form-group">
													<textarea class="form-control"  rows="10" cols="20" name="content" placeholder="回复  <s:property value='#v.self.username'/>..."></textarea>
													<div class="col-sm-offset-10">
														<button type="submit" class="btn btn-default">确定</button>
													</div>
												</div>
											</form>
										</s:if>
									</div>
									</div>
								</s:iterator>
							</s:iterator>
						</s:elseif>
					</article>
				</div>
			</main>
		</div>
	</div>
</body>
</html>