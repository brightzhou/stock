<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String refereeId = request.getParameter("refereeId");
String apkPath = request.getParameter("apkPath");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户列表</title>
	<base href="<%=basePath%>"/>
	<script src="scripts/base/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="scripts/sui/mini-all-min.js" type="text/javascript"></script>
	<link href="scripts/sui/themes/default/miniui.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/default/plugin.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/icons.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/sqt/skin.css" rel="stylesheet" type="text/css" />
	<link href="styles/style_sqt.css" rel="stylesheet" type="text/css" />
    
    <style type="text/css">
    .errorText
    {
        color:red;
    }
    .main{
    	font-size: 70px;
    }
    input {
		width: 300px;
		height: 270px;
		size: 100px;
	}
	
	.mini-textbox-border{
		height: 70px;
		width: 450px;
		border: 40px;
		border-color: #323232;
		border-style: solid;
		border-width: 7px;
		box-shadow: 1px 2px 1px rgba(0, 0, 0, 0.07);
	}
	.mini-textbox{
		width: 450px;
		height: 70px;
		display: inline-block;
		padding: 0;
		margin: 0;
		border: 0;
		vertical-align: middle;
		overflow: hidden;
		position: relative;
	}
    </style>
</head>

<body class="body_fit" >
<h1 align="center" class="main">哈哈宝注册</h1>      
    <div id="form1" align="left">
        <table class="main">
            <tr>
                <td >
                    <label for="nickname$text">昵称：</label>
                </td>
                <td  >
                    <input name="nickname" errorMode="none" onvalidation="onUserNameValidation"  class="mini-textbox" required="true" requiredErrorText="帐号不能为空" width="500px"/>                    
                </td>    
                <td  id="nickname_error" class="errorText"></td>
            </tr>
            <tr/>
            <tr/>
            <tr />
            <tr>
                <td >                   
                    <label for="password$text">密码：</label>
                </td>
                <td>
                    <input name="password" errorMode="none" onvalidation="onPwdValidation" class="mini-password"  vtype="minLength:6;maxLength:20" required="true" requiredErrorText="密码不能为空" minLengthErrorText="密码不能小于6个字符" maxLengthErrorText="密码不能超过20个字符" width="500px"/>                    
                </td>
                <td id="password_error" class="errorText"></td>
            </tr> 
            <tr/>
            <tr/>
            <tr/>
            <tr>
                <td >                   
                    <label for="refereeId$text">推荐人ID：</label>
                </td>
                <td>
                    <input name="refereeId" errorMode="none" value="<%=refereeId%>" onvalidation="onRefereeIdValidation" class="mini-textbox" vtype="int"  intErrorText="推广人ID只能是数字" width="500px" />                    
                </td>
                <td id="refereeId_error" class="errorText"></td>
            </tr>
            <tr/>
            <tr/>
            <tr/>
            <tr/>
            <tr/>
            <tr/>
            <tr/>
            <tr/>
            <tr/>
            <tr/>
            <tr>
                <td>
                </td>
                <td >
                    <input value="注册" type="button" onclick="submitForm()" style="width: 400px;height: 70px;font-size: 25px;"/>
                </td>
            </tr>
        </table>
    </div>

    
    <script type="text/javascript">
        mini.parse();

        function submitForm() {           
            var form = new mini.Form("#form1");
            form.validate();

            if (form.isValid() == false) return;

            //提交数据
            var data = form.getData();
            var json = mini.encode(data);
            $.ajax({
                url: "<%=basePath%>api/stock/register",
                type: "post",
                data: data,
                dataType: "json",
                success: function (text) {
                	if(text=='1'){
                		window.location.href = '<%=basePath%>pages/registerSuccess.jsp?apkPath='+'<%=apkPath%>';
                	}else{
                		mini.alert("该用户已经注册");
                	}
                }
            });
        }

        //////////////////////////////////////////
        function updateError(e) {
            var id = e.sender.name + "_error";
            var el = document.getElementById(id);
            if (el) {
                el.innerHTML = e.errorText;
            }
        }
        function onUserNameValidation(e) {                  
            updateError(e);
        }
        function onPwdValidation(e) {        
            updateError(e);
        }
        
        function onRefereeIdValidation(e) {        
            updateError(e);
        }
    </script>
</body>
</html>
