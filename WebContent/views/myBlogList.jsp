<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0, user-scalable=no">
<title>我的博客</title>
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

	<!-- start header -->
	<header class="main-header bgimage">
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<!-- start logo -->
				<a class="branding" href="" title=""><img src="" alt=""></a>
				<img src="" alt="" class="hide">
			</div>
		</div>
	</div>
	</header>
	<!-- 包含 导航栏 -->
	<jsp:include page="/views/navbar.jsp"></jsp:include>
	<section class="content-wrap">
	<div class="container">
		<div class="row">
			<main class="col-md-8 main-content"> 
				<!-- 迭代博客列表 -->
				<s:iterator var="b" value="myBlogList">
					<article id=<s:property value="#b.id" /> class="post tag-spark">
						<div class="post-head">
							<h1 class="post-title">
								<a href="#"><s:property value="#b.title" /></a>
							</h1>
							<div class="post-meta">
								<span class="author">作者：<a href="#"><s:property value="#b.user.username" /></a></span>
								<time class="post-date" datetime="" title=""></time>
							</div>
						</div>
					<div class="post-content">
						<p><s:property value="#b.content" /></p>
					</div>
					<div class="post-permalink">
						<a href="#" class="btn btn-default">阅读全文</a>
					</div>

					<footer class="post-footer clearfix">
						<div class="pull-left tag-list">
							<i class="fa fa-folder-open-o"></i> <a href="#">yech</a>
						</div>
						<div class="pull-right share"></div>
					</footer> 
				</article> 
			</s:iterator>
			<nav class="pagination" role="navigation"> 
				<span class="label label-warning">第 1 页 &frasl; 共 1 页</span> 
			</nav> 
			</main>
			<aside class="col-md-4 sidebar">
			<div class="widget">
				<h4 class="title">yech</h4>
				<div class="content download">
					<a href="" class="btn btn-default btn-block" onclick="">YechBlog</a>
				</div>
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