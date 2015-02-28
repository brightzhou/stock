<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登陆哈哈宝</title>
	<base href="<%=basePath%>"/>
	<script src="scripts/base/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="scripts/sui/mini-all-min.js" type="text/javascript"></script>
	<link href="scripts/sui/themes/default/miniui.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/default/plugin.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/icons.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/sqt/skin.css" rel="stylesheet" type="text/css" />
	<link href="styles/style_sqt.css" rel="stylesheet" type="text/css" />
<style type="text/css">

.mydiv{
    background-image:url('<%=basePath%>icons/login.png');
	background-repeat:no-repeat;
	background-position:center;
}
.mini-button{
	background-image:url('<%=basePath%>icons/button.png');
	background-repeat:no-repeat;
	border:none;
}

</style>
</head>
<body>

<div id="form1" align="center" class="mydiv" style="width:100%;height:700px;">
    <table style="padding-top: 380px;">
        <tr>
            <td><label for="username$text">帐号：</label></td>
            <td>
                <input name="user" class="mini-textbox" required="true" requiredErrorText="帐号不能为空" placeholder=""/>
            </td>    
        </tr>
        <tr>
            <td><label for="pwd$text">密码：</label></td>
            <td>
                <input name="pwd" class="mini-password" vtype="minLength:6" minLengthErrorText="密码不能少于6个字符" />

            </td>
        </tr>    
        <tr></tr>
        <tr></tr>        
        <tr></tr>
        <tr></tr>
        <tr></tr>
        <tr>
            <td></td>
            <td>
            	<a class="mini-button" style="margin:0px 10px;" onclick="submitForm()">登陆</a>
            </td>
        </tr>
    </table>
</div>

    
    <script type="text/javascript">
        
        function submitForm() {
            var form = new mini.Form("#form1");

            form.validate();
            if (form.isValid() == false) return;

            //提交数据
            var data = form.getData();      
            var json = mini.encode(data);  
            $.ajax({
                url: "<%=basePath%>api/stock/web/skip/mainPage",
                type: "post",
                data: { submitData: json },
                success: function (text) {
                	if('1'==text){
						window.location.href='<%=basePath%>pages/main.jsp';
                	}else{
                		mini.alert('用户名或密码错误，请重新输入！');
                	}
                },
                error : function() {
					mini.alert("回话已经过期，请重新登陆");
					return false;
				}
                
            });
        }

    </script>
</body>
</html>
