<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0, user-scalable=no">
<title>${user.username}的主页</title>
<link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="css/myStyle.css" rel="stylesheet">

<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<style type="text/css">
body{
	background-image:url();
}
.bgimage {
	background-image: url(image/bg.jpg);
	background-position: 40% 40%;
	background-repeat: no-repeat;
	background-size: cover;
}
</style>

<script type="text/javascript">
	$(function(){
		$("#h_bloglist").click(function(){
			$("#hisBlogs").show();
			$("#hisFriends").hide();
		});
		$("#h_friends").click(function(){
			$("#hisFriends").show();
			$("#hisBlogs").hide();
		});
		
		$("span a").click(function(){
			var $a = $(this).html();
			if($a == "关注ta"){
				//使用 ajax 的方式
				var url = this.href;
				var args = {"time":new Date()};
				$.post(url,args,function(data){
					//若 data 的返回值为 1，则提示关注成功
					if(data == 1){
						alert("关注成功!");
					} else if(data == 0){
					//若 data 的返回值不为1，则关注失败
						alert("已经关注过了!");
					}
				});
			}
			//取消超链接的默认行为
			return false;
		});
	})
</script>
</head>
<body>
	<!-- 包含 导航栏 -->
	<jsp:include page="/views/navbar.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<main class="col-md-12 main-content">
				<div class="post">
					<dl class="pull-left">
						<dt class="personal-img">
							<!-- 如果上传了个人头像 -->
							<s:if test="user.image != null">
								<img alt="" src='<s:url value="user.image"/>' height="120px" width="120px">
							</s:if>
							<s:else>
								<img alt="" src="image/personImg.jpg">
							</s:else>
						</dt>
						 <dd class="focus_num">关注</dd>
            			 <dd class="fans_num">粉丝</dd>
					</dl>
					<dl class="">
						<dd class="person-name">
							<s:property value="user.username"></s:property>
							<span class="person_add_focus"><a href="RelationAction_addFocus?userId=<s:property value='user.id'/>">关注ta</a></span>
						</dd>
						<dd class="person-signature"><span>天下第一帅</span></dd>
						<dd class="person-introduce"><span>计算机软件&nbsp;&nbsp;|&nbsp;&nbsp;未填写职位&nbsp;&nbsp;|&nbsp;&nbsp;未填写姓名&nbsp;&nbsp;|&nbsp;&nbsp;中国-北京&nbsp;&nbsp;|&nbsp;&nbsp;男&nbsp;&nbsp;|&nbsp;&nbsp;未填写生日</span></dd>
					</dl>
				</div>
			</main>
		</div>
		
		<div class="row">
			<main class="col-md-12 main-content">
				<ul class="nav nav-tabs">
 					 <li id="h_bloglist" role="presentation" class="active"><a href="#">他的博客</a></li>
  					 <li id="h_friends" role="presentation"><a href="#">他的伙伴</a></li>
				</ul>
				
				 <div id="hisBlogs" class="post">
				 	<table class="table table-hover">
				 		<tr>
				 			<th>标题</th>
				 			<th>阅读</th>
				 			<th>评论</th>
				 		</tr>
				 		<s:iterator var="hb" value="hisBlogs">
				 			<tr>
				 				<td>
				 					<a href="BlogAction_readDetail?bid=<s:property value='#hb.id'/>"><s:property value="#hb.title"/></a>
									(<s:property value="#hb.createTime"/>)
				 				</td>
				 				<td><s:property value="#hb.readCount"/></td>
				 				<td><s:property value="#hb.comments.size()"/></td>
				 			</tr>
				 		</s:iterator>
				 	</table>
				 </div>
				 
				  <div id="hisFriends" class="post" style="display:none;">
					these are his friends!
				 </div>
			</main>
		</div>
	</div>
</body>
</html>