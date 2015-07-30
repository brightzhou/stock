<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>产品设置</title>
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
<div class="Place_text"><span>产品设置</span></div>
        <div style="width:1100px;">
	        <div class="mini-toolbar" style="border-bottom:0;padding:2px;">
	            <table style="width:100%;">
	                <tr>
	                    <td style="width:100%;">
	                        <a class="mini-button" iconCls="icon-add" onclick="setFundAccount('N')">关闭</a>&nbsp;&nbsp;
	                        <a class="mini-button" iconCls="icon-add" onclick="setFundAccount('Y')">启用</a>
	                    </td>
	                    <td style="white-space:nowrap;">
	                        <input id="key" class="mini-textbox" emptyText="请输入代码" style="width:150px;" onenter="onKeyEnter"/>   
	                        <a class="mini-button" onclick="search()">查询</a>
	                    </td>
	                </tr>
	            </table>           
	        </div>
    	</div>
        <div >
            <div id="datagrid1" class="mini-datagrid" style="width:1100px;height:400px;" allowResize="true"
        			url="api/stock/web/caculatePercent"  idField="id" multiSelect="false">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center" width="5%">序号</div>
                    <div type="checkcolumn" >#</div>
                    <div width="7%" field="productCode"  headerAlign="center" align="center">
                        产品代码
                    </div>
                    <div field="productName" width="10%"  headerAlign="center" align="center" >
                        产品名称
                    </div>
                    <div field="status"   headerAlign="center" align="center" renderer="onHdRender">
                        是否启用
                    </div>                   
                </div>
            </div>
        </div>
<script type="text/javascript">
    mini.parse();
    
    $(function(){
	    grid = mini.get("datagrid1");
        grid.load();
	});
    
    function setFundAccount(param) {
    	var row = grid.getSelected();
    	if (row) {
	    	var json = {'status':param,'code':row.productCode};
	    	if(row.status=='N'&& param=='N'){
	    		mini.alert('已经禁止');
	    		return;
	    	}
	    	if(row.status=='Y'&&param=='Y'){
	    		mini.alert('已经是启用');
	    		return;
	    	}
	        $.ajax({
	            url: "api/stock/web/productStatus/set",
	            data: {data:mini.encode(json)},
	            cache: false,
	            success: function (text) {
	            	if(text=='1'){
	            		grid.reload();
	            	}else{
	            		mini.alert('保存数据发生异常，请联系管理员');
	            	}
	            },
	            error: function (text) {
	            	mini.alert('服务器发生异常');
	            }
	        });
        } else {
            mini.alert("请选中一条记录");
        }
    	
    }
	
    function onHdRender(e){
       	var re = e.record;
    	if(re.status=='Y'){
    		return '<font color=green>正常</font>';
    	}else{
    		return '<font color=red>已禁止</font>';
    	}
    }
    
    function handle(e){
    	var re = e.record;
    	if(re.useCount){
    		return re.useCount;
    	}else{
    		return 0;
    	}
    }
    
	function search() {
        var productCode = mini.get("key").getValue();
        grid.load({ productCode: productCode });
    }
	
    function onKeyEnter(e) {
        search();
    }
    
</script>
</body>
</html>
