//使用jQuery.ajaxSetup() 方法设置全局 AJAX 默认选项，捕获刚刚在拦截器里传递的值
$.ajaxSetup({  
    cache: false, //关闭ajax缓存
    contentType:"application/x-www-form-urlencoded;charset=utf-8",   
    //complete：表示完成请求后触发。
    //即在success或error触发后触发。也有success: 和 error: 等参数，根据需要来
    complete:function(XHR,textStatus){ 
    	var resText = XHR.responseText; 
    	/*根据拦截器传递的标识，进行相关操作*/
    	//isNotLogin标识用户当前未登录，需要将页面转到登录页面
        if(resText=='isNotLogin'){ 
        	window.location="LoginAction_toLoginPage";
        }  
    }   
}); 