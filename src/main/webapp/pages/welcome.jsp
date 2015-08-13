<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,width-viewport=640, initial-scale=0.1, user-scalable=yes, minimum-scale=0.55, maximum-scale=0.7"/>
    
<title>欢迎来到哈哈宝</title>
<style type="text/css">
.info{ 
	margin: 0px auto;
	width: 640px;
	background-repeat: no-repeat;
	height: 530px;
	background-image: url("<%=basePath%>images/welcome.png");
	padding-top: 490px;
	background-color: #e4f2f5;
}
.info .one   {
  	width: 535px;
height: 65px;
	background-image: url("<%=basePath%>images/welcome_01.png");
	background-repeat: no-repeat;
	margin-left: 50px;
	float: left;
    display: block;

}


.info .two  {
   width: 535px;
height: 65px;
	background-image: url("<%=basePath%>images/welcome_03.png");
	background-repeat: no-repeat;
	margin-top: 105px;
	margin-left: 50px;
    display: block;
}


 
.info .detail {
    width: 522px;
height: 136px;
margin-top: 6px;
margin-left: 63px;
font-size: 27px;
line-height: 27px;
color: #A29F9F;
 
}

.info .invest {
      width: 522px;
height: 27px;
margin-top: 49px;
margin-left: 63px;
font-size: 27px;
line-height: 27px;
color: #A29F9F;
	
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
<body  >
   <div class="info">
      <a href="<%=basePath%>pages/register.jsp?refereeId=${refereeId}&apkPath=${apkPath}"   class="one"  target="_blank" ></a>
      <a href="<%=basePath%>pages/download.jsp?refereeId=${refereeId}&apkPath=${apkPath}"   class="two"   ></a>
	   <div class="invest">*&nbsp;记得推荐人要写<span style="color:#df7c07;font-weight: bold;">${refereeId}</span>哦!</div>
	  <div class="detail">*&nbsp;哈哈宝是资金秒到的配资软件，我们为有眼光、有梦想的你提供了最快最方便的平台。你只需专注投资，由我们为你提供只有专业机构才能享用的实时风控、快速资金服务。</div>
     
   </div>
   <div id="mcover" onclick="document.getElementById('mcover').style.display='';" style="display: none;">
		<img src="<%=basePath%>images/open.png">
  </div>
   
   
<script type="text/javascript">




 
function downLoad(){
	var path = '${apkPath}';
	window.location.href = '<%=basePath%>pages/download.jsp?refereeId='+${refereeId}+'&apkPath=${apkPath}';
	
}

function register(){
	var path = '${apkPath}';
	window.location.href = '<%=basePath%>pages/register.jsp?refereeId='+${refereeId}+'&apkPath=${apkPath}';
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