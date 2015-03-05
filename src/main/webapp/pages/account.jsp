<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>多账户管理</title>
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
<div class="Place_text"><span>多账户设置</span></div>  
 <div  class="mini-splitter " vertical="true" style="width:100%;height:100%;">
    <table align="center">
        <tr>
            <td >
                <div id="grid1" class="mini-datagrid" style="width:180px;height:200px;"
                    idField="id" multiSelect="true" 
                    url="<%=basePath%>api/stock/web/getFundAccount?status=1" resultAsData="true" showFooter="false">
                    <div property="columns">
                        <div type="checkcolumn"></div>
                        <div header="可用账户" field="text"></div>
                    </div>
                </div>
            </td>
            <td style="width:60px;text-align:center;">
                <input type="button" value=">" onclick="add()" style="width:40px;"/><br />
  <!--               <input type="button" value=">>" onclick="addAll()" style="width:40px;"/><br />
                <input type="button" value="&lt;&lt;" onclick="removeAll()" style="width:40px;"/><br /> -->
                <input type="button" value="<" onclick="removes()" style="width:40px;"/><br />

            </td>
            <td>
                <div id="grid2" class="mini-datagrid" style="width:250px;height:200px;"                     
                    idField="id"  multiSelect="true" showFooter="false"
                    allowCellSelect="true" resultAsData="true" url="<%=basePath%>api/stock/web/getFundAccount?status=0"
                >
                    <div property="columns">
                        <div type="checkcolumn"></div>
                        <div header="账号" field="id" width="80"></div>
                        <div header="不可用账户" field="text">
                            <input property="editor" class="mini-textbox" style="width:100%"/>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
        <tr></tr>
        <tr >
	        <td align="right" colspan="3">
	        	<a class="mini-button" iconCls="icon-add" onclick="saveData()">保存</a>
	        </td>
        </tr>
    </table>    
     
        <div >
            <div id="datagrid1" class="mini-datagrid" style="width:98%;height:90%;  padding-left:5px; margin-top:10px;" allowalternating="false"
                 allowresize="false" multiselect="true"  sizeList="[20,30,50,100]" pageSize="10" allowCellEdit="true" allowCellSelect="true"
                 url="<%=basePath%>api/stock/web/caculatePercent">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center" width="5%">序号</div>
                    <div width="10%" field="assetName" headerAlign="center" align="center">
                       资产单元名称
                    </div>
                    <div width="10%" field="total" headerAlign="center" align="center">
                       总资产
                    </div>
                    <div width="10%" field="leaveFund" headerAlign="center" align="center">
                       剩余资产
                    </div>
                    <div field="leaveFundPercent" width="10%"  headerAlign="center" align="center" >
                        剩余资产比例
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
        mini.parse();
        var grid = mini.get("datagrid1");
        grid.setShowEmptyText(true);
    	grid.setEmptyText("查询结果为空!");
        $(function(){
       	    grid = mini.get("datagrid1");
            grid.load();
       	});
        
        var grid1 = mini.get("grid1");
        var grid2 = mini.get("grid2");
        grid1.load();
        grid2.load();
        
        function add() {
            var items = grid1.getSelecteds();
            if(items==''){
            	mini.alert('请先选中至少一条记录');
            	return;
            }
            grid1.removeRows(items);
            grid2.addRows(items);
        }
        function addAll() {        
        	var items = grid1.getSelecteds();
        	if(items==''){
            	mini.alert('请先选中至少一条记录');
            	return;
            }
            var items = grid1.getData();
            grid1.removeRows(items);
            grid2.addRows(items);
        }
        function removes() {
            var items = grid2.getSelecteds();
            if(items==''){
            	mini.alert('请先选中至少一条记录');
            	return;
            }
            grid2.removeRows(items);
            grid1.addRows(items);
        }
        function removeAll() {
        	var items = grid2.getSelecteds();
        	if(items==''){
            	mini.alert('请先选中至少一条记录');
            	return;
            }
            var items = grid2.getData();
            grid2.removeRows(items);
            grid1.addRows(items);
        }
        function upItem() {
            var items = grid2.getSelecteds();
            for (var i = 0, l = items.length; i < l; i++) {
                var item = items[i];
                var index = grid2.indexOf(item);
                grid2.moveRow(item, index-1);
            }
        }
        function downItem() {
            
            var items = grid2.getSelecteds();
            for (var i = 0, l = items.length; i < l; i++) {
                var item = items[i];
                var index = grid2.indexOf(item);
                grid2.moveRow(item, index + 2);
            }
        }
        function saveData() {
            var data = grid2.getData();
            var json = mini.encode(data);
            $.ajax({
                url: "<%=basePath%>api/stock/web/saveFundAccount",
                data: { data: json },
                cache: false,
                success: function (text) {
                	if(text=='1'){
                		alert('设置成功');
                	}else{
                		mini.alert('保存数据发生异常，请联系管理员');
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
