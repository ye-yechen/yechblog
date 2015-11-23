<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0, user-scalable=no">
<title>上传资源</title>
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
			<div class="col-sm-12"></div>
		</div>
	</div>
	</header>
	<div class="alert alert-info" role="alert">资源上传(除常用文件类型，其他资源请尽量打包上传，避免上传失败)</div>
	<form action="ResourceAction_uploadResource" method="post" 
				enctype="multipart/form-data" class="col-sm-offset-3 col-sm-6">

		<div class="form-group">
	        <input type="file" name="userRes">
		</div>
		<div class="form-group">
			<label for="restype">资源类型</label> 
			<select name="resType">
				<option value="">请选择</option>
				<option value="文档类">文档</option>
				<option value="代码类">代码类</option>
				<option value="工具类">工具类</option>
				<option value="图片图标">图片图标</option>
				<option value="其他">其他</option>
			</select>
		</div>
		<div class="form-group">
			<label for="resscore">资源分</label> 
			<select name="resScore" id="resscore">
				<option value="0" selected="selected"> 0</option>
			  	<option value="1"> 1</option>
			  	<option value="2"> 2</option>
			  	<option value="3"> 3</option>
			  	<option value="4"> 4</option>
			  	<option value="5"> 5</option>
			  	<option value="6"> 6</option>
			  	<option value="7"> 7</option>
			  	<option value="8"> 8</option>
			  	<option value="9"> 9</option>
			  	<option value="10">10</option>
			</select>
		</div>
		<div class="form-group">
			<label for="resdesc">资源描述</label> 
			<textarea id="resdesc" class="form-control" rows="10" cols="20" name="resDesc"></textarea>
		</div>
		<div class="alert alert-danger" role="alert">
			<dl class="">
				<dt>上传须知</dt>
				<dd>* 如涉及侵权内容,您的资源将被移除</dd>
				<dd>* 请勿上传与技术无关的内容.一旦发现将被删除</dd>
				<dd>* 请勿在未经授权的情况下上传任何涉及著作权侵权的资源</dd>
				<dd>* 点击上传资源即表示您确认该资源不违反资源分享的使用条款</dd>
			</dl>
		</div>
		<div class="form-group">
			<div class="col-sm-4 pull-right">
				<button type="submit" class="btn btn-default">上传</button>
			</div>
		</div>
	</form>
</body>
</html>