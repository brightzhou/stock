<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎来到哈哈宝</title>
<style type="text/css">
.info{ 
	margin: 0px auto;
	width: 1080px;
	height: 926px;
	background-image: url("images/welcome.png");
	padding-top: 996px;
}
.info .one   {
  	width: 997px;
	height: 120px;
	background-image: url("images/welcome_01.png");
	background-repeat: no-repeat;
	margin-left: 50px;
	float: left;
    display: block;

}

.info .one:hover   {
  background-image: url("images/welcome_02.png");
}
.info .two  {
    width: 997px;
	height: 120px;
	background-image: url("images/welcome_03.png");
	background-repeat: no-repeat;
	margin-top: 192px;
	margin-left: 50px;
    display: block;
}

.info .two:hover{
  background-image: url("images/welcome_04.png");
}


</style>
<body>
   <div class="info">
      <a href="javascript:void();" onclick="downLoad()" class="one"  target="_blank" ></a>
      <a href="javascript:void();" onclick="register()" class="two"  target="_blank" ></a>
   </div>
   
   
<script type="text/javascript">

function downLoad(){
	var path = '${apkPath}';
	window.location.href = '<%=basePath%>pages/download.jsp?refereeId='+${refereeId}+'&apkPath='+path;
	
}

function register(){
	var path = '${apkPath}';
	window.location.href = '<%=basePath%>pages/register.jsp?refereeId='+${refereeId}+'&apkPath='+path;
}
</script>
</body>
</html>