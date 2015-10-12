<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0, user-scalable=no">
<title>首页</title>
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
				<!-- end logo -->
				<h2 class="text-hide">yechblog is simple and useful!</h2>
				<img src="" alt="" class="hide">
			</div>
		</div>
	</div>
	</header>
	<!-- end header -->
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"> <img alt="Brand"
				src="image/brand.ico">
			</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav nav-pills">
				<li class="active"><a href="/index.jsp">博客精选<span
						class="sr-only">(current)</span></a></li>
				<li><a href="#">论坛天地</a></li>
				<li><a href="#">问答社区</a></li>
				<li><a href="#">资源分享</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">个人中心<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="BlogAction_toWriteBlogPage">写博客</a></li>
						<li><a href="BlogAction_queryAllBlogs">我的博客</a></li>
						<li><a href="#">我的论坛</a></li>
						<li><a href="#">我的收藏</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="#">我的分享</a></li>
					</ul></li>

				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">关于</a></li>
				</ul>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	</nav>

	<section class="content-wrap">
	<div class="container">
		<div class="row">
			<main class="col-md-8 main-content"> 
				<!-- 迭代博客列表 -->
				<s:iterator var="b" value="blogList">
					<article id=<s:property value="#b.id" /> class="panel panel-success">
						<div class="panel-heading">
							<h1 class="panel-title">
								<a href="#"><s:property value="#b.title" /></a>
							</h1>
							<div class="">
								<span class="author">作者：<a href="#"><s:property value="#b.user.username" /></a></span>
								<time class="" datetime="" title=""></time>
							</div>
						</div>
					<div class="panel-body">
						<p><s:property value="#b.content" /></p>
					</div>
					<div class="">
						<a href="#" class="btn btn-success">阅读全文</a>
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

	<!--  
	<div class="page-header">
 		 <h2 class="text-center"><a href="LoginAction_toLoginPage" namespace="/"><span class="label label-primary"></span></a></h2>
	</div>-->
	<s:if test="#session['user'] != null">
		<div class="bg-success" style="">
			<span class="label label-primary">欢迎<s:property
					value="#session['user'].username" /></span>&nbsp;&nbsp;
		</div>
	</s:if>
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