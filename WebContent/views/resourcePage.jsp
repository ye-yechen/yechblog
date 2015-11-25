<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0, user-scalable=no">
<title>资源共享</title>
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
	<!-- 包含 导航栏 -->
	<jsp:include page="/views/navbar.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<main class="col-md-12 main-content">
				<div class="post">
					<div class="alert alert-warning" role="alert">资源列表</div>
					<s:if test="resList == null || resList.size() == 0">
						<article class="post">
							<div class="post-content">
								现在还没有资源！赶紧上传吧!
							</div>
						</article>
					</s:if>
					<s:else>
					<table class="table table-hover">
						<tr>
							<th>资源</th>
							<th>资源名称</th>
							<th>资源描述</th>
							<th>资源类型</th>
							<th>上传者</th>
							<th>上传时间</th>
							<th>下载量</th>
						</tr>
						<s:iterator var="r" value="resList">
							<tr>
								<s:if test='#r.resSuffix.equals(".zip")'>
									<td><img alt="" src="image/filetype/zip.ico" width="50" height="50"></td>
								</s:if>
								<s:elseif test='#r.resSuffix.equals(".xml")'>
									<td><img alt="" src="image/filetype/xml.png" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".java")'>
									<td><img alt="" src="image/filetype/jar.png" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".dll")'>
									<td><img alt="" src="image/filetype/dll.ico" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".exe")'>
									<td><img alt="" src="image/filetype/exe.ico" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".xls")'>
									<td><img alt="" src="image/filetype/xls.png" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".xlsx")'>
									<td><img alt="" src="image/filetype/xls.png" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".txt")'>
									<td><img alt="" src="image/filetype/txt.png" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".ruby")'>
									<td><img alt="" src="image/filetype/ruby.png" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".py")'>
									<td><img alt="" src="image/filetype/python.png" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".psd")'>
									<td><img alt="" src="image/filetype/psd.png" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".ppt")'>
									<td><img alt="" src="image/filetype/ppt.png" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".pptx")'>
									<td><img alt="" src="image/filetype/ppt.png" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".png")'>
									<td><img alt="" src="image/filetype/png.ico" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".php")'>
									<td><img alt="" src="image/filetype/php.png" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".js")'>
									<td><img alt="" src="image/filetype/js.png" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".jpg")'>
									<td><img alt="" src="image/filetype/jpg.ico" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".jpeg")'>
									<td><img alt="" src="image/filetype/jpeg.ico" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".jar")'>
									<td><img alt="" src="image/filetype/jar.png" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".html")'>
									<td><img alt="" src="image/filetype/html.png" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".gif")'>
									<td><img alt="" src="image/filetype/gif.ico" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".doc")'>
									<td><img alt="" src="image/filetype/doc.png" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".docx")'>
									<td><img alt="" src="image/filetype/doc.png" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".css")'>
									<td><img alt="" src="image/filetype/css.png" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".chm")'>
									<td><img alt="" src="image/filetype/chm.ico" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".bmp")'>
									<td><img alt="" src="image/filetype/bmp.ico" width="50" height="50"></td>
								</s:elseif>
								<s:elseif test='#r.resSuffix.equals(".asp")'>
									<td><img alt="" src="image/filetype/asp.png" width="50" height="50"></td>
								</s:elseif>
								<s:else>
									<td><img alt="" src="image/filetype/other.ico" width="50" height="50"></td>
								</s:else>
								<td><a href="ResourceAction_downloadResource?rid=<s:property value='#r.id'/>"><s:property value="#r.resName"/></a></td>
								<td><s:property value="#r.resDesc"/></td>
								<td><s:property value="#r.resType"/></td>
								<td>
									<a href="UserAction_toOtherHomePage?userId=<s:property value='#r.uploadUser.id'/>">
										<s:property value="#r.uploadUser.username"/>
									</a>
								</td>
								<td><s:property value="#r.resUploadTime"/></td>
								<td><s:property value="#r.downloadCount"/></td>
							</tr>
						</s:iterator>
					</table>
					</s:else>
				</div>
			</main>
		</div>
	</div>
</body>
</html>