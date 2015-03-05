<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>多账户查询</title>
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
        <div style="width:1100px;">
	        <div class="mini-toolbar" style="border-bottom:0;padding:2px;">
	            <table style="width:100%;">
	                <tr>
	                    <td style="width:100%;">
	                        <a class="mini-button" iconCls="icon-add" onclick="setFundAccount('1')">设置可用</a>&nbsp;&nbsp;
	                        <a class="mini-button" iconCls="icon-add" onclick="setFundAccount('0')">设置不可用</a>
	                    </td>
	                    <td style="white-space:nowrap;">
	                        <input id="key" class="mini-textbox" emptyText="请输入昵称" style="width:150px;" onenter="onKeyEnter"/>   
	                        <a class="mini-button" onclick="search()">查询</a>
	                    </td>
	                </tr>
	            </table>           
	        </div>
    	</div>
        <div >
            <div id="datagrid1" class="mini-datagrid" style="width:1100px;height:400px;" allowResize="true"
        			url="<%=basePath%>api/stock/web/caculatePercent"  idField="id" multiSelect="false">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center" width="5%">序号</div>
                    <div type="checkcolumn" >#</div>
                    <div width="25%" field="assetName"  headerAlign="center" align="center">
                        资产单元名称
                    </div>
                    <div field="total" width="10%"  headerAlign="center" align="center" >
                        总资产
                    </div>
                    <div  field="leaveFund" headerAlign="center" align="center">
                        剩余资产
                    </div>
                    <div  field="leaveFundPercent"  headerAlign="center" align="center">
                        剩余资产比例
                    </div>         
                    <div field="status"   headerAlign="center" align="center" renderer="onHdRender">
                        状态
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
	    	var json = {'type':param,'id':row.managerAccountId};
	    	if(row.status=='0'&& param!='1'){
	    		mini.alert('已经停止使用');
	    		return;
	    	}
	    	if(row.status=='1'&&param=='1'){
	    		mini.alert('已经是正常状态，无需设置');
	    		return;
	    	}
	        $.ajax({
	            url: "<%=basePath%>api/stock/web/saveFundAccount",
	            data: {data:mini.encode(json)},
	            cache: false,
	            success: function (text) {
	            	if(text=='1'){
	            		alert('设置成功');
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
    	if(re.status=='1'){
    		return '<font color=green>正常</font>';
    	}else{
    		return '<font color=red>已禁止</font>';
    	}
    }
    
	function search() {
        var assetName = mini.get("key").getValue();
        grid.load({ assetName: assetName });
    }
	
    function onKeyEnter(e) {
        search();
    }
    
</script>
</body>
</html>
