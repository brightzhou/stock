<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>下载哈哈宝APK页面</title>
<base href="<%=basePath%>" />
<script src="scripts/base/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="scripts/sui/mini-all-min.js" type="text/javascript"></script>
<link href="scripts/sui/themes/default/miniui.css" rel="stylesheet"
	type="text/css" />
<link href="scripts/sui/themes/default/plugin.css" rel="stylesheet"
	type="text/css" />
<link href="scripts/sui/themes/icons.css" rel="stylesheet"
	type="text/css" />
<link href="scripts/sui/themes/sqt/skin.css" rel="stylesheet"
	type="text/css" />
<link href="styles/style_sqt.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {
	text-align: center
}
</style>
</head>

<body class="body_fit">
	<div style="margin: 0 auto; border: 1px solid #000; width: 800px; height: 350px;padding-top:40px;color: blue;border-color: green;">
	<div align="center" style="padding-top:40px;">
		<font size="8px" style="font-family: microsoft yahei">请在注册的时候输入你的推进人ID</font>
	</div>
	<div align="center"  style="padding-top: 20px;">
		<font size="8px" style="font-family: microsoft yahei">你的推荐人ID是:&nbsp;&nbsp;${refereeId}</font>
	</div>
	<div align="center" style="padding-top: 37px;">
		<input type="button" value="注册" onclick="register()" style="width: 180px;height: 80px;font-size: 30px;"/>
	</div>
	<div align="center" style="padding-top: 40px;">
		<input type="button" value="下载【哈哈宝】" onclick="downLoad()" style="width: 240px;height: 80px;font-size: 30px;"/>
		<%-- <span class="link_2"><a href="${apkPath}" style="cursor: pointer;"><font size="12px">点我下载【哈哈宝】</font></a></span> --%>
	</div>
	</div>
	<script type="text/javascript">
		mini.parse();
		
		
		function downLoad(){
			window.location.href = '${apkPath}';
		}
		
		function register(){
			var path = '${apkPath}';
			window.location.href = '<%=basePath%>pages/register.jsp?refereeId='+${refereeId}+'&apkPath='+path;
		}
	</script>
</body>
</html>
