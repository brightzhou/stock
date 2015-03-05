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
        <tr >
	        <td align="center" colspan="3">
	        	<a class="mini-button" iconCls="icon-add" onclick="openOrCloseApp('open')">打开</a>
	        </td>
	        <td align="center" colspan="3">
	        	<a class="mini-button" iconCls="icon-add" onclick="openOrCloseApp('close')">关闭</a>
	        </td>
        </tr>
    </table>    
    
    <script type="text/javascript">
        mini.parse();
        function openOrCloseApp(param) {
            $.ajax({
                url: "<%=basePath%>api/stock/web/manager/openOrCloseApp?flag="+param,
                data: '',
                cache: false,
                success: function (text) {
                	if(param=='open'){
	                	mini.alert('打开成功');
                	}else{
                		mini.alert('关闭成功');
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
