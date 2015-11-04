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
<title>注册</title>
<link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="css/signin.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

 <script type="text/javascript">  
     function changeImg() {  
        $("#code").attr("src","IdentifyCodeAction_execute?d="+new Date().valueOf());  
     }  
     
 </script>  

</head>
<body>
	<div class="signin">
	<div class="signin-head"><img src="image/logo2.png" alt="" class=""></div>
	<form class="form-signin" action="RegistAction_doRegist" method="post" role="form">
		<input type="text" class="form-control" name="email" placeholder="邮箱" required autofocus>
		<div class="col-sm-4"><font color="RED"><s:fielderror><s:param>email</s:param></s:fielderror></font></div>
		
		<input name="username" type="text" class="form-control" placeholder="用户名" required/>
		<div class="col-sm-4"><font color="RED"><s:fielderror><s:param>username</s:param></s:fielderror></font></div>
		
		<input name="password" type="password" class="form-control" placeholder="密码" required />
		<div class="col-sm-4"><font color="RED"><s:fielderror><s:param>password</s:param></s:fielderror></font></div>
		
		<input type="text" class="form-control" name="identifyCode" id="identifyCode"
					placeholder="验证码">
		<img alt="验证码" id="code" src="IdentifyCodeAction_execute">
		<a href="#" onclick="changeImg()">看不清，换一张！</a>
		<font color="RED"><s:fielderror><s:param>identifyCode</s:param></s:fielderror></font>
		<div class="alert alert-danger" role="alert">请务必填写真实邮箱，以便激活账号!</div>
		<button type="submit" class="btn btn-block btn-warning">注册</button>
	</form>
	</div>
</body>
</html>