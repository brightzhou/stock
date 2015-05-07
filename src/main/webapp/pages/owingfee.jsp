<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>手续费欠款列表</title>
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
<div class="Place_text"><span>续费欠款列表</span></div>
        <div style="width:1100px;">
	        <div class="mini-toolbar" style="border-bottom:0;padding:2px;">
	            <table style="width:100%;">
	                <tr>
	                    <td style="white-space:nowrap;">
	                        <input id="key" class="mini-textbox" emptyText="请输入昵称" style="width:150px;" onenter="onKeyEnter"/>
	                        <input name="loss" id="loss" class="mini-combobox" valueField="id" textField="text" 
	                            data="[{'id': '1', 'text': '余额小于0'},{'id': '2', 'text': '亏损'}]"/>   
	                        <a class="mini-button" onclick="search()">查询</a>
	                    </td>
	                </tr>
	            </table>           
	        </div>
    	</div>
        <div >
            <div id="datagrid1" class="mini-datagrid" style="width:1100px;height:400px;" allowResize="true"
        			url="api/stock/web/getOwingFee"  idField="id" multiSelect="false">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center" width="5%">序号</div>
                    <div width="10%" field="nickname" renderer="onHdRender" headerAlign="center" align="center">
                        用户昵称
                    </div>
                    <div field="operationAccount"   headerAlign="center" align="center" renderer="feeRender">
                        操盘账户
                    </div>                   
                    <div field="phone" width="10%"  headerAlign="center" align="center" >
                        手机
                    </div>
                    <div  field="oweFee" headerAlign="center" align="center"  dataType="float" decimalPlaces="2">
                        欠费情况
                    </div>
                    <div  field="profitAndLoss" headerAlign="center" align="center"  dataType="float" decimalPlaces="2">
                        支付亏损
                    </div>
                </div>
            </div>
        </div>
<script type="text/javascript">
    mini.parse();
    
    $(function(){
	    grid = mini.get("datagrid1");
	    mini.get("loss").setValue('1');
        grid.load({
        	loss : mini.get("loss").getValue()
        });
	});
    
	function feeRender(e) {
		var value = e.value;
        if (value==null) return '<span ><font color=red>操盘已结束</font></span>';
        return value;
    };
	
	function search() {
        var nickname = mini.get("key").getValue();
        var loss = mini.get("loss").getValue();
        grid.load({ nickname: nickname,loss:loss });
    }
	
    function onKeyEnter(e) {
        search();
    }
    
</script>
</body>
</html>
