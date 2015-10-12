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
<title>登录</title>
<link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(function(){
		//进入注册页面
		$("#signup").click(function(){
			 window.location.href="RegistAction_toRegistPage";
		});
	})
</script>

</head>
<body>
	<div class="page-header">
 		 <h2 class="text-center"><span class="label label-primary">用户登录</span></h2>
	</div>
	<form class="form-horizontal" action="LoginAction_doLogin" namespace="/" method="post">
		<div class="form-group">
			<div class="col-sm-offset-4 col-sm-4"><font color="RED"><s:actionerror></s:actionerror></font></div>
		</div>
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="email"
					placeholder="Email">
			</div>
		</div>
		<div class="form-group">
			<label for="inputusername" class="col-sm-2 control-label">UserName</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" name="username"
					placeholder="Username">
			</div>
		</div>
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">Password</label>
			<div class="col-sm-4">
				<input type="password" class="form-control" name="password" id="password"
					placeholder="Password">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-4">
				<div class="checkbox">
					<label> <input type="checkbox" name="remember"> Remember me
					</label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-4">
				<button type="submit" class="btn btn-primary">Sign in</button>
			</div>
			<label class="col-sm-4">
				<button id="signup" type="button" class="btn btn-primary">Sign up</button>
			</label>
		</div>
	</form>
</body>
</html>