<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>增加总资产</title>
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
	<div class="mini-fit" id="datagrid">
		<div align="center" style="padding-top: 30px">
			<table style="width:80%;">
			    <tr>
                    <td class="mini-item_18">
                                              请选择资金账户：
                    </td>
                    <td class="mini-content_32">
                    	<input id="text" class="mini-textbox" style="width: 60%;" value="" enabled="false"/>
                    	<input id="type" class="mini-textbox" style="width: 60%;" value=""  visible = "false"/>  
                    <%-- <input id="type" class="mini-combobox" style="width:150px;" textField="text" valueField="id" 
    					url="<%=basePath%>api/stock/web/manager/getFundAccount" />  --%> 
                    </td>
				</tr>
				<tr>
					<td class="mini-item_18">
						<font>请输入金额:</font>
					</td>
					<td class="mini-content_32">
                 	  <input id="fund" class="mini-textbox" style="width: 60%;" emptyText="单位(元)"/> 
                    </td>
					
				</tr>
				<tr></tr>
				<tr></tr>
				<tr></tr>
				<tr>
					<td style="text-align: center;" colspan="2">
						<a class="mini-button font13 blue"
							style="margin: 5px 10px 0px 0; padding: 1px 10px;"
							onclick="handle('sure')">确定</a> 
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a class="mini-button font13 blue"
							style="margin: 5px 10px 0px 0; padding: 1px 10px;"
							onclick="handle('cancel')">取消</a>
					</td>
					
				</tr>
			</table>
		</div>
	</div>
<script type="text/javascript">
    mini.parse();
    var gridUrl = "<%=basePath%>/api/stock/web/manager/totalFund/add";
    function handle(type) {
		if (type == 'sure') {
			$.ajax({
                url: gridUrl,
                type: "POST",
                data: {
                    "fund": $.trim(mini.get("fund").getValue()),
                    "type": $.trim(mini.get("type").getValue())
                },
                success: function(data) {
                	if(data=='1'){
						/* close(); */
                		mini.alert("充值成功！","",function(action){
		                		if (action == "ok") {
		                			CloseWindow('save');
		                		}
	                	});
                	}else{
                		mini.alert('充值失败，请联系管理员');
                	}
                }
            });
		}else{
			close();
		}
	}
	
    
    function SetData(data) {
       data = mini.clone(data);
       mini.get("text").setValue(data.text);
       mini.get("type").setValue(data.value);
    }
    
	function CloseWindow(action) {
      	if (window.CloseOwnerWindow){
      		return window.CloseOwnerWindow(action);
      	}else{
      		window.close();
      	}
    }

	function close() {
    	CloseWindow("cancel");
    }
    
</script>
</body>
</html>
