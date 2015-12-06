<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0, user-scalable=no">
<title>提问题</title>
<link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="css/myStyle.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="xhEditor/xheditor-1.2.2.min.js"></script>
<script type="text/javascript" src="xhEditor/xheditor_lang/zh-cn.js"></script>
<script type="text/javascript">
	$(function() {
		$("button[type='submit']").click(function(){
			if($.trim($("input[name='title']").val()) == ''){
				alert("问题标题不能为空!");
				return false;
			}
			if($.trim($("textarea[name='content']").val()) == ''){
				alert("问题内容不能为空!");
				return false;
			}
			if($.trim($("input[name='category']").val()) == ''){
				alert("问题分类不能为空!");
				return false;
			}
			//$("#newblog").submit();
		});
		
	})
</script>
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
	 <nav role="navigation" class="navbar navbar-default">
		  <ul class="nav navbar-nav">
		  	  <li><a href="QuestionAction_pagination">问答社区</a></li>
		  	  <li><a href="BlogAction_pagination">博客精选</a></li>
		  </ul>
	 </nav>
	<h1 class="col-sm-offset-2">发表问题</h1>
	<form action="QuestionAction_newQuestion" method="post" class="col-sm-offset-2 col-sm-8">
		<div class="form-group">
			<label for="questiontitle">问题标题</label>
			 <input type="text" name="title"
				class="form-control" id="questiontitle" placeholder="Title">
		</div>
		<div class="form-group">
			<label for="questioncontent">问题内容</label>
			<textarea class="xheditor {tools:'simple'} form-control" rows="30" cols="50" name="content"></textarea>
		</div>
		
		<div class="form-group">
			<label for="">为问题添加版块</label> 
			 <select id="select_parent_forum" name="category">
     			 <option value="">-选择版块-</option>
     			 <option value="C++/C">C++/C</option>
     			 <option value="java">java/javaEE</option>
     			 <option value="php">php</option>
     			 <option value="c#">C#/.NET</option>
     			 <option value="Android">Android</option>
     			 <option value="ios">ios</option>
     			 <option value="Python">Python</option>
     			 <option value="javascript">javaScript/jQuery</option>
     			 <option value="css">css</option>
     			 <option value="数据库">数据库</option>
     			 <option value="生活">生活</option>
     			 <option value="学习">学习</option>
     			 <option value="其他">其他</option>
   			 </select>
		</div>
		<div class="form-group">
			<div class="col-sm-4 pull-right">
				<button type="submit" class="btn btn-default">发表问题</button>
			</div>
		</div>
	</form>
</body>
</html>