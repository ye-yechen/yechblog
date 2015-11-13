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
<title>登录</title>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="css/signin.css" rel="stylesheet">
</head>
<body>
	<div class="signin">
	<div class="signin-head"><img src="image/logo2.png" alt="" class=""></div>
	<div class="col-sm-offset-2 col-sm-10">
		<font color="RED"><s:actionerror/></font>
	</div>
	<form action="LoginAction_doLogin" method="post" class="form-signin" role="form">
		<input type="text" class="form-control" name="email" placeholder="邮箱" required autofocus>
		<input name="password" type="password" class="form-control" placeholder="密码" required />
		<button class="btn btn-lg btn-warning btn-block" type="submit">登录</button>
		<label class="checkbox">
			<input type="checkbox" value="remember-me"> 记住我
		</label>
	</form>
</div>
</body>
</html>