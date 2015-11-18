<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
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
			$("#hisQuestions").hide();
		});
		$("#h_friends").click(function(){
			$("#hisFriends").show();
			$("#hisBlogs").hide();
			$("#hisQuestions").hide();
		});
		$("#h_questionlist").click(function(){
			$("#hisQuestions").show();
			$("#hisBlogs").hide();
			$("#hisFriends").hide();
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
								<img alt='' src="<s:url value='user.image'/>" height="120px" width="120px">
							</s:if>
							<s:else>
								<img alt="" src="image/personImg.jpg">
							</s:else>
						</dt>
						 <dd class="focus_num"><b><s:property value="hisRelations.size()"/></b>关注</dd>
            			 <dd class="fans_num"><b><s:property value="focusHims.size()"/></b>粉丝</dd>
					</dl>
					<dl class="">
						<dd class="person-name">
							<s:property value="user.username"></s:property>
							<span class="person_add_focus"><a href="RelationAction_addFocus?userId=<s:property value='user.id'/>">关注ta</a></span>
						</dd>
						<dd class="person-signature"><span><s:property value="user.notes"/></span></dd>
						<dd class="person-introduce"><span>
							<s:property value="user.field"/>&nbsp;&nbsp;|&nbsp;&nbsp;
							<s:property value="user.career"/>&nbsp;&nbsp;|&nbsp;&nbsp;
							<s:property value="user.username"/>&nbsp;&nbsp;|&nbsp;&nbsp;
							<s:property value="user.country"/>-<s:property value="user.province"/>-<s:property value="user.city"/>&nbsp;&nbsp;|&nbsp;&nbsp;
							<s:if test="user.gender">女</s:if>
							<s:else>男</s:else>&nbsp;&nbsp;|&nbsp;&nbsp;
							<s:property value="user.birth"/></span>
						</dd>
					</dl>
				</div>
			</main>
		</div>
		
		<div class="row">
			<main class="col-md-12 main-content">
				<ul class="nav nav-tabs">
 					 <li id="h_bloglist" role="presentation" class="active"><a href="#">他的博客</a></li>
 					 <li id="h_questionlist" role="presentation"><a href="#">他的问题</a></li>
  					 <li id="h_friends" role="presentation"><a href="#">他的关系</a></li>
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
					  <div style="color:#959595 ;">他关注的</div>
							<table class="table table-hover">
							<s:iterator var="hr" status="s" value="hisRelations">
								<s:if test="(#s.index+1)%4!=0">
									<td>
										<img alt="" src='image/personImg.jpg' width="40" height="40">
										<a href="#">
											<s:property value="#hr.other.username"/>
										</a>
									</td>
								</s:if>
								<s:else>
									<tr></tr>
										<td>
											<img alt="" src="image/personImg.jpg" width="40" height="40">
											<a href="#">
												<s:property value="#hr.other.username"/>
											</a>
										</td>
								</s:else>
							</s:iterator>
					</table>
					<div style="color:#959595 ;">关注他的</div>
					<table class="table table-hover">
							<s:iterator var="fh" status="s" value="focusHims">
								<s:if test="(#s.index+1)%4!=0">
									<td>
										<img alt="" src='image/personImg.jpg' width="40" height="40">
										<a href="#">
											<s:property value="#fh.self.username"/>
										</a>
									</td>
								</s:if>
								<s:else>
									<tr></tr>
										<td>
											<img alt="" src="image/personImg.jpg" width="40" height="40">
											<a href="#">
												<s:property value="#fh.self.username"/>
											</a>
										</td>
								</s:else>
							</s:iterator>
					</table>
				 </div>
				 <div id="hisQuestions" class="post" style="display:none;">
				  	  <table class="table table-hover">
				 		<tr>
				 			<th>标题</th>
				 			<th>浏览</th>
				 			<th>回答</th>
				 		</tr>
				 		<s:iterator var="hq" value="hisQuestions">
				 			<tr>
				 				<td>
				 					<a href="QuestionAction_readDetail?qid=<s:property value='#hq.id'/>"><s:property value="#hq.title"/></a>
									(<s:property value="#hq.createTime"/>)
				 				</td>
				 				<td><s:property value="#hq.readCount"/></td>
				 				<td><s:property value="#hq.answers.size()"/></td>
				 			</tr>
				 		</s:iterator>
				 	</table>
				 </div>
			</main>
		</div>
	</div>
</body>
</html>