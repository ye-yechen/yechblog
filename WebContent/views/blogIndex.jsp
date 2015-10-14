<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0, user-scalable=no">
<title>博客精选</title>
<link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="css/myStyle.css" rel="stylesheet">

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

	<!-- end header -->
	<!-- 包含 导航栏 -->
	<jsp:include page="/views/navbar.jsp"></jsp:include>

	<section class="content-wrap">
	<div class="container">
		<div class="row">
			<main class="col-md-9 main-content"> 
				<!-- 迭代博客列表 -->
				<s:iterator var="b" value="allBlogList">
					<article id=<s:property value="#b.id" /> class="post ">
						<div class="post-head">
							<h1 class="post-title">
								<s:property value="#b.title" />
							</h1>
							<div class="post-meta">
								<span class="author">作者：<a href="#"><s:property value="#b.user.username" /></a></span>
								<span class="post-date"><s:property value="#b.createTime" /></span>
							</div>
						</div>
					<div class="post-content">
						<p><s:property value="#b.content" /></p>
					</div>
					<div class="post-permalink">
						<a href="BlogAction_readDetail?bid=<s:property value='#b.id' />" class="btn btn-default">阅读全文</a>
					</div>

					<footer class="post-footer clearfix">
						<div class="pull-left tag-list">
							<i class="fa fa-folder-open-o"></i> <a href="#">yech</a>
						</div>
						<div class="pull-right share">
							<i class="fa fa-folder-open-o"></i><a href="#">收藏</a>&nbsp;&nbsp;&nbsp;
							<i class="fa fa-folder-open-o"></i><a href="#">分享</a>
						</div>
					</footer> 
				</article> 
			</s:iterator>
			<nav class="pagination" role="navigation"> 
				<span class="label label-warning">第&nbsp;<s:property value="currentPageIndex"/>&nbsp;页 &frasl; 共&nbsp; <s:property value="pageCount"/>&nbsp;页</span>
				 <ul class="pager">
    				<li><a href="BlogAction_pagenation?pageIndex=1"><span><font color="#e67e22">首页</font></span></a></li>
    				<s:if test="currentPageIndex > 1">
    					<li><a href="BlogAction_pagenation?pageIndex=<s:property value='currentPageIndex-1' />"><span><font color="#e67e22">上一页</font></span></a></li>
   					</s:if>
   					<s:elseif test="currentPageIndex < pageCount">
   						<li><a href="BlogAction_pagenation?pageIndex=<s:property value='currentPageIndex+1' />"><span><font color="#e67e22">下一页</font></span></a></li>
   					</s:elseif>
   						<li><a href="BlogAction_pagenation?pageIndex=<s:property value='pageCount' />"><span><font color="#e67e22">尾页</font></span></a></li>
  				</ul>
			</nav> 
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
								<a href="#"><s:property value="#session['user'].username" /></a>
								<span class="badge">0</span>
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

	<!--footer-->
	<footer>
	<div class="container"></div>
	<div class="text-center" id="all-rights">
		Copyright&nbsp;&copy;&nbsp;2015&nbsp;<a href="#">yech blog</a>&nbsp;All
		rights reserved.
	</div>
	</footer>
</body>
</html>