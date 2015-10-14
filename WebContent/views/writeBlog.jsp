<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0, user-scalable=no">
<title>写博客</title>
<link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="css/myStyle.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script> 
<script type="text/javascript">
	$(function(){
		CKEDITOR.replace('content'); 
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
                <div class="col-sm-12">
                </div>
            </div>
        </div>
    </header>
	<h1>write your blog!</h1>
	<form action="BlogAction_newBlog" method="post" namespace="/">
		 <div class="form-group">
   		 	<label for="blogtitle">博客标题</label>
    	 	<input type="text" name="title" class="form-control" id="blogtitle" placeholder="Title">
		 </div>
		 <div class="form-group">
   		 	<label for="blogcontent">博客内容</label>
    	 	<textarea class="form-control" rows="30" cols="50" name="content"></textarea>
		 </div>
		 <div class="form-group">
			<div class="col-sm-offset-2 col-sm-4">
				<button type="submit" class="btn btn-default">publish</button>
			</div>
		</div>
	</form>
	
</body>
</html>