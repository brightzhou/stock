<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>增加新产品</title>
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
                                              产品名称：
                    </td>
                    <td class="mini-content_32">
                     <input id="financeProduct" class="mini-textbox" style="width: 60%;" required="true" emptyText=""/>  
                    </td>
                </tr>
                <tr>
                    <td class="mini-item_18">
                                              额度：
                    </td>
                    <td class="mini-content_32">
                 	  <input id="financeTotalLimit" class="mini-textbox" style="width: 60%;" required="true" emptyText="单元（元）" vtype="int"/> 
                    </td>
                </tr>
				<tr>
					<td class="mini-item_18">
				年化收益
					</td>
					<td class="mini-content_32">
                 	  <input id="annualIncome" class="mini-textbox" style="width: 60%;" required="true" vtype="float" vtype="float;rangeDecimals:2" emptyText="0.18"/>
                    </td>
				</tr>
				<tr>
					<td class="mini-item_18">
				期限
					</td>
					<td class="mini-content_32">
                 	  <input id="expireDay" class="mini-textbox" style="width: 60%;" required="true" emptyText="单位（天）" vtype="int"/> 
                    </td>
				</tr>
				<tr>
					<td class="mini-item_18">
				起息日
					</td>
					<td class="mini-content_32">
                 	  <input id="carryDate" class="mini-datepicker" format="yyyy-MM-dd" required="true" />
                    </td>
				</tr>
				<tr>
					<td class="mini-item_18">
				最大额度
					</td>
					<td class="mini-content_32">
                 	  <input id="maxLimit" class="mini-textbox" style="width: 60%;" required="true" emptyText="单位（元）" vtype="int"/> 
                    </td>
				</tr>
				<tr>
					<td class="mini-item_18">
				最小额度
					</td>
					<td class="mini-content_32">
                 	  <input id="minLimit" class="mini-textbox" style="width: 60%;" required="true" emptyText="单位（元）" vtype="int"/> 
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
</form>
<script type="text/javascript">
    mini.parse();
    var gridUrl = "api/stock/web/product/save";
    var form = new mini.Form("form1");
    function handle(type) {
		if (type == 'sure') {
            form.validate();
            if (form.isValid() == false) return;
			var financeProduct = $.trim(mini.get("financeProduct").getValue());
			var financeTotalLimit = $.trim(mini.get("financeTotalLimit").getValue());
			var annualIncome = $.trim(mini.get("annualIncome").getValue());
			var expireDay = $.trim(mini.get("expireDay").getValue());
			var carryDate = $.trim(mini.get("carryDate").getFormValue());
			var maxLimit = $.trim(mini.get("maxLimit").getValue());
			var minLimit = $.trim(mini.get("minLimit").getValue());
			
			if(maxLimit<minLimit){
				mini.alert('最大额度不能低于最小额度');
				return;
			}
			
			$.ajax({
                url: gridUrl,
                type: "POST",
                data: {
                    "financeProduct": financeProduct,
                    "financeTotalLimit": financeTotalLimit,
                    "annualIncome": annualIncome,
                    "expireDay": expireDay,
                    "carryDate": carryDate,
                    "maxLimit": maxLimit,
                    "minLimit": minLimit,
                },
                success: function(data) {
                	if(data=='1'){
						/* close(); */
	               		mini.alert("新增成功！","",function(action){
	                		if (action == "ok") {
	                			CloseWindow('save');
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
