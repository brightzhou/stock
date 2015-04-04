<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String refereeId = request.getParameter("refereeId");
	String apkPath = request.getParameter("apkPath");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="scripts/base/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="scripts/sui/mini-all-min.js" type="text/javascript"></script>
<script src="scripts/base/jquery-1.7.1.min.js" type="text/javascript"></script>
<link href="scripts/sui/themes/default/miniui.css" rel="stylesheet"
	type="text/css" />
<link href="scripts/sui/themes/default/plugin.css" rel="stylesheet"
	type="text/css" />
<link href="scripts/sui/themes/icons.css" rel="stylesheet"
	type="text/css" />
<link href="scripts/sui/themes/sqt/skin.css" rel="stylesheet"
	type="text/css" />
<link href="styles/style_sqt.css" rel="stylesheet" type="text/css" />

<style>
.info {
	margin: 0px auto;
	width: 1080px;
	height: 810px;
	background-image: url("images/download.png");
	padding-top: 1110px;
}

.info .desc {
	width: 981px;
	height: 74px;
	background-image: url("images/download_05.png");
	background-repeat: no-repeat;
	margin-left: 265px;
}

.info .button {
	margin-top: 117px;
}

.info .button  .left {
	width: 485px;
	height: 131px;
	background-image: url("images/download_01.png");
	background-repeat: no-repeat;
	margin-left: 41px;
	float: left;
	display: block;
}

.info .button  .left:hover {
	background-image: url("images/download_02.png");
}

.info .button  .right {
	width: 523px;
	height: 131px;
	background-image: url("images/download_03.png");
	background-repeat: no-repeat;
	float: right;
	display: block;
}

.info .button  .right:hover {
	background-image: url("images/download_04.png");
}
</style>
</head>

<body>
	<div class="info">
		<div class="desc"></div>
		<div class="button">
			<a href="javascript:void();" class="left" onclick="downLoad()" target="_blank"></a> 
			<a href="javascript:void();" class="right" onclick="" target="_blank"></a>
		</div>
	</div>

	<script type="text/javascript">
	    var path = '<%=apkPath%>';
		function downLoad() {
			window.location.href = path;
		}
	</script>
</body>
</html>


