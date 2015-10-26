<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0, user-scalable=no">
<title>个人主页</title>
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
		$("#btn_addImg").click(function(){
			$("#addImgForm").submit();
		});
		
		$("#btn_editInfo").click(function(){
			$("#editInfoForm").submit();
		});
		
		//切换‘我的小活动’、‘我的小伙伴’、‘我的小宝藏’的显示状态
		$("#m_activity").click(function(){
			$("#myActivity").show();
			$("#myFriends").hide();
			$("#myCollection").hide();
			$("#myBlogs").hide();
		});
		$("#m_friends").click(function(){
			$("#myFriends").show();
			$("#myCollection").hide();
			$("#myActivity").hide();
			$("#myBlogs").hide();
		});
		$("#m_collections").click(function(){
			$("#myCollection").show();
			$("#myFriends").hide();
			$("#myActivity").hide();
			$("#myBlogs").hide();
		});
		$("#m_bloglist").click(function(){
			$("#myBlogs").show();
			$("#myCollection").hide();
			$("#myFriends").hide();
			$("#myActivity").hide();
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
							<s:if test="#session['user'].image != null">
								<img alt="" src='<s:url value="%{#session['user'].image}"/>' height="120px" width="120px">
							</s:if>
							<s:else>
								<img alt="" src="image/personImg.jpg">
							</s:else>
						</dt>
						<dd class="person-edit  pull-right">
							<a id="edit" href="#" data-toggle="modal" data-target="#myModal2">上传头像</a>
						</dd>
						 <dd class="focus_num">关注</dd>
            			 <dd class="">粉丝</dd>
					</dl>
					<dl class="">
						<dd class="person-name"><s:property value="#session['user'].username"></s:property></dd>
						<dd class="person-edit  pull-right">
							<a id="edit" href="#" data-toggle="modal" data-target="#myModal">编辑</a>
						</dd>
						<dd class="person-signature"><span>天下第一帅</span></dd>
						<dd class="person-introduce"><span>计算机软件&nbsp;&nbsp;|&nbsp;&nbsp;未填写职位&nbsp;&nbsp;|&nbsp;&nbsp;未填写姓名&nbsp;&nbsp;|&nbsp;&nbsp;中国-安徽省-马鞍山市&nbsp;&nbsp;|&nbsp;&nbsp;男&nbsp;&nbsp;|&nbsp;&nbsp;未填写生日</span></dd>
					</dl>
				</div>
			 </main>
		</div>
		<div class="row">
			<main class="col-md-12 main-content">
				<ul class="nav nav-tabs">
 					 <li id="m_activity" role="presentation" class="active"><a href="#">我的小活动</a></li>
 					 <li id="m_bloglist" role="presentation"><a href="#">我的小博客</a></li>
  					 <li id="m_friends" role="presentation"><a href="#">我的小伙伴</a></li>
  					 <li id="m_collections" role="presentation"><a href="#">我的小宝藏</a></li>
				</ul>
				 <div id="myActivity" class="post">
					<s:iterator var="m" value="allMessages">
						<ul class="notice-list">
							<li>
								<s:if test="#m.comment == true">
									评论了&nbsp;<a href="#"><s:property value="#m.other.username"/></a>&nbsp;的博客
										&nbsp;&nbsp;
										<a href="BlogAction_readDetail?bid=<s:property value='#m.blog.id'/>">
											<s:property value="#m.blog.title"/>
										</a>
								</s:if>
								<s:elseif test="#m.love == true">
									赞了&nbsp;<a href="#"><s:property value="#m.other.username"/></a>&nbsp;的博客
										&nbsp;&nbsp;
										<a href="BlogAction_readDetail?bid=<s:property value='#m.blog.id'/>">
											<span><s:property value="#m.blog.title"/></span>
										</a>
								</s:elseif>
								<s:elseif test="#m.collect == true">
									收藏了&nbsp;<a href="#"><s:property value="#m.other.username"/></a>&nbsp;的博客
										&nbsp;&nbsp;
										<a href="BlogAction_readDetail?bid=<s:property value='#m.blog.id'/>">
											<s:property value="#m.blog.title"/>
										</a>
								</s:elseif>
								<s:elseif test="#m.share == true">
									分享了&nbsp;<a href="#"><s:property value="#m.other.username"/></a>&nbsp;的博客
										&nbsp;&nbsp;
										<a href="BlogAction_readDetail?bid=<s:property value='#m.blog.id'/>">
											<s:property value="#m.blog.title"/>
										</a>
								</s:elseif>
								<s:elseif test="#m.reply == true">
									在&nbsp;<a href="BlogAction_readDetail?bid=<s:property value='#m.blog.id'/>">
										<s:property value="#m.blog.title"/>
									</a>&nbsp;中回复了&nbsp;<a href="#"><s:property value="#m.other.username"/></a>
										&nbsp;&nbsp;
								</s:elseif>
								
								<div class="pull-right">
									<i><s:property value="#m.createTime"/></i>
								</div>
							</li>
						</ul>
					</s:iterator>
				 </div>
				 <div id="myBlogs" class="post" style="display:none;">
				 	<table class="table table-hover">
				 		<tr>
				 			<th>标题</th>
				 			<th>阅读</th>
				 			<th>评论</th>
				 			<th>操作</th>
				 		</tr>
				 		<s:iterator var="mb" value="myBlogList">
				 			<tr>
				 				<td>
				 					<a href="BlogAction_readDetail?bid=<s:property value='#mb.id'/>"><s:property value="#mb.title"/></a>
									(<s:property value="#mb.createTime"/>)
				 				</td>
				 				<td><s:property value="#mb.readCount"/></td>
				 				<td><s:property value="#mb.comments.size()"/></td>
				 				<td>
				 					<i><a href="BlogAction_editBlog?bid=<s:property value='#mb.id'/>">编辑</a></i>&nbsp;
									<i><a href="BlogAction_deleteBlog?bid=<s:property value='#mb.id'/>">删除</a></i>&nbsp;
									<i><a href="#">分类</a></i>&nbsp;
				 				</td>
				 			</tr>
				 		</s:iterator>
				 	</table>
				 </div>
				 <div id="myFriends" class="post" style="display:none;">
					these are my friends!
				 </div>
				 <div id="myCollection" class="post" style="display:none;">
					these are my collections!
				 </div>
			</main>
		</div>
	</div>
	
	
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
	 		  aria-labelledby="myModalLabel" aria-hidden="true">
		   		<div class="modal-dialog">
		   		   <div class="modal-content">
	        		 <div class="modal-header">
	           			 <button type="button" class="close" 
	              			 data-dismiss="modal" aria-hidden="true">&times;
	           			 </button>
	           			 <h4 class="modal-title" id="myModalLabel">
	           			    个人资料
	           			 </h4>
	         		</div>
	         	
	         		 <div class="modal-body">
	         		 	<form action="UserAction_editInfo" method="post" id="editInfoForm">
	         		 		<table class="table table-bordered" border="0">
							  <tr>
							  	<td>
							  		<label>name:</label>
							  		<input type="text" name="username">
							  	</td>
							  	<td>
							  		<label>职位:</label>
							  		<input type="text" name="career">
							  	</td>
							  </tr>
							  <tr>
							  	<td>
							  		<label>生日:</label>
							  		<input type="text" name="birth">
							  	</td>
							  	<td>
							  		<label>性别:</label>
							  		<input type="radio" name="gender">男
							  		<input type="radio" name="gender">女
							  	</td>
							  </tr>
							  <tr>
							  	<td colspan="2">
							  		<label>行业:</label>
							  		<select name="field">
							  			<option value="field">计算机</option>
							  			<option value="field">计算机</option>
							  			<option value="field">计算机</option>
							  			<option value="field">计算机</option>
							  			<option value="field">计算机</option>
							  			<option value="field">计算机</option>
							  			<option value="field">计算机</option>
							  		</select>
							  	</td>
							  </tr>
							  <tr>
							  	<td colspan="2">
							  		<label>地区:</label>
							  		<select name="country">
							  			<option value="">中国</option>
							  			<option value="">中国</option>
							  			<option value="">中国</option>
							  		</select>
							  		<select name="province">
							  			<option value="">安徽</option>
							  			<option value="">安徽</option>
							  			<option value="">安徽</option>
							  		</select>
							  		<select name="city">
							  			<option value="">桐城</option>
							  			<option value="">桐城</option>
							  			<option value="">桐城</option>
							  		</select>
							  	</td>
							  </tr>
							  <tr>
							  	<td colspan="2">
							  		<label>简介:</label>
							  		<textarea rows="5" cols="75" name="notes"></textarea>
							  	</td>
							  </tr>
							</table>
					 	</form>
					</div>
	        	 
		         	<div class="modal-footer">
		           		 <button type="button" class="btn btn-default" 
		              			 data-dismiss="modal">关闭
		        	    </button>
		           		 <button id="btn_editInfo" type="button" class="btn btn-primary">
		             		  提交更改
		          		 </button>
		         	</div>
	      	</div><!-- /.modal-content -->
	      </div>
	</div><!-- /.modal -->
	
	<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" 
	 		  aria-labelledby="myModalLabel" aria-hidden="true">
		 <div class="modal-dialog">
		   <div class="modal-content">
	       		<div class="modal-header">
	       			<button type="button" class="close" 
	              			 data-dismiss="modal" aria-hidden="true">&times;
	           			 </button>
	           			 <h4 class="modal-title" id="myModalLabel">
	           			    上传头像
	           			 </h4>
	       		</div>
	       		<div class="modal-body">
	         		 <form action="UserAction_addImage" method="post" enctype="multipart/form-data" id="addImgForm">
	         		 	<label>头像:</label>
	         		 	<input type="file" name="userImg">
	         		 	<font color="red"><s:fielderror fieldName="userImg"></s:fielderror></font>
	         		 </form>
	         	</div>
	         	<div class="modal-footer">
	         		<button type="button" class="btn btn-default" 
		              			 data-dismiss="modal">关闭</button>
		             <button type="button" class="btn btn-primary" id="btn_addImg">提交更改</button>
	         	</div>
		          
	       </div>
	     </div>
	</div>
</body>
</html>