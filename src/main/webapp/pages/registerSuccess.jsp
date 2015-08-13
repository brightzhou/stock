<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	background-image: url("<%=basePath%>images/registerSuccess.png");
	padding-top: 1110px;
	background-color: #e4f2f5;
	
}

.info .desc {
	width: 605px;
	height: 74px;
	background-repeat: no-repeat;
	margin-left: 265px;
	font-size: 50px;
	line-height: 74px;
	font-weight: bold;
}

.info .button {
	margin-top: 117px;
}

.info .button  .left {
	width: 485px;
	height: 131px;
	background-image: url("<%=basePath%>images/registerSuccess_01.png");
	background-repeat: no-repeat;
	margin-left: 41px;
	float: left;
	display: block;
}



.info .button  .right {
	width: 482px;
height: 131px;
background-image: url("<%=basePath%>images/registerSuccess_03.png");
background-repeat: no-repeat;
float: right;
display: block;
margin-right: 36px;
}

#mcover {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.8);
    display: none;
    z-index: 20000;
}
#mcover img {
    position: fixed;
    right: 18px;
    top: 5px;
    z-index: 20001;
}


</style>
</head>

<body>
	<div class="info">
		<div class="desc">点击安装哈哈宝应用软件</div>
		<div class="button">
			<a href="http://121.41.34.71:8082/stock/api/stock/file/download?flag=HaHaBao.apk"   class="left" target="_blank"></a> 
			<a href="https://itunes.apple.com/cn/app/ha-ha-bao/id981024638?mt=8" class="right" target="_blank" ></a>
		</div>

	</div>
	<div id="mcover"  style="display: none;">
		<img src="<%=basePath%>images/openTwo.png">
  </div>
	<script type="text/javascript">
		mini.parse();
		
		function downLoadAndroid(){
			window.location.href = '<%=apkPath%>';
		}
		
				function isWeiXin(){ 
    var ua = window.navigator.userAgent.toLowerCase(); 
	if(ua.match(/MicroMessenger/i) == 'micromessenger'){ 
		document.getElementById('mcover').style.display='block'
	}else{ 
	   
	} 
} 
 isWeiXin();
	</script>
</body>
</html>
