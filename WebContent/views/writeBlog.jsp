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
<link href="xhEditor/prettify/prettify.css" rel="stylesheet" type="text/css" >
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="xhEditor/xheditor-1.2.2.min.js"></script>
<script type="text/javascript" src="xhEditor/xheditor_lang/zh-cn.js"></script>
<script type="text/javascript" src="xhEditor/prettify/prettify.js"></script>
<script type="text/javascript">
	$(function() {
		$(pageInit);  
		function pageInit()
		{
		    var allPlugin={
		        
		        Code:{c:'btnCode',t:'插入代码',h:1,e:function(){
		            var _this=this;
		            var htmlCode='<div><select id="xheCodeType">'
		            +'<option value="html">HTML/XML</option><option value="js">Javascript</option>'
		            +'<option value="css">CSS</option><option value="php">PHP</option>'
		            +'<option value="java">Java</option><option value="py">Python</option>'
		            +'<option value="pl">Perl</option><option value="rb">Ruby</option>'
		            +'<option value="cs">C#</option><option value="c">C++/C</option>'
		            +'<option value="sql">sql</option>'
		            +'<option value="vb">VB/ASP</option><option value="">其它</option>'
		            +'</select></div><div>'
		            +'<textarea id="xheCodeValue" wrap="soft" spellcheck="false" style="width:300px;height:100px;" /></div><div style="text-align:right;"><input type="button" id="xheSave" value="确定" /></div>';    
		            var jCode=$(htmlCode),jType=$('#xheCodeType',jCode),jValue=$('#xheCodeValue',jCode),jSave=$('#xheSave',jCode);
		            jSave.click(function(){
		                _this.loadBookmark();
		               	_this.pasteHTML('<pre class="prettyprint lang-'+jType.val()+'">'+_this.domEncode(jValue.val())+'</pre>');
		                _this.hidePanel();
		                return false;    
		            });
		            _this.saveBookmark();  
		            _this.showDialog(jCode);
		        }}
		    }
		    $('#bcontent').xheditor({plugins:allPlugin,tools:'Cut,Copy,Paste,Pastetext,Blocktag,Fontface,FontSize,FontColor,BackColor,Bold,Italic,Underline,Removeformat,Link,Source,Code,Emot,Table'});
		}

		var b = true;
		//得到焦点
		$("#blogtag").focus(function() {
			$("#usual_tags").show();
		}).blur(function() { //失去焦点
			if(b){
				$("#usual_tags").hide();
			}
		});
		$("#usual_tags").click(function(){
			alert("ss");
			if(b){
				b = false;
			} else {
				b = true;
			}
		});
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
<body onload="prettyPrint()">
	<!-- start header -->
	<header class="main-header bgimage">
	<div class="container">
		<div class="row">
			<div class="col-sm-12"></div>
		</div>
	</div>
	</header>
	<h1 class="col-sm-offset-2">write your blog!</h1>
	<form action="BlogAction_newBlog" method="post" namespace="/"
		class="col-sm-offset-2 col-sm-8">
		<div class="form-group">
			<label for="blogtitle">博客标题</label>
			 <input type="text" name="title"
				class="form-control" id="blogtitle" placeholder="Title">
		</div>
		<div class="form-group">
			<label for="blogsummary">博客摘要(少于200字)</label>
			 <input type="text" name="summary"
				class="form-control" id="blogsummary" placeholder="Summary">
		</div>
		<div class="form-group">
			<label for="blogcontent">博客内容</label>
			<textarea id="bcontent" class="form-control" rows="30" cols="50" name="content"></textarea>
		</div>
		<div class="form-group">
			<label for="blogtag1">博客标签(添加Tag，你的内容能被更多人看到,标签用,分隔)</label> 
			<input
				type="text" name="myTags" class="form-control" id="blogtag"
				placeholder="Tag">
			<div id="usual_tags" style="border: 1px solid #e67e22; display: none;">
				<table id="tag_tab">
					<tr>
						<th>常用标签 &nbsp;</th>
						<td>android &nbsp;</td>
						<td>java &nbsp;</td>
						<td>javaEE &nbsp;</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="alert alert-danger" role="alert">如果不写博客摘要，将自动截取博客内容的前200字符作为摘要</div>
		<div class="form-group">
			<div class="col-sm-4 pull-right">
				<button type="submit" class="btn btn-default">publish</button>
			</div>
		</div>
	</form>
</body>
</html>