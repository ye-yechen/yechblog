<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0, user-scalable=no">
<title>博客精选</title>
<link type="text/css" href="css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="css/myStyle.css" rel="stylesheet">
<link href="xhEditor/prettify/prettify.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="xhEditor/prettify/prettify.js"></script>
<style type="text/css">

.bgimage {
	background-image: url(image/bg.jpg);
	background-position: 40% 40%;
	background-repeat: no-repeat;
	background-size: cover;
}
.badge :empty {
    display: none;
}
</style>

<script type="text/javascript">
	$(function(){
		//使用jQuery.ajaxSetup() 方法设置全局 AJAX 默认选项，捕获刚刚在拦截器里传递的值
		$.ajaxSetup({  
		    cache: false, //关闭ajax缓存
		    contentType:"application/x-www-form-urlencoded;charset=utf-8",   
		    //complete：表示完成请求后触发。
		    //即在success或error触发后触发
		    //XHR:XMLHttpReques
		    complete:function(XHR,textStatus){ 
		    	var resText = XHR.responseText; 
		    
		    	//isNotLogin标识用户当前未登录，需要将页面转到登录页面
		        if(resText=='isNotLogin'){ 
		        	window.location="LoginAction_toLoginPage";
		        }
		    }   
		}); 
		
		$("i > a").click(function(){
			var $a = $(this).html();
			if($a == "收藏"){
				//使用 ajax 的方式处理收藏
				var url = this.href;
				var args = {"time":new Date()};
				$.post(url,args,function(data){
					//若 data 的返回值为 1，则提示删除成功，并将当前行删除
					if(data == 1){
						alert("收藏成功^_^!");
						$(".collect").attr("disabled",true);
					} else if(data == 0){
						//若 data 的返回值不为1，则删除失败
						alert("已经收藏过了*><*!");
					} else{
						alert("先去登录!");
					}
				});
				//取消超链接的默认行为
				return false;
			}else if($a == "分享"){
				var $id = $(this).attr('id');
				if($("#sharezone_"+$id).is(":hidden")){
					$("#sharezone_"+$id).show();
				}else{
					$("#sharezone_"+$id).hide();
				}
			}
		});
		
		$(".author > a").click(function(){
			//得到userId
			var $userId = $(this).parent('.author').attr('id');
			$(this).attr('href','UserAction_toOtherHomePage?userId='+$userId);
		});
		
		//ajax 方式提交表单，搜索好友
		$("#searchit").click(function(){
			//获取输入的用户名
			var $friendName = $("#friendName").val();
			//开始发送数据
			$.ajax({
				//请求处理页
				url:"UserAction_searchFriends",
				dataType: "json",
				type: "POST",
				//传送请求数据
                data: { "friendName" : $friendName},
                beforeSend: function () {   
                	if($friendName == ""){
                		alert("搜索内容不能为空!");
                		return false;
                	}
                },
                //处理成功返回的数据
                success:function(retVal){
                	$.each(retVal,function(index,element){
	                	var $parentdiv = $("<div></div>");//创建一个父div
	                	var $childa = $("<a href='#'>"+element.username+"</a>");
	                	$childa.attr('href','UserAction_toOtherHomePage?userId='+element.id);
	                	$parentdiv.append($childa);
	                	$("#search-result").append($parentdiv);
                	});
	                $("#search-result").show();
                }
			})
			return false;
		});
		
	})
	
</script>

</head>
<body>
	<!-- 包含 导航栏 -->
	<jsp:include page="/views/navbar.jsp"></jsp:include>

	<section class="content-wrap">
	<div class="container">
		<div class="row">
			<main class="col-md-9 main-content"> 
				<s:if test="allBlogList == null || allBlogList.size() == 0">
					<article class="post">
						<div class="post-content">
							现在还没有人写博客！赶紧注册加入我们吧，你就是我们的第一个用户！
							我们将会是创造奇迹的一群人!
						</div>
					</article>
				</s:if>
				<!-- 迭代博客列表 -->
				<s:else>
				<s:iterator var="b" value="allBlogList">
					<article id=<s:property value="#b.id" /> class="post ">
						<div class="post-head">
							<h1 id="title_<s:property value="#b.id" />" class="post-title">
								<s:property value="#b.title" />
							</h1>
							<div class="post-meta">
								<span class="author" id="<s:property value='#b.user.id' />">
									作者：
									<a href=""><s:property value="#b.user.username" /></a>
								</span>
								<span class="post-date"><s:property value="#b.createTime" /></span>
							</div>
						</div>
					<div class="post-content">
						<p id="summary_<s:property value="#b.id" />">
							<s:property value="#b.summary" escapeHtml="false"/>
						</p>
					</div>
					<div class="post-permalink">
						<a href="BlogAction_readDetail?bid=<s:property value='#b.id' />" class="btn btn-default">阅读全文</a>
					</div>

					<footer class="post-footer clearfix">
						<div class="pull-left tag-list">
							<i class="fa-folder-open-o"></i> 
							<span>标签</span>
							<s:iterator var="bt" value="#b.tags">
								<i class="tag-cloud"><a href="BlogAction_similarBlogsPagination?tagName=<s:property value="#bt.tagName" />"><s:property value="#bt.tagName" /></a></i>
							</s:iterator>
						</div>
						<div class="pull-right share">
							<i class="collect" ><a href="BlogAction_addToCollections?bid=<s:property value='#b.id' />">收藏</a></i>&nbsp;&nbsp;&nbsp;
							<i class=""><a id="<s:property value='#b.id' />">分享</a></i>
						</div>
						<div id="sharezone_<s:property value='#b.id' />" class="jiathis_style_32x32" style="display: none">
								<a class="jiathis_button_douban"></a>
								<a class="jiathis_button_weixin"></a>
								<a class="jiathis_button_qzone"></a>
								<a class="jiathis_button_cqq"></a>
								<a class="jiathis_button_tsina"></a>
								<a class="jiathis_button_tqq"></a>
								<a class="jiathis_button_ydnote"></a>
								<a class="jiathis_button_evernote"></a>
						   		<script type="text/javascript" >
						   		var jiathis_config;
						   		$("i > a").click(function(){
						   			var iid = $(this).attr('id');
						   			jiathis_config={
											url:"http://114.215.92.22:8080",
											title:document.getElementById("title_"+iid).innerHTML,
											summary:document.getElementById("summary_"+iid).innerHTML,
											shortUrl:false,
											hideMore:true,
											appkey:{
										        "tsina":"",
										        "tqq":"",
										        "douban":"",
										        "evernote":"",
										        "ydnote":"",
										        "cqq":"",
										        "weixin":"",
										        "qzone":"",
										    }
										}
						   		});
								</script>
								<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
						</div>
					</footer> 
				</article> 
			</s:iterator>
			</s:else>
			<nav class="pagination" role="navigation"> 
				<span class="label label-warning">第&nbsp;<s:property value="currentPageIndex"/>&nbsp;页 &frasl; 共&nbsp; <s:property value="pageCount"/>&nbsp;页</span>
				 <ul class="pager">
				 	<s:if test="currentPageIndex != 1">
    					<li><a href="BlogAction_pagination?pageIndex=1"><span><font color="#e67e22">首页</font></span></a></li>
    				</s:if>
    				<s:if test="currentPageIndex > 1">
    					<li><a href="BlogAction_pagination?pageIndex=<s:property value='currentPageIndex-1' />"><span><font color="#e67e22">上一页</font></span></a></li>
   					</s:if>
   					<s:if test="currentPageIndex < pageCount">
   						<li><a href="BlogAction_pagination?pageIndex=<s:property value='currentPageIndex+1' />"><span><font color="#e67e22">下一页</font></span></a></li>
   					</s:if>
   					<s:if test="currentPageIndex != pageCount">
   						<li><a href="BlogAction_pagination?pageIndex=<s:property value='pageCount' />"><span><font color="#e67e22">尾页</font></span></a></li>
  					</s:if>
  				</ul>
			</nav> 
			</main>
			<aside class="col-md-3 sidebar">
			
			<div class="widget">
				<h4 class="title">
					<span class="content">YechBlog</span>
				</h4>
					<span class="mylabel">
						<s:if test="#session['user'] != null">
							<span class="welcome">欢迎:</span>&nbsp;&nbsp;&nbsp;&nbsp;
							<span class="name">
								<a href="BlogAction_toPersonalPage"><s:property value="#session['user'].username" /></a>
								<a href="UserAction_toMessageCenter">
									<span class="badge badge-important">
										<s:property value="#session['user'].messages.size()"/>
									</span>
								</a>
							</span>&nbsp;&nbsp;
						</s:if>
						<s:else>
							<div class="content download">
								<a href="LoginAction_toLoginPage" class="btn btn-default btn-block" onclick="">
									<span class="">去登录</span>
								</a>
							</div>
						</s:else>
					</span>
			</div>
			</aside>
			<aside class="col-md-3 sidebar">
				<div class="widget">
					<form id="searchForm" role="search">
						<div class="input-group">
							<input name="friendName" id="friendName" type="text" class="form-control" placeholder="搜好友...">
							<span class="input-group-addon">
							   <a id="searchit" href=""> <img alt="" src="image/search.png"></a>
							</span>
						</div>
					</form>
					<div id="search-result" style="display: none;"> 
						
					</div>
				</div>
			</aside>
		</div>
	</div>
	</section>

<!-- JiaThis Button END -->
	<!--footer
	<footer>
	<div class="container"></div>
	<div class="text-center" id="all-rights">
		Copyright&nbsp;&copy;&nbsp;2015&nbsp;<a href="#">yech blog</a>&nbsp;All
		rights reserved.
	</div>
	</footer>-->
	<jsp:include page="/views/foot.jsp"></jsp:include>
</body>
</html>