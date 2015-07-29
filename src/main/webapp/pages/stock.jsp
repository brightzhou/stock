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
        <div style="width:800px;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:100%;">
                        <a class="mini-button" iconCls="icon-add" onclick="addRow()" plain="true" tooltip="增加...">增加</a>
                        <a class="mini-button" iconCls="icon-remove" onclick="removeRow()" plain="true">删除</a>
                        <span class="separator"></span>
                        <a class="mini-button" iconCls="icon-save" onclick="saveData()" plain="true">保存</a>            
                    </td>
                    <td style="white-space:nowrap;">
                        <input id="stockCode" class="mini-textbox" emptyText="请输入股票代码" style="width:150px;" onenter="onKeyEnter"/>   
                        <a class="mini-button" onclick="search()">查询</a>
                    </td>
                </tr>
            </table>           
        </div>
    </div>
<form id="form1" method="post">
    <div id="datagrid1" class="mini-datagrid" style="width:800px;height:280px;" 
        url="api/stock/web/stockCode/get" idField="id" 
        allowResize="true" pageSize="20" 
        allowCellEdit="true" allowCellSelect="true" multiSelect="true" 
        editNextOnEnterKey="true"  editNextRowCell="true"
    >
        <div property="columns">
            <div type="checkcolumn"></div>
            <div name="id"  field="id" headerAlign="center" allowSort="true" width="30" style="display: none">序号
                <input class="mini-textbox" style="width:10%;" minWidth="200" />
            </div>
            <div name="stockCode"  field="stockCode" headerAlign="center"  width="150" required="true" vtype="int;rangeLength:1,6;">股票代码
                <input property="editor" class="mini-textbox" style="width:100%;" minWidth="200" />
            </div>
        </div>
    </div>
    </form>
    <script type="text/javascript">
     
        mini.parse();
        var grid = mini.get("datagrid1");
        grid.load();
        grid.setShowEmptyText(true);
    	grid.setEmptyText("查询结果为空!");
        function search() {
            var stockCode = mini.get("stockCode").getValue();
            grid.load({ stockCode:stockCode });
        }

        function onKeyEnter(e) {
            search();
        }

        function addRow() {          
            var newRow = { name: "New Row" };
            grid.addRow(newRow, 0);
            grid.beginEditCell(newRow, "stockCode");
        }
        function removeRow() {
            var rows = grid.getSelecteds();
            /* var json = mini.encode(rows); */
            if (rows.length > 0) {
            	var json = mini.encode(rows);
                grid.removeRows(rows, true);
               /*  
                $.ajax({
                    url: "api/stock/entrust/stockCode/del",
                    data: { data: json },
                    type: "post",
                    success: function (text) {
                    	mini.alert("删除成功");
                        grid.reload();
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert(jqXHR.responseText);
                    }
                }); */
            }else{
            	mini.alert('请选择至少一条记录');
            }
        }
        function saveData() {
        	
        	 grid.validate();
             if (grid.isValid() == false) {
             	 mini.alert('输入代码不合法，必须是数字，长度在1-6位之间');
                 var error = grid.getCellErrors()[0];
                 grid.beginEditCell(error.record, error.column);
                 return;
             }
            
            var data = grid.getChanges();
            
            if(data.length <= 0){
            	mini.alert('没有可以更新的记录');
            	return;
            }
            var json = mini.encode(data);
            
            grid.loading("保存中，请稍后......");
            $.ajax({
                url: "api/stock/web/stockCode/save",
                data: { data: json },
                type: "post",
                success: function (text) {
                	grid.reload();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });
        }

        
    </script>
</body>
</html>
