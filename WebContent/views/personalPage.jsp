<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0, user-scalable=no">
<title>个人主页</title>
<link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="css/myStyle.css" rel="stylesheet">

<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/quxian.js"></script>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/pagination.js"></script>
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
		messagePagination();//消息分页(显示第一页结果)
		blogPagination();//博客分页(显示第一页结果)
		collectionPagination();//收藏的博客分页(显示第一页结果)
		questionPagination();//问题分页(显示第一页结果)
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
			$("#myQuestions").hide();
		});
		$("#m_friends").click(function(){
			$("#myFriends").show();
			$("#myCollection").hide();
			$("#myActivity").hide();
			$("#myBlogs").hide();
			$("#myQuestions").hide();
		});
		$("#m_collections").click(function(){
			$("#myCollection").show();
			$("#myFriends").hide();
			$("#myActivity").hide();
			$("#myBlogs").hide();
			$("#myQuestions").hide();
		});
		$("#m_bloglist").click(function(){
			$("#myBlogs").show();
			$("#myCollection").hide();
			$("#myFriends").hide();
			$("#myActivity").hide();
			$("#myQuestions").hide();
		});
		$("#m_questions").click(function(){
			$("#myQuestions").show();
			$("#myBlogs").hide();
			$("#myCollection").hide();
			$("#myFriends").hide();
			$("#myActivity").hide();
		});
		//不再关注
		$("td > div > a").click(function(){
			//使用 ajax 的方式删除
			var url = this.href;
			var args = {"time":new Date()};
			//将 <a></a>节点所在<td>从页面删除
			var $td = $(this).parent().parent();
			$.post(url,args,function(data){
				//若 data 的返回值为 1，则提示删除成功，并将当前行删除
				if(data == 1){
					//alert("操作成功!");
					$td.remove();
				} else{
				//若 data 的返回值不为1，则删除失败
					alert("取消关注失败!");
				}
			});
			//取消超链接的默认行为
			return false;
		});
		
		//使用 on 方法为元素进行事件委托(i > a 是由js代码动态生成的元素)
		$("#blogTab,#collectTab,#questionTab").on("click","i > a",function(){
			var $a = $(this).html();
			if($a == "删除博客"){
				//1.点击 delete 时，弹出警告信息
				var flag = confirm("确定要删除这篇博客吗?");
				if(flag){
					//使用 ajax 的方式删除
					var url = this.href;
					var args = {"time":new Date()};
					//将 <td></td>节点所在行从页面删除
					var $td = $(this).parent().parent().parent();
					$.post(url,args,function(data){
						//若 data 的返回值为 1，则提示删除成功，并将当前行删除
						if(data == 1){
							alert("删除成功!");
							$td.remove();
						} else{
						//若 data 的返回值不为1，则删除失败
							alert("删除失败!");
						}
					});
				}
				//取消超链接的默认行为
				return false;
			}
			if($a == "删除收藏"){
				//1.点击 delete 时，弹出警告信息
				var flag = confirm("确定要删除这篇收藏的博客吗?");
				if(flag){
					//使用 ajax 的方式删除
					var url = this.href;
					var args = {"time":new Date()};
					//将 <td></td>节点所在行从页面删除
					var $td = $(this).parent().parent().parent();
					$.post(url,args,function(data){
						//若 data 的返回值为 1，则提示删除成功，并将当前行删除
						if(data == 1){
							alert("删除成功!");
							$td.remove();
						} else{
						//若 data 的返回值不为1，则删除失败
							alert("删除失败!");
						}
					});
				}
				//取消超链接的默认行为
				return false;
			}
			if($a == "删除问题"){
				//1.点击 delete 时，弹出警告信息
				var flag = confirm("确定要删除这个问题吗?");
				if(flag){
					//使用 ajax 的方式删除
					var url = this.href;
					var args = {"time":new Date()};
					//将 <td></td>节点所在行从页面删除
					var $td = $(this).parent().parent().parent();
					$.post(url,args,function(data){
						//若 data 的返回值为 1，则提示删除成功，并将当前行删除
						if(data == 1){
							alert("删除成功!");
							$td.remove();
						} else{
						//若 data 的返回值不为1，则删除失败
							alert("删除失败!");
						}
					});
				}
				return false;
			}
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
				<s:set var="u" value="#session['user']"></s:set>
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
						 <dd class="focus_num"><b><s:property value="allRelations.size()"/></b>关注</dd>
            			 <dd class="fans_num"><b><s:property value="allFocusMe.size()"/></b>粉丝</dd>
					</dl>
					<dl class="">
						<dd class="person-name"><s:property value="#session['user'].username"></s:property></dd>
						<dd class="person-edit  pull-right">
							<a id="edit" href="#" data-toggle="modal" data-target="#myModal">编辑</a>
						</dd>
						<dd class="person-signature"><span><s:property value="#session['user'].notes"/></span></dd>
						<dd class="person-introduce"><span>
							<s:property value="#session['user'].field"/>&nbsp;&nbsp;|&nbsp;&nbsp;
							<s:property value="#session['user'].career"/>&nbsp;&nbsp;|&nbsp;&nbsp;
							<s:property value="#session['user'].username"/>&nbsp;&nbsp;|&nbsp;&nbsp;
							<s:property value="#session['user'].country"/>-<s:property value="#session['user'].province"/>-<s:property value="#session['user'].city"/>&nbsp;&nbsp;|&nbsp;&nbsp;
							<s:if test="#session['user'].gender">女</s:if>
							<s:else>男</s:else>&nbsp;&nbsp;|&nbsp;&nbsp;
							<s:property value="#session['user'].birth"/></span>
						</dd>
					</dl>
				</div>
			 </main>
		</div>
		<div class="row">
			<main class="col-md-12 main-content">
				<ul class="nav nav-tabs">
 					 <li id="m_activity" role="presentation" class="active"><a href="#">我的活动</a></li>
 					 <li id="m_bloglist" role="presentation"><a href="#">我的博客</a></li>
  					 <li id="m_friends" role="presentation"><a href="#">我的伙伴</a></li>
  					 <li id="m_collections" role="presentation"><a href="#">我的收藏</a></li>
  					 <li id="m_questions" role="presentation"><a href="#">我的问题</a></li>
				</ul>
				 <div id="myActivity" class="post">
				 	<div id="queryMore">
				 		<button onclick="messagePagination()" class="btn btn-warning btn-block">查看更多</button>
				 	</div>
				 </div>
				 
				 <div id="myBlogs" class="post" style="display:none;">
				 	<table id="blogTab" class="table table-hover">
				 		<tr>
				 			<th>标题</th>
				 			<th>阅读</th>
				 			<th>评论</th>
				 			<th>评论权限</th>
				 			<th>操作</th>
				 		</tr>
				 	</table>
				 	<div id="">
				 		<button onclick="blogPagination()" class="btn btn-warning btn-block">查看更多</button>
				 	</div>
				 </div>
				 <div id="myCollection" class="post" style="display:none;">
					<table id="collectTab" class="table table-hover">
				 		<tr>
				 			<th>收藏的博客</th>
				 			<th>操作</th>
				 		</tr>
				 	</table>
				 	<div id="">
				 		<button onclick="collectionPagination()" class="btn btn-warning btn-block">查看更多</button>
				 	</div>
				 </div>
				 <div id="myFriends" class="post" style="display:none;">
				 		<div style="color:#959595 ;">我关注的</div>
				 		<table class="table table-hover">
							<s:iterator var="ar" status="s" value="allRelations">
								<s:if test="(#s.index+1)%4!=0">
									<td>
										<!-- 如果上传了个人头像 -->
										<s:if test="#ar.other.image != null">
											<img alt='' src="<s:url value='#ar.other.image'/>" width="40" height="40">
										</s:if>
										<s:else>
											<img alt="" src="image/personImg.jpg" width="40" height="40">
										</s:else>
										<a href="UserAction_toOtherHomePage?userId=<s:property value='#ar.other.id'/>">
											<s:property value="#ar.other.username"/>
										</a>
										<div class="stop-focus"><a href="RelationAction_stopFocus?userId=<s:property value='#ar.other.id'/>">不再关注</a></div>
									</td>
								</s:if>
								<s:else>
									<tr></tr>
										<td>
											<!-- 如果上传了个人头像 -->
											<s:if test="#ar.other.image != null">
												<img alt='' src="<s:url value='#ar.other.image'/>" width="40" height="40">
											</s:if>
											<s:else>
												<img alt="" src="image/personImg.jpg" width="40" height="40">
											</s:else>
											<a href="UserAction_toOtherHomePage?userId=<s:property value='#ar.other.id'/>">
												<s:property value="#ar.other.username"/>
											</a>
											<div class="stop-focus"><a href="RelationAction_stopFocus?userId=<s:property value='#ar.other.id'/>">不再关注</a></div>
										</td>
								</s:else>
								
							</s:iterator>
					</table>
					<div style="color:#959595 ;">关注我的</div>
					<table class="table table-hover">
							<s:iterator var="af" status="s" value="allFocusMe">
								<s:if test="(#s.index+1)%4!=0">
									<td>
										<!-- 如果上传了个人头像 -->
										<s:if test="#af.self.image != null">
											<img alt='' src="<s:url value='#af.self.image'/>" width="40" height="40">
										</s:if>
										<s:else>
											<img alt="" src="image/personImg.jpg" width="40" height="40">
										</s:else>
										<a href="UserAction_toOtherHomePage?userId=<s:property value='#af.self.id'/>">
											<s:property value="#af.self.username"/>
										</a>
									</td>
								</s:if>
								<s:else>
									<tr></tr>
										<td>
											<!-- 如果上传了个人头像 -->
											<s:if test="#af.self.image != null">
												<img alt='' src="<s:url value='#af.self.image'/>" width="40" height="40">
											</s:if>
											<s:else>
												<img alt="" src="image/personImg.jpg" width="40" height="40">
											</s:else>
											<a href="UserAction_toOtherHomePage?userId=<s:property value='#af.self.id'/>">
												<s:property value="#af.self.username"/>
											</a>
										</td>
								</s:else>
							</s:iterator>
					</table>
				 </div>
				 <div id="myQuestions" class="post" style="display:none;">
				 	<table id="questionTab" class="table table-hover">
				 		<tr>
				 			<th>标题</th>
				 			<th>浏览</th>
				 			<th>回答</th>
				 			<th>操作</th>
				 		</tr>
				 	</table>
				 	<div id="">
				 		<button onclick="questionPagination()" class="btn btn-warning btn-block">查看更多</button>
				 	</div>
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
							  		<label>昵称:</label>
							  		<input type="text" name="username" value="<s:property value='#u.username'/>">
							  	</td>
							  	<td>
							  		<label>职位:</label>
							  		<input type="text" name="career" value="<s:property value='#u.career'/>">
							  	</td>
							  </tr>
							  <tr>
							  	<td>
							  		<label>生日:</label>
							  		<input id="birth" class="Wdate" type="text" name="birth" value="<s:property value='#u.birth'/>" 
							  				onFocus="WdatePicker({isShowWeek:false})">
							  	</td>
							  	<td>
							  		<label>性别:</label>
							  		<input type="radio" name="gender" <s:if test="#u.gender == 0">checked="checked"</s:if>>男
							  		<input type="radio" name="gender" <s:if test="#u.gender == 1">checked="checked"</s:if>>女
							  	</td>
							  </tr>
							  <tr>
							  	<td colspan="2">
							  		<label>行业:</label>
							  		<select name="field">
							  			<option value="none">--选择行业--</option>
							  			<option value="计算机">计算机</option>
							  			<option value="互联网/电子商务">互联网/电子商务</option>
							  			<option value="电子/微电子">电子/微电子</option>
							  			<option value="餐饮服务">餐饮服务</option>
							  			<option value="嵌入式">嵌入式</option>
							  			<option value="医疗">医疗</option>
							  			<option value="中介服务">中介服务</option>
							  		</select>
							  	</td>
							  </tr>
							  <tr>
							  	<td colspan="2">
							  		<label>地区:</label>
							  		<select name="province"></select>
							  		<select name="city"></select>
									<script type="text/javascript">
										new PCAS("province","city","area","","","");
									</script>
							  	</td>
							  </tr>
							  <tr>
							  	<td colspan="2">
							  		<label>简介:</label>
							  		<textarea rows="5" cols="75" name="notes" 
							  				 placeholder="一句话介绍自己..."><s:property value='#u.notes'/></textarea>
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
	<jsp:include page="/views/foot.jsp"></jsp:include>
</body>
</html>