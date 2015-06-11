<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>群发短信</title>
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
	 <input  type="hidden"   name="id" id="id" >
	<div class="mini-fit" id="datagrid">
		<div align="center" style="padding-top: 30px">
			<table style="width:80%;">
			    <tr>
                    <td class="mini-item_18">
                                                         字典类型：
                    </td>
                    <td class="mini-content_32">
                        <select name="dicType" id="dicType" class="mini-select">
                            <option value="1">其它</option>
                            <option value="2">炒盘参数</option>
                        </select>
                    </td>
				</tr>
				<tr>
                    <td class="mini-item_18">
                                                                           字典Key：
                    </td>
                    <td class="mini-content_32">
                       <input type="text" name="dicWord" id="dicWord" class="mini-text">
                    </td>
				</tr>
				<tr>
                    <td class="mini-item_18">
                                                                           字典值：
                    </td>
                    <td class="mini-content_32">
                       <input type="text" name="dicValue" id="dicValue" class="mini-text">
                    </td>
				</tr>
				<tr>
                    <td class="mini-item_18">
                                                                           字典名：
                    </td>
                    <td class="mini-content_32">
                       <input type="text" name="dicName" id="dicName" class="mini-text">
                    </td>
				</tr>
				<tr>
                    <td class="mini-item_18">
                                                                           序号：
                    </td>
                    <td class="mini-content_32">
                       <input type="text" name="orderNo" id="orderNo" class="mini-text">
                    </td>
				</tr>
				<tr>
                    <td class="mini-item_18">
                                                                           描述：
                    </td>
                    <td class="mini-content_32">
                       <textarea rows="3" cols="50" name="des" id="des" ></textarea>
                    </td>
				</tr>
				<tr>
                    <td class="mini-item_18">
                                                                           是否缓存：
                    </td>
                    <td class="mini-content_32">
                                                                                     是<input type="radio" name="isCache" value="1"  class="mini-radio" >  &nbsp; 否 <input type="radio" name="isCache" value="0" class="mini-radio"  >
                    </td>
				</tr>
				<tr>
                    <td class="mini-item_18">
                                                                           状态：
                    </td>
                    <td class="mini-content_32">
                                                                                    可用<input type="radio" name="status" value="1" class="mini-radio"  >  &nbsp;  不可用 <input type="radio" name="status" value="0" class="mini-radio"  >
                    </td>
				</tr>
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
    var gridUrl = "<%=basePath%>/api/stock/web/dictionaries/update";
    function handle(type) {
		if (type == 'sure') {
			$.ajax({
                url: gridUrl,
                type: "POST",
                data: $('#form1').serialize(),
                success: function(data) {
                	if(data=='1'){
                		mini.alert("添加成功！","",function(action){
	                		if (action == "ok") {
	                			CloseWindow('save');
	                		}
	                	});
                	}else{
                		mini.alert('添加失败 ');
                	}
                }
            });
		}else{
			close();
		}
	}
	
    
    
    
    
    
    
    
    function SetData(data) {
           data = mini.clone(data);
           $("#id").val(data.id); 
           init(data.id);
    }
    function init(id){
    	$.ajax({
            url:"<%=basePath%>/api/stock/web/dictionaries/initInfo",
            type: "POST",
            data: {"id":id},
            dataType:"json", 
            success: function(data) {
            	$("#dicType").val(data.dicType); 
            	$("#dicWord").val(data.dicWord); 
            	$("#dicValue").val(data.dicValue); 
            	$("#dicName").val(data.dicName); 
            	$("#orderNo").val(data.orderNo); 
            	$("#des").val(data.des); 
            	if(data.isCache==1){
            		$("input[name='isCache'][value='1']").attr("checked",true);  
            	}else{
            		$("input[name='isCache'][value='0']").attr("checked",true);  
            		 
            	}
            	if(data.status==1){
            		$("input[name='status'][value='1']").attr("checked",true);  
            	}else{
            		$("input[name='status'][value='0']").attr("checked",true);  
            	 
            	}
         
            }
        });
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
