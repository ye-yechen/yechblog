<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0, user-scalable=no">
<title>首页</title>
<link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.2.min.js" ></script>
<script type="text/javascript" src="js/bootstrap.min.js" ></script>
</head>
<body>
	<div class="page-header">
 		 <h2 class="text-center"><a href="LoginAction_toLoginPage" namespace="/"><span class="label label-primary">登录</span></a></h2>
	</div>
	<s:if test="#session['user'] != null">
		<div class="" style="text-align: right;">
			<span class="label label-primary">欢迎<s:property value="#session['user'].username" /></span>&nbsp;&nbsp;
		</div>
	</s:if>
</body>
</html>