<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>股票池维护</title>
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
<div class="Place_text"><span>股票池维护</span></div>
        <div style="width:1100px;">
	        <div class="mini-toolbar" style="border-bottom:0;padding:2px;">
	            <table style="width:100%;">
	                <tr>
	                    <td style="width:100%;">
	                        <a class="mini-button" iconCls="icon-remove" onclick="removeRow()" >删除</a>
	                        <a class="mini-button" iconCls="icon-save" onclick="saveData()">保存</a>
	                    </td>
	                    <td style="white-space:nowrap;">
	                        <input id="key" class="mini-textbox" emptyText="请输入昵称" style="width:150px;" onenter="onKeyEnter"/>   
	                        <a class="mini-button" onclick="search()">查询</a>
	                    </td>
	                </tr>
	            </table>           
	        </div>
    	</div>
    	<div>
            <div id="datagrid1" class="mini-datagrid" style="width:1100px;height:400px;" allowResize="true"
        			url="api/stock/web/userbank/get"  idField="id" allowCellEdit="true" allowCellSelect="true" multiSelect="false"
        			allowCellValid="true" >
                <div property="columns">
                	<div type="checkcolumn" >#</div>
                    <div field="id"  headerAlign="center" align="center" width="3%" >
       	序号
                    </div>
                    <div field="nickname"  headerAlign="center" align="center">
                        用户昵称
                    </div>
                    <div field="truename" width="10%"  headerAlign="center" align="center" >
                        真实姓名
                    </div>
                    <div  field="bank" headerAlign="center" align="center">
                        开户银行
                    </div>
                    <div  field="cardNumber"  headerAlign="center" align="center" vtype="int;rangeLength:10,25;">
                        银行卡号			<input property="editor" class="mini-textbox" style="width:100%;" />
                    </div>   
                    <div  field="phone"  headerAlign="center" align="center" >
                        联系电话
                    </div>     
            	</div>
        	</div>
        </div>
<script type="text/javascript">
    mini.parse();
    var grid;
    $(function(){
	    grid = mini.get("datagrid1");
        grid.load();
	});
    
    function saveData() {
        grid.validate();
        if (grid.isValid() == false) {
        	mini.alert('请输入整数,长度在10到25位之间');
            var error = grid.getCellErrors()[0];
            grid.beginEditCell(error.record, error.column);
            return;
        }

        var data = grid.getChanges();
        var json = mini.encode(data);

        grid.loading("保存中，请稍后......");
        $.ajax({
            url: "api/stock/web/userBank/save",
            data: {data:json},
            type: "post",
            success: function (text) {
            	if(text=='1'){
            		mini.alert('更新成功');
            		grid.reload();
            	}else{
            		mini.alert('更新失败，请联系管理员');
            	}
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.responseText);
            }
        });
    }
    
    	
    function removeRow() {
    	var rows = grid.getSelected();
        var id = rows.id;
    	mini.confirm("确定删除吗?", "确定？",
	            function (action) {            
	                if(action == "ok"){
	                	 $.ajax({
		     	                url: "api/stock/web/userBank/kill",
		     	                data: {id:id},
		     	                type: "post",
		     	                success: function (msg) {
		     	                	 if(msg=='1'){
		     	                    	 mini.alert("删除成功！","",function(action){
	     			                		if (action == "ok") {
	     			                			grid.reload();	
	     			                		}
	    			                	});
		     	                	 }else{
		     	                		 mini.alert('删除失败，请联系管理员');
		     	                	 }
		     	                },
		     	                error: function(msg){
		     	                	alert("failure:"+msg);
		     	                }			
		     				});
	                } 
	            }
	  	); 
    }
    
	function search() {
        var nickname = mini.get("key").getValue();
        grid.load({ nickname: nickname });
    }
	
    function onKeyEnter(e) {
        search();
    }
    
</script>
</body>
</html>
