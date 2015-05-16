<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>操盘记录</title>
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
<div class="Place_text"><span>用户操盘查询</span></div>
        <div style="width:1100px;">
	        <div class="mini-toolbar" style="border-bottom:0;padding:2px;">
	            <table style="width:100%;">
	                <tr>
	                    <td style="white-space:nowrap;">
	                        <input id="key" class="mini-textbox" emptyText="请输入昵称/操盘账号" style="width:150px;" onenter="onKeyEnter"/>   
	                        <input name="range" id="range" class="mini-combobox" valueField="id" textField="text" 
	                            data="[{'id': '1', 'text': '当前操盘'},{'id': '0', 'text': '历史操盘'}]"
	                             
	                            />
	                        <a class="mini-button" onclick="search()">查询</a>
	                    </td>
	                </tr>
	            </table>           
	        </div>
    	</div>
        <div >
            <div id="datagrid1" class="mini-datagrid" style="width:1100px;height:400px;" allowResize="true"
        			url="api/stock/web/getOperationInfo"  idField="id" multiSelect="false">
                <div property="columns">
                    <div width="10%" field="nickname" headerAlign="center" align="center">
                        用户昵称
                    </div>
                    <div field="truename" headerAlign="center" align="center" width="10%">
                        真实姓名
                    </div>
                    <div  field="operationNo"  headerAlign="center" align="center" width="11%">
                        操盘ID
                    </div>
                    <div  field="operationAccount"  headerAlign="center" align="center" width="11%"> 
                        操盘账号
                    </div>
                    <div  field="operationFund"  headerAlign="center" align="center" width="8%">
                        操盘金额
                    </div>       
					<div  field="profitAndLoss"  headerAlign="center" align="center" width="8%">
                       盈亏金额
                    </div>
					<div  field="profitPercent"  headerAlign="center" align="center" width="8%">
                       盈亏率
                    </div>
					<div  field="guranteeFund"  headerAlign="center" align="center" width="8%">
                        保证金
                    </div>
					<div  field="managementFee"  headerAlign="center" align="center" width="8%">
                        管理费
                    </div>
                    <div field="startDate"  headerAlign="center" align="center" renderer="onBirthdayRenderer">
                        开始时间
                    </div>
                    <div  field="endDate" headerAlign="center" align="center" renderer="onBirthdayRenderer">
                        结束操盘时间
                    </div>
                </div>
            </div>
        </div>
<script type="text/javascript">
    mini.parse();
    
    $(function(){
	    grid = mini.get("datagrid1");
	    mini.get("range").setValue('1');
        grid.load({ range:mini.get("range").getValue()});
	});
    
	
	function search() {
        var nickname = mini.get("key").getValue();
        var range = mini.get("range").getValue();
        grid.load({ nickname: nickname,range:range });
    }
	
    function onKeyEnter(e) {
        search();
    }
    
    function onBirthdayRenderer(e) {
        var value = e.value;
        if (value) return mini.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
        return "";
    }
    
</script>
</body>
</html>
