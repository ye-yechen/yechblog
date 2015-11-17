<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
	//导航栏标签的 active 状态切换
	$(function(){
		$('ul.nav > li').click(function (e) {
			//阻止元素发生默认的行为
			//e.preventDefault();
			$('ul.nav > li').removeClass('active');
			$(this).addClass('active');
		});
	})
</script>
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
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> 
					<span class="icon-bar"></span> 
			</button>
			<a class="navbar-brand" href="#"> 
				<img alt="Brand" src="image/logo.png">
			</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav nav-pills">
				<li class="active"><a href="BlogAction_pagination">博客精选<span
						class="sr-only">(current)</span></a></li>
				<li><a href="QuestionAction_pagination">问答社区</a></li>
				<li><a href="#">资源分享</a></li>
				<li><a href="#">美文推送</a></li>
				<li><a href="LoginAction_toDataAnalysePage">数据统计</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">个人中心<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="BlogAction_toPersonalPage">个人主页</a></li>
						<li role="separator" class="divider"></li>
						<li><a href="BlogAction_toWriteBlogPage">写博客</a></li>
						<li><a href="QuestionAction_toAskQuestionPage">提问题</a></li>
						<li><a href="BlogAction_myPagination">我的博客</a></li>
						<li><a href="#">我的问答</a></li>
						<li><a href="#">我的分享</a></li>
					</ul></li>

				<form action="BlogAction_searchBlog" method="post" class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input name="searchCondition" type="text" class="form-control" placeholder="博客名">
					</div>
					<button type="submit" class="btn btn-default">搜索</button>
				</form>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="UserAction_toAboutPage">关于</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<s:if test="#session['user'] != null">
						<li><a href="LoginAction_logout">退出</a><li>
					</s:if>
					<s:else>
						<li><a href="LoginAction_toLoginPage">登录</a></li>
					</s:else>
					<li><a href="RegistAction_toRegistPage">注册</a><li>
				</ul>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	</nav>
</body>
</html>