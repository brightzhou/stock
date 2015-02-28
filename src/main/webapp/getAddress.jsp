<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	String urls  = request.getParameter("url");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>获取地址</title>
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

a:link,a:visited{font-size:12px} 
a:hover,a:active{font-size:16px} 

</style>

</head>

<body class="body_fit">
	<div>
 	<font size="10px">陌陌</font>&nbsp;&nbsp;&nbsp;<a id="J-call-app" href="javascript:;"><font size="300px;">高速下载</font>>></a>
      <input type="button" value="普通下载" style="height:100px;width:300px;font-size: 45px;" id="ptxz" onclick="xz()"/> 
      <br/>
     <font size="10px">QQ</font>&nbsp;&nbsp;&nbsp;<a id="J-call-app_qq" href="javascript:;"><font size="300px;">高速下载</font>>></a>
      <input type="button" value="普通下载" style="height:100px;width:300px;font-size: 45px;" id="ptxz" onclick="xz('qq')"/> 
      <br/>
     <font size="10px">微信</font>&nbsp;&nbsp;&nbsp;<a id="J-call-app_wx" href="javascript:;"><font size="300px;">高速下载</font>>></a>
      <input type="button" value="普通下载" style="height:100px;width:300px;font-size: 45px;" id="ptxz" onclick="xz('wx')"/> 
      <input id="J-download-app" type="hidden" name="storeurl" value="http://121.41.34.71:8082/stock/files/PhotoAssistant.apk">
    </div>

	<script type="text/javascript">
	<%-- var adress = '<%=urls%>'; --%>
	var momo_apk = 'http://192.168.70.103:8080/stock/files/momo.apk';
	var qq_apk = 'http://192.168.70.103:8080/stock/files/QQ.apk';
	var wx_apk = 'http://192.168.70.103:8080/stock/files/weixin.apk';
/* 	var adress = {
		     momo:momo_apk,
		       qq:qq_apk,
		       wx:wx_apk
		};
	var json = mini.encode(adress); */
	
    function gsxz(address){
      	 $.ajax({
             url: '<%=basePath%>api/stock/web/zc/getUrl',
             data: {address:address},
             type: "post",
             success: function (msg) {
            	 //ptxz();
             },
             error: function(msg){
             	alert("获取地址失败");
             	return false;
             }			
		});
    }
    
    function ptxz(){
    	$.ajax({
	            url: '<%=basePath%>api/stock/file/downloadapk',
	            data: {data:""},
	            type: "post",
	            success: function (msg) {
	            	//alert("apk:"+msg);
	            },
	            error: function(msg){
	             	alert("下载apk失败");
	             	return false;
	            }			
		});
    }
    
    function xz(){
    	var url = '<%=basePath%>api/stock/zc/ptxz';
    	window.location.href=url;
    }
    
    (function(){
        var ua = navigator.userAgent.toLowerCase();
        var t;
        var config = {
          /*scheme:必须*/
          scheme_IOS: 'cundong://',
          scheme_Adr: 'cundong://splash/openwith?name='+momo_apk,
          scheme_Adr_qq: 'cundong://splash/openwith?name='+qq_apk,
          scheme_Adr_wx: 'cundong://splash/openwith?name='+wx_apk,
          download_url: document.getElementById('J-download-app').value,
          timeout: 600
        };

        function openclient() {
          gsxz(momo_apk);	
        	
          var startTime = Date.now();

          var ifr = document.createElement('iframe');


          ifr.src = ua.indexOf('os') > 0 ? config.scheme_IOS : config.scheme_Adr;
          ifr.style.display = 'none';
          document.body.appendChild(ifr);

          var t = setTimeout(function() {
            var endTime = Date.now();

            if (!startTime || endTime - startTime < config.timeout + 200) { //如果装了app并跳到客户端后，endTime - startTime 一定> timeout + 200
              window.location = config.download_url;
            } else {

            }
          }, config.timeout);

          window.onblur = function() {
            clearTimeout(t);
          }
        };
        
        window.addEventListener("DOMContentLoaded", function(){
          document.getElementById("J-call-app").addEventListener('click',openclient,false);

        }, false);
        
        function openclientQQ() {
        	gsxz(qq_apk);
            var startTime = Date.now();

            var ifr = document.createElement('iframe');


            ifr.src = ua.indexOf('os') > 0 ? config.scheme_IOS : config.scheme_Adr_qq;
            ifr.style.display = 'none';
            document.body.appendChild(ifr);

            var t = setTimeout(function() {
              var endTime = Date.now();

              if (!startTime || endTime - startTime < config.timeout + 200) { //如果装了app并跳到客户端后，endTime - startTime 一定> timeout + 200
                window.location = config.download_url;
              } else {

              }
            }, config.timeout);

            window.onblur = function() {
              clearTimeout(t);
            }
          };
          
        window.addEventListener("DOMContentLoaded", function(){
            document.getElementById("J-call-app_qq").addEventListener('click',openclientQQ,false);

          }, false);
        
        function openclientWx() {
        	gsxz(wx_apk);
        	
            var startTime = Date.now();

            var ifr = document.createElement('iframe');


            ifr.src = ua.indexOf('os') > 0 ? config.scheme_IOS : config.scheme_Adr_wx;
            ifr.style.display = 'none';
            document.body.appendChild(ifr);

            var t = setTimeout(function() {
              var endTime = Date.now();

              if (!startTime || endTime - startTime < config.timeout + 200) { //如果装了app并跳到客户端后，endTime - startTime 一定> timeout + 200
                window.location = config.download_url;
              } else {

              }
            }, config.timeout);

            window.onblur = function() {
              clearTimeout(t);
            }
          };
          
        window.addEventListener("DOMContentLoaded", function(){
            document.getElementById("J-call-app_wx").addEventListener('click',openclientWx,false);

          }, false);
      })()
</script>
</body>
</html>
