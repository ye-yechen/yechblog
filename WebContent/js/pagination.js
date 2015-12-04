/**
 * 消息分页
 */
var $msgPage = 1;
var $msgPageCount = 1;
function messagePagination() {
	// 消息请求分页
	$.ajax({
		// 请求处理页
		url : "BlogAction_messagePagination",
		dataType : "json",
		type : "POST",
		data : {
			"pageIndex" : $msgPage++
		},
		beforeSend: function () {   
        	if(($msgPage-1) > $msgPageCount){
        		alert("没有更多了!");
        		return false;
        	}
        },
		error : function(result) {
//			alert(result);
		},
		// 处理成功返回的数据
		success : function(result) {
			$.each(result, function(index, element) {
				$msgPageCount = element.pageCount;
				var $parentUl = $("<ul class='notice-list'></ul>");// 创建一个父ul
				var $childLi = $("<li></li>"); // 创建 li
				if (element.collect == true) { // 收藏的消息
					var $otherName = $("<a href='#'>" + element.otherName+ "</a>");
					$otherName.attr('href','UserAction_toOtherHomePage?userId='
									+ element.otherId);
					$childLi.append("收藏了&nbsp;&nbsp;");
					$childLi.append($otherName);
					$childLi.append("&nbsp;&nbsp;的博客&nbsp;&nbsp;");
					$childLi.append("<div class='pull-right'><i>"+ element.createTime +"</i></div>");
					var $blogTitle = $("<a href='#'>" + element.blogTitle+ "</a>");
					$blogTitle.attr('href', 'BlogAction_readDetail?bid='
							+ element.blogId);
					$childLi.append($blogTitle);
					$parentUl.append($childLi);
					// $("#myActivity").append($parentUl);
					$("#myActivity").append($("#queryMore").before($parentUl));
				} else if (element.focus == true) { // 关注的消息
					var $otherName = $("<a href='#'>" + element.otherName+ "</a>");
					$otherName.attr('href','UserAction_toOtherHomePage?userId='
									+ element.otherId);
					$childLi.append("关注了&nbsp;&nbsp;");
					$childLi.append($otherName);
					$childLi.append("<div class='pull-right'><i>"+ element.createTime +"</i></div>");
					$parentUl.append($childLi);
					// $("#myActivity").append($parentUl);
					$("#myActivity").append($("#queryMore").before($parentUl));
				} else if (element.comment == true) { // 评论的消息
					var $otherName = $("<a href='#'>" + element.otherName+ "</a>");
					$otherName.attr('href','UserAction_toOtherHomePage?userId='
									+ element.otherId);
					$childLi.append("评论了&nbsp;&nbsp;");
					$childLi.append($otherName);
					$childLi.append("&nbsp;&nbsp;的博客&nbsp;&nbsp;");
					var $blogTitle = $("<a href='#'>" + element.blogTitle+ "</a>");
					$blogTitle.attr('href', 'BlogAction_readDetail?bid='
							+ element.blogId);
					$childLi.append($blogTitle);
					$childLi.append("<div class='pull-right'><i>"+ element.createTime +"</i></div>");
					$parentUl.append($childLi);
					// $("#myActivity").append($parentUl);
					$("#myActivity").append($("#queryMore").before($parentUl));
				} else if (element.love == true) { // 赞 的消息
					var $otherName = $("<a href='#'>" + element.otherName+ "</a>");
					$otherName.attr('href','UserAction_toOtherHomePage?userId='
									+ element.otherId);
					$childLi.append("赞了&nbsp;&nbsp;");
					$childLi.append($otherName);
					$childLi.append("&nbsp;&nbsp;的博客&nbsp;&nbsp;");
					var $blogTitle = $("<a href='#'>" + element.blogTitle+ "</a>");
					$blogTitle.attr('href', 'BlogAction_readDetail?bid='
																+ element.blogId);
					$childLi.append($blogTitle);
					$childLi.append("<div class='pull-right'><i>"+ element.createTime +"</i></div>");
					$parentUl.append($childLi);
					// $("#myActivity").append($parentUl);
					$("#myActivity").append($("#queryMore").before($parentUl));
				} else if (element.share == true) { // 分享的消息
					var $otherName = $("<a href='#'>" + element.otherName+ "</a>");
					$otherName.attr('href','UserAction_toOtherHomePage?userId='
									+ element.otherId);
					$childLi.append("分享了&nbsp;&nbsp;");
					$childLi.append($otherName);
					$childLi.append("&nbsp;&nbsp;的博客&nbsp;&nbsp;");
					var $blogTitle = $("<a href='#'>" + element.blogTitle+ "</a>");
					$blogTitle.attr('href', 'BlogAction_readDetail?bid='
															+ element.blogId);
					$childLi.append($blogTitle);
					$childLi.append("<div class='pull-right'><i>"+ element.createTime +"</i></div>");
					$parentUl.append($childLi);
					$("#myActivity").append($("#queryMore").before($parentUl));
				} else if(element.reply == true){	//回复的消息
					$childLi.append("在&nbsp;&nbsp;");
					var $blogTitle = $("<a href='#'>" + element.blogTitle+ "</a>");
					$blogTitle.attr('href', 'BlogAction_readDetail?bid='
															+ element.blogId);
					$childLi.append($blogTitle);
					$childLi.append("&nbsp;中回复了&nbsp;");
					var $otherName = $("<a href='#'>" + element.otherName+ "</a>");
					$otherName.attr('href','UserAction_toOtherHomePage?userId='
									+ element.otherId);
					$childLi.append($otherName);
					$childLi.append("<div class='pull-right'><i>"+ element.createTime +"</i></div>");
					$parentUl.append($childLi);
					$("#myActivity").append($("#queryMore").before($parentUl));
				} else if(element.answer == true){
					var $otherName = $("<a href='#'>" + element.otherName+ "</a>");
					$otherName.attr('href','UserAction_toOtherHomePage?userId='
									+ element.otherId);
					$childLi.append("回答了&nbsp;&nbsp;");
					$childLi.append($otherName);
					$childLi.append("&nbsp;&nbsp;的问题 &nbsp;&nbsp;");
					var $questionTitle = $("<a href='#'>" + element.questionTitle+ "</a>");
					$questionTitle.attr('href', 'QuestionAction_readDetail?qid='
															+ element.questionId);
					$childLi.append($questionTitle);
					$childLi.append("<div class='pull-right'><i>"+ element.createTime +"</i></div>");
					$parentUl.append($childLi);
					$("#myActivity").append($("#queryMore").before($parentUl));
				} else if(element.addAsk == true){
					var $otherName = $("<a href='#'>" + element.otherName+ "</a>");
					$otherName.attr('href','UserAction_toOtherHomePage?userId='
									+ element.otherId);
					$childLi.append("追问了&nbsp;&nbsp;");
					$childLi.append($otherName);
					$childLi.append("&nbsp;&nbsp;");
					var $questionTitle = $("<a href='#'>" + element.questionTitle+ "</a>");
					$questionTitle.attr('href', 'QuestionAction_readDetail?qid='
															+ element.questionId);
					$childLi.append($questionTitle);
					$childLi.append("<div class='pull-right'><i>"+ element.createTime +"</i></div>");
					$parentUl.append($childLi);
					$("#myActivity").append($("#queryMore").before($parentUl));
				}
			});
		}
	});
}

/**
 * 博客分页
 */
var $blogPage = 1;
var $blogPageCount = 1;
function blogPagination(){
	// 博客请求分页
	$.ajax({
		// 请求处理页
		url : "BlogAction_blogPagination",
		dataType : "json",
		type : "POST",
		data : {
			"pageIndex" : $blogPage++
		},
		beforeSend: function () {   
        	if(($blogPage-1) > $blogPageCount){
        		alert("没有更多了!");
        		return false;
        	}
        },
		error : function(result) {
//			alert("error");
		},
		// 处理成功返回的数据
		success : function(result) {
			$.each(result, function(index, element) {
				$blogPageCount = element.pageCount;
				var $tr = $("<tr></tr>");
				var $td1 = $("<td></td>");
				var $blogTitle = $("<a href='#'>" + element.blogTitle+ "</a>");
				$blogTitle.attr('href', 'BlogAction_readDetail?bid='+ element.blogId);
				$td1.append($blogTitle);
				$td1.append("("+ element.createTime +")");
				$tr.append($td1);
				
				var $td2 = $("<td>"+ element.readCount +"</td>");
				$tr.append($td2);
				
				var $td3 = $("<td>"+ element.commentSize +"</td>");
				$tr.append($td3);
				
				var $td4 = $("<td></td>");
				var $allowCm = $("<a href='#'></a>");
				if(element.allowComment == true){
					$allowCm.append("禁止评论");
				}else{
					$allowCm.append("允许评论");
				}
				$allowCm.attr('href','BlogAction_changeAllowState?bid='
						+element.blogId);
				$td4.append($allowCm);
				$tr.append($td4);
				var $td5 = $("<td></td>");
				var $i1 = $("<i></i>");
				var $edit = $("<a href='#'></a>");
				$edit.attr('href','BlogAction_editBlog?bid='+element.blogId);
				$edit.append("编辑&nbsp;&nbsp;");
				$i1.append($edit);
				$td5.append($i1);
				var $i2 = $("<i></i>");
				var $delete = $("<a href='#'></a>");
				$delete.attr('href','BlogAction_deleteBlog?bid='+element.blogId);
				$delete.append("删除博客");
				$i2.append($delete);
				$td5.append($i2);
				var $i3 = $("<i></i>");
				var $category = $("<a href='#'></a>");
//				$category.attr('href','BlogAction_deleteBlog?bid='
//						+element.blogId);
				$category.append("&nbsp;&nbsp;分类&nbsp;");
				$i3.append($category);
				$td5.append($i3);
				$tr.append($td5);
				$("#blogTab").append($tr);
			});
		}
	});
}

/**
 * 收藏的博客分页
 */
var $collectionPage = 1;
var $collectionPageCount = 1;
function collectionPagination(){
	// 博客请求分页
	$.ajax({
		// 请求处理页
		url : "BlogAction_collectionPagination",
		dataType : "json",
		type : "POST",
		data : {
			"pageIndex" : $collectionPage++
		},
		beforeSend: function () {   
        	if(($collectionPage-1) > $collectionPageCount){
        		alert("没有更多了!");
        		return false;
        	}
        },
		error : function(result) {
//			alert("error");
		},
		// 处理成功返回的数据
		success : function(result) {
			$.each(result, function(index, element) {
				$collectionPageCount = element.pageCount;
				var $tr = $("<tr></tr>");
				var $td1 = $("<td></td>");
				var $blogTitle = $("<a href='#'>" + element.blogTitle+ "</a>");
				$blogTitle.attr('href', 'BlogAction_readDetail?bid='+ element.blogId);
				$td1.append($blogTitle);
				$td1.append("("+ element.collectTime +")");
				$tr.append($td1);
				
				var $td2 = $("<td></td>");
				var $i = $("<i></i>");
				var $delete = $("<a href='#'></a>");
				$delete.attr('href','BlogAction_removeTheCollection?bid='+element.blogId);
				$delete.append("删除收藏");
				$i.append($delete);
				$td2.append($i);
				$tr.append($td2);
				$("#collectTab").append($tr);
			});
		}
	});
}

/**
 * 问题分页
 */
var $questiontionPage = 1;
var $questionPageCount = 1;
function questionPagination(){
	// 博客请求分页
	$.ajax({
		// 请求处理页
		url : "BlogAction_questionPagination",
		dataType : "json",
		type : "POST",
		data : {
			"pageIndex" : $questiontionPage++
		},
		beforeSend: function () {   
        	if(($questiontionPage-1) > $questionPageCount){
        		alert("没有更多了!");
        		return false;
        	}
        },
		error : function(result) {
//			alert("error");
		},
		// 处理成功返回的数据
		success : function(result) {
			$.each(result, function(index, element) {
				$questionPageCount = element.pageCount;
				var $tr = $("<tr></tr>");
				var $td1 = $("<td></td>");
				var $questionTitle = $("<a href='#'>" + element.questionTitle+ "</a>");
				$questionTitle.attr('href', 'QuestionAction_readDetail?qid='+ element.questionId);
				$td1.append($questionTitle);
				$td1.append("("+ element.createTime +")");
				$tr.append($td1);
				
				var $td2 = $("<td>"+ element.readCount +"</td>");
				$tr.append($td2);
				
				var $td3 = $("<td>"+ element.answerSize +"</td>");
				$tr.append($td3);
				
				var $td4 = $("<td></td>");
				var $i1 = $("<i></i>");
				var $edit = $("<a href='#'></a>");
				$edit.attr('href','QuestionAction_editQuestion?qid='+element.questionId);
				$edit.append("&nbsp;&nbsp;编辑&nbsp;&nbsp;");
				$i1.append($edit);
				$td4.append($i1);
				var $i2 = $("<i></i>");
				var $delete = $("<a href='#'></a>");
				$delete.attr('href','QuestionAction_deleteQuestion?qid='+element.questionId);
				$delete.append("删除博客");
				$i2.append($delete);
				$td4.append($i2);
				$tr.append($td4);
				$("#questionTab").append($tr);
			});
		}
	});
}

/**
 * 他的问题分页
 */
var $hisBlogPage = 1;
var $hisBlogPageCount = 1;
function hisBlogPagination(){
	// 博客请求分页
	$.ajax({
		// 请求处理页
		url : "UserAction_hisBlogPagination",
		dataType : "json",
		type : "POST",
		data : {
			"pageIndex" : $hisBlogPage++
		},
		beforeSend: function () {   
        	if(($hisBlogPage-1) > $hisBlogPageCount){
        		alert("没有更多了!");
        		return false;
        	}
        },
		error : function(result) {
			alert("error");
		},
		// 处理成功返回的数据
		success : function(result) {
			$.each(result, function(index, element) {
				$hisBlogPageCount = element.pageCount;
				var $tr = $("<tr></tr>");
				var $td1 = $("<td></td>");
				var $blogTitle = $("<a href='#'>" + element.blogTitle+ "</a>");
				$blogTitle.attr('href', 'BlogAction_readDetail?bid='+ element.blogId);
				$td1.append($blogTitle);
				$td1.append("("+ element.createTime +")");
				$tr.append($td1);
				
				var $td2 = $("<td>"+ element.readCount +"</td>");
				$tr.append($td2);
				
				var $td3 = $("<td>"+ element.commentSize +"</td>");
				$tr.append($td3);
				
				$("#hisBlogTab").append($tr);
			});
		}
	});
}

/**
 * 问题分页
 */
var $hisQuestiontionPage = 1;
var $hisQuestionPageCount = 1;
function hisQuestionPagination(){
	// 博客请求分页
	$.ajax({
		// 请求处理页
		url : "UserAction_hisQuestionPagination",
		dataType : "json",
		type : "POST",
		data : {
			"pageIndex" : $hisQuestiontionPage++
		},
		beforeSend: function () {   
        	if(($hisQuestiontionPage-1) > $hisQuestionPageCount){
        		alert("没有更多了!");
        		return false;
        	}
        },
		error : function(result) {
			alert("error");
		},
		// 处理成功返回的数据
		success : function(result) {
			$.each(result, function(index, element) {
				$hisQuestionPageCount = element.pageCount;
				var $tr = $("<tr></tr>");
				var $td1 = $("<td></td>");
				var $questionTitle = $("<a href='#'>" + element.questionTitle+ "</a>");
				$questionTitle.attr('href', 'QuestionAction_readDetail?qid='+ element.questionId);
				$td1.append($questionTitle);
				$td1.append("("+ element.createTime +")");
				$tr.append($td1);
				
				var $td2 = $("<td>"+ element.readCount +"</td>");
				$tr.append($td2);
				
				var $td3 = $("<td>"+ element.answerSize +"</td>");
				$tr.append($td3);
				
				$("#hisQuestionTab").append($tr);
			});
		}
	});
}
