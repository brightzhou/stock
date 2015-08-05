<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>哈哈宝开关</title>
	<base href="<%=basePath%>"/>
	<script src="scripts/base/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="scripts/sui/mini-all-min.js" type="text/javascript"></script>
	<link href="scripts/sui/themes/default/miniui.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/default/plugin.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/icons.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/sqt/skin.css" rel="stylesheet" type="text/css" />
	<link href="styles/style_sqt.css" rel="stylesheet" type="text/css" />

</head>

<body class="body_fit" >
<div class="Place_text"><span>哈哈宝开关设置</span></div>  
    <table align="center">
    	<tr>
    		<td align="center" colspan="2">
    			<div id="status"></div>
    		</td>
    	</tr>
        <tr >
	        <td align="center" colspan="3">
	        	<a class="mini-button" iconCls="icon-add" onclick="openOrCloseApp('open','homes')">打开</a>
	        </td>
	        <td align="center" colspan="3">
	        	<a class="mini-button" iconCls="icon-add" onclick="openOrCloseApp('close','homes')">关闭</a>
	        </td>
        </tr>
        <br/>
    	<tr>
    		<td align="center" colspan="2">
    			<div id="statusGuess"></div>
    		</td>
    	</tr>
        <tr >
	        <td align="center" colspan="3">
	        	<a class="mini-button" iconCls="icon-add" onclick="openOrCloseApp('open','guess')">打开</a>
	        </td>
	        <td align="center" colspan="3">
	        	<a class="mini-button" iconCls="icon-add" onclick="openOrCloseApp('close','guess')">关闭</a>
	        </td>
        </tr>
    </table>    
    
    <script type="text/javascript">
        mini.parse();
        
        init();
        function init(){
        	$.ajax({
                url: "api/stock/web/manager/getAppStatus",
                data: '',
                cache: false,
                success: function (text) {
                	var data = mini.decode(text);
                	if(data.HOMES=='open'){
	                	$('#status').append('APP已经打开');
                	}else{
                		$('#status').append('APP已经关闭');
                	}
                	
                	if(data.GUESS=='open'){
	                	$('#statusGuess').append('卖出哈哈币已经打开');
                	}else{
                		$('#statusGuess').append('卖出哈哈币已经关闭');
                	}
                },
                error: function (text) {
                	mini.alert('服务器发生异常');
                }
            });
        }
        
        
        function openOrCloseApp(param,type) {
            $.ajax({
                url: "api/stock/web/manager/openOrCloseApp?flag="+param+"&type="+type,
                data: '',
                cache: false,
                success: function (text) {
                	if('homes'==type){
	                	if(param=='open'){
		                	mini.alert('打开成功');
		                	$('#status').empty();
		                	$('#status').append('APP已经打开');
	                	}else{
	                		mini.alert('关闭成功');
	                		$('#status').empty();
	                		$('#status').append('APP已经关闭');
	                	}
                	}else{
                		if(param=='open'){
		                	mini.alert('打开成功');
		                	$('#statusGuess').empty();
		                	$('#statusGuess').append('卖出哈哈币已经打开');
	                	}else{
	                		mini.alert('关闭成功');
	                		$('#statusGuess').empty();
	                		$('#statusGuess').append('卖出哈哈币已经关闭');
	                	}
                	}
                },
                error: function (text) {
                	mini.alert('服务器发生异常');
                }
            });
        }
        
    </script>
</body>
</html>
