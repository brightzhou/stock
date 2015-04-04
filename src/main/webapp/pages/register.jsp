<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String refereeId = request.getParameter("refereeId");
String apkPath = request.getParameter("apkPath");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<base href="<%=basePath%>"/>
	<script src="scripts/base/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="scripts/sui/mini-all-min.js" type="text/javascript"></script>
	<script src="scripts/base/jquery-1.7.1.min.js" type="text/javascript"></script>
	<link href="scripts/sui/themes/default/miniui.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/default/plugin.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/icons.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/sqt/skin.css" rel="stylesheet" type="text/css" />
	<link href="styles/style_sqt.css" rel="stylesheet" type="text/css" />
<style>
.info{ 
	margin: 0px auto;
    width: 1080px;
    height: 975px;
    background-image: url("images/register.png");
    padding-top: 945px;

}
.info .name{ 
	width: 981px;
    height: 74px;
    background-image: url(images/register_01.png) ;
    background-repeat:no-repeat;
    margin-left: 49px;

}

input{
    margin-top: -11px;
	margin-left: 337px;
	height: 67px;
	
	font-size: 60px;
	width: 641px;
	border: 0px none;
	background-color: #E4F2F5;
}
.info .password{ 
	 width: 981px;
     height: 74px;
     background-image: url("images/register_02.png") ;
     background-repeat:no-repeat;
     margin-left: 49px;
     margin-top: 63px;
}
.info .passwordTwo{ 
	width: 981px;
    height: 74px;
    background-image: url("images/register_03.png")  ;
	 background-repeat:no-repeat;
	 margin-left: 49px;
	 margin-top: 63px;
}
.info .invest{ 
	width: 981px;
    height: 74px;
    background-image: url("images/register_04.png") ;
	 background-repeat:no-repeat;
	 margin-left: 49px;
	 margin-top: 63px;
}
.info .register{ 
	width: 981px;
	height: 116px;
	background-image: url("images/register_05.png");
	background-repeat: no-repeat;
	margin-left: 49px;
	margin-top: 88px;
	cursor: pointer;
}

.info .register:hover{ 
	background-image: url("images/register_06.png");
}

.info .error{ 
	width: 981px;
	margin-left: 49px;
	margin-top: 22px;
	color: #F00;
	height: 67px;
	line-height: 50px;
	font-size: 60px;
}

</style>
</head>

<body class="body_fit" >
   <div class="info">
    <div id="form1" align="left">
      <div  style="clear: both; "></div>
      <div class="name"><input type="text"  name="nickname" id="nickname" />  </div>
      <div class="password"><input type="text"  name="password"  id="password"/></div>
      <div class="passwordTwo"><input type="text"  name="passwordTwo" id="passwordTwo" />
      <div class="invest"><input type="text"  name="refereeId" value="<%=refereeId%>" id="refereeId"/></div>
      <div class="register" id="register" onclick="submitForm()"></div>
      <!-- <div class="error">注册失败</div> -->
      </div>
   </div>
   
<script type="text/javascript">
	mini.parse();
	
	
	
	function submitForm() {           
		
		var nickname = $('#nickname').val();
		var password = $('#password').val();
		var passwordTwo = $('#passwordTwo').val();
		var refereeId = $('#refereeId').val();
	    //提交数据
	/*     var data = form.getData();
	    var json = mini.encode(data); */
	    
	    if(nickname==''){
	    	alert('用户昵称不能为空');
	    	return;
	    }
	    
	    if(password==''){
	    	alert('用户密码不能为空');
	    	return;
	    }else{
		    if(passwordTwo!=password){
		    	alert('两次输入密码不一致,请重新输入');
		    	$('#passwordTwo').val('');
		    	return;
		    }
	    }
	    
	    
	    var data ={nickname:nickname,password:password,refereeId:refereeId}
	    $.ajax({
	        url: "<%=basePath%>api/stock/register",
	        type: "post",
	        data: data,
	        dataType: "json",
	        success: function (text) {
	        	if(text=='1'){
	        		window.location.href = '<%=basePath%>pages/registerSuccess.jsp?apkPath='+'<%=apkPath%>';
	        	}else{
	        		mini.alert("该用户已经注册");
	        	}
	        }
	    });
	}

	//////////////////////////////////////////
	function updateError(e) {
	    var id = e.sender.name + "_error";
	    var el = document.getElementById(id);
	    if (el) {
	        el.innerHTML = e.errorText;
	    }
	}
	function onUserNameValidation(e) {                  
	    updateError(e);
	}
	function onPwdValidation(e) {        
	    updateError(e);
	}
	
	function onRefereeIdValidation(e) {        
	    updateError(e);
	}
</script>
</body>
</html>


