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
<script type="text/javascript" src="js/highcharts.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<style type="text/css">
.bgimage {
	background-image: url(image/bg.jpg);
	background-position: 40% 40%;
	background-repeat: no-repeat;
	background-size: cover;
}
</style>

<script type="text/javascript">
	$(function(){
		$.ajax({
			//请求处理页
			url:"LoginAction_getBlogNumsWithTag",
			dataType: "json",
			type: "POST",
			data:{},
			async: false,
			error: function(result){
				alert(result);
			},
            //处理成功返回的数据
            success:function(result){
            	 var json = result;              
                 var jsondata = [];              
                 for (var i in json) {
                     jsondata.push([i, json[i]]);
                 }   
            	var chart = new Highcharts.Chart({
                    chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false,
                        type: 'pie',
                        renderTo: 'blogChart'
                    },
                    title: {
                        text: '含各标签的博客数量统计'
                    },
                    credits: { 
                        text: 'www.yechoor.cn' 
                    }, 
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                            },
                    	showInLegend: true
                        },
                    },
                    series: [{
                        name: "blog percent",
                        colorByPoint: true,
                        data:jsondata
                     }]
                });
            }
		})
		
		$.ajax({
			//请求处理页
			url:"LoginAction_getQuestionNumsWithCategory",
			dataType: "json",
			type: "POST",
			data:{},
			async: false,
			error: function(result){
				alert(result);
			},
            //处理成功返回的数据
            success:function(result){
            	 var json = result;              
                 var jsondata = [];              
                 for (var i in json) {
                     jsondata.push([i, json[i]]);
                 }   
            	var chart = new Highcharts.Chart({
                    chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false,
                        type: 'pie',
                        renderTo: 'questionChart'
                    },
                    title: {
                        text: '含各标签的问答数量统计'
                    },
                    credits: { 
                        text: 'www.yechoor.cn' 
                    }, 
                    tooltip: {
                        pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                            },
                    	showInLegend: true
                        },
                    },
                    series: [{
                        name: "question percent",
                        colorByPoint: true,
                        data:jsondata
                     }]
                });
            }
		})
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
			<!--  <aside class="col-md-2 sidebar">
				<div class="widget">
					yechoor
				</div>
			</aside> -->
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
			<aside class="col-md-4 sidebar">
				<div class="widget">
					<span class="title" style="color: #77a05f">用户反馈</span>
					<s:iterator var="f" value="feedBackList">
						<div class="fbInfo">
							<span class="author" style="color: #629a9d"><s:property value="#f.user.username"/></span>
							<span class="pull-right" style="color: #78aaab"><s:property value="#f.feedBackTime"/></span>
							<span class="fbcontent"><a data-toggle="modal" data-target="#fbDetail_<s:property value='#f.id'/>"><s:property value="#f.content"/></a></span>
							<!-- 点击反馈信息弹出的模态框 -->
							<div class="modal fade" id="fbDetail_<s:property value='#f.id'/>" tabindex="-1" role="dialog" 
							 		  aria-labelledby="myModalLabel" aria-hidden="true">
								 <div class="modal-dialog">
								   <div class="modal-content">
							       		<div class="modal-header">详细反馈信息</div>
							       		<div class="modal-body">
											<textarea class="form-control" rows="10" cols="20" disabled="disabled">
												<s:property value="#f.content"/>
											</textarea>
							         	</div>
							         	<div class="modal-footer">
							         		<button type="button" class="btn btn-default" 
								              			 data-dismiss="modal">关闭</button>
							         	</div>
							       	</div>
							     </div>
							</div>
							
							<s:if test="#f.state">
								<span class="pull-right" style="color: #6fcc71">已解决</span>
							</s:if>
							<s:else>
								<span class="pull-right" style="color: #d52b40">未解决</span>
							</s:else>
						</div>
					</s:iterator>
				</div>
			</aside>
			<main class="col-md-8 main-content">
				<div class="post">
					<div id="blogChart" style="min-width: 400px; height: 600px; max-width: 600px; margin: 0 auto"></div>
				</div>
			</main>
			<main class="col-md-8 main-content">
				<div class="post">
					<div id="questionChart" style="min-width: 400px; height: 600px; max-width: 600px; margin: 0 auto"></div>
				</div>
			</main>
		</div>
	</div>
	<jsp:include page="/views/foot.jsp"></jsp:include>
</body>
</html>