<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>增加新新竞猜</title>
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
<form id="form1" method="post">
	<div class="mini-fit" id="datagrid">
		<div align="center" style="padding-top: 30px">
			<table style="width:80%;">
                <tr>
                    <td class="mini-item_18">
                                              竞猜名称：
                    </td>
                    <td class="mini-content_32">
                     <input id="guessName" class="mini-textbox" style="width: 60%;" required="true" vtype="maxLength:30" maxLengthErrorText="密码不能大于30个字符"/>  
                    </td>
                </tr>
				<tr>
					<td class="mini-item_18">
                                             押注个数：
                    </td>
                    <td class="mini-content_32">
                     <input id="purchaseNum" class="mini-textbox" style="width: 60%;" required="true" emptyText="20" vtype="int;maxLength:3" maxLengthErrorText="不能大于3位数"/>  
                    </td>
				</tr>
				<tr>
					<td class="mini-item_18">
                                            抽成比例：
                    </td>
                    <td class="mini-content_32">
                     <input id="pumpedPercent" class="mini-textbox" style="width: 60%;" required="true"  emptyText="0.1" vtype="float;maxLength:6" decimalPlaces="2" />  
                    </td>
				</tr>
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
</form>
<script type="text/javascript">
    mini.parse();
    var gridUrl = "api/stock/web/guessProduct/save";
    var form = new mini.Form("form1");
    function handle(type) {
		if (type == 'sure') {
            form.validate();
            if (form.isValid() == false) return;
            
            var data = form.getData();
            var json = mini.encode(data);
			
			$.ajax({
                url: gridUrl,
                type: "POST",
                data: {
                    "data": json,
                },
                success: function(data) {
                	if(data=='1'){
						/* close(); */
	               		mini.alert("新增成功！","",function(action){
	                		if (action == "ok") {
	                			CloseWindow('save');
	                		}else{
	                			mini.alert('更新失败');
	                		}
	                	});
                	}else{
                		mini.alert('新增失败，请联系管理员');
                	}
                }
            });
		}else{
			close();
		}
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
