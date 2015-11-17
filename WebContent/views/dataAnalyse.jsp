<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0, user-scalable=no">
<title>数据分析</title>
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
					<div style="color: #e67e22">当前注册总人数:&nbsp;&nbsp;${application.registedUserNums }</div>
					<div style="color: #e67e22">当前登录人数:&nbsp;&nbsp;${application.count }</div>
					<table class="table table-hover">
						<tr>
							<th>用户名</th>
							<th>用户IP</th>
							<th>用户地址</th>
						</tr>
						<!-- 迭代在线用户的用户名、ip和地址 -->
						<s:iterator var="u" status="s" value="#application.userInfo">
							<tr>
								<td><span style="color: #e67e22"><s:property value="key"/></span></td>
								<s:iterator value="value">
								<td>
									<span style="color: #e67e22">
										<s:property value="key"/>
									</span>
								</td>
								<td>
									<span style="color: #e67e22">
										<s:property value="value"/>
									</span>
								</td>
								</s:iterator>
							</tr>
						</s:iterator>
					</table>
				</div>
			</main>
			<aside class="col-md-2 sidebar">
				<div class="widget">
					yechoor
				</div>
			</aside>
			<main class="col-md-8 main-content">
				<div class="post">
					<!-- 迭代含有某标签的博客数量 -->
					<div class="table-responsive">
					<table class="table table-hover">
						<tr><th colspan="2">博客相关</th></tr>
						<s:iterator var="bn" status="s" value="#application.blogNumsWithTag">
								<s:if test="(#s.index)%2!=0">
									<td>
										<span style="color: #959595">含有&nbsp;
											<span style="color: #e67e22">&lt;<s:property value="key"/>&gt;</span>&nbsp;标签的博客数量:&nbsp;
											<span style="color: #e67e22"><s:property value="value"/></span>
										</span>
									</td>
								</s:if>
								<s:else>
									<tr></tr>
									<td>
										<span style="color: #959595">含有&nbsp;
										<span style="color: #e67e22">&lt;<s:property value="key"/>&gt;</span>&nbsp;标签的博客数量:&nbsp;
										<span style="color: #e67e22"><s:property value="value"/></span>
										</span>
									</td>
								</s:else>
						</s:iterator>
					</table>
					</div>
					<!-- 迭代含有某标签的问题数量 -->
					<div class="table-responsive">
					<table class="table table-hover">
						<tr><th colspan="2">问答相关</th></tr>
						<s:iterator var="qn" status="s" value="#application.questionNumsWithTag">
							<s:if test="(#s.index)%2!=0">
									<td>
										<span style="color: #959595">属于&nbsp;
											<span style="color: #e67e22">&lt;<s:property value="key"/>&gt;</span>&nbsp;分类的问答数量:&nbsp;
											<span style="color: #e67e22"><s:property value="value"/></span>
										</span>
									</td>
								</s:if>
								<s:else>
									<tr></tr>
									<td>
										<span style="color: #959595">属于&nbsp;
										<span style="color: #e67e22">&lt;<s:property value="key"/>&gt;</span>&nbsp;分类的问答数量:&nbsp;
										<span style="color: #e67e22"><s:property value="value"/></span>
										</span>
									</td>
								</s:else>
						</s:iterator>
					</table>
					</div>
				</div>
			</main>
			<aside class="col-md-2 sidebar">
				<div class="widget">
					yechoor
				</div>
			</aside>
		</div>
	</div>
</body>
</html>