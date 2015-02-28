<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String apkPath = request.getParameter("apkPath");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no,minimum-scale=1.0" />
<title>注册成功</title>
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
a{text-decoration:none}
</style>
</head>

<body class="body_fit">
	<div style="margin: 0 auto; border: 1px solid #000; width: 800px; height: 400px;padding-top:300px;color: blue;border-color: green;">
	<div><font size="8px" style="font-family: microsoft yahei">注册成功</font></div>
	<div align="center" style="padding-top: 40px;">
		<input type="button" value="下载" onclick="downLoad()" style="width: 300px;height: 120px;font-size: 40px;">
	</div>
	</div>
	<script type="text/javascript">
		mini.parse();
		
		function downLoad(){
			window.location.href = '<%=apkPath%>';
		}
	</script>
</body>
</html>
