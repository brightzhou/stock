<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>资金流水</title>
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
<div class="Place_text"><span>资金流水查询</span></div>
        <div style="width:1100px;">
	        <div class="mini-toolbar" style="border-bottom:0;padding:2px;">
	            <table style="width:100%;">
	                <tr>
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
        			url="<%=basePath%>api/stock/web/getFundFlowInfo"  idField="id" multiSelect="false">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center" width="5%">序号</div>
                    <div width="10%" field="nickname" headerAlign="center" align="center">
                        用户昵称
                    </div>
                    <div  field="trueName"  headerAlign="center" align="center">
                        真实姓名
                    </div>
                    <div  field="phone"  headerAlign="center" align="center">
                        手机
                    </div>
                    <div field="openBank" width="10%"  headerAlign="center" align="center" >
                        开户行
                    </div>
                    <div  field="bank" headerAlign="center" align="center">
                        充值行
                    </div>
                    <div  field="cardNo"  headerAlign="center" align="center">
                        卡号
                    </div>      
                    <div  field="fundStr"  headerAlign="center" align="center">
                        金额
                    </div>  
                    <div  field="bussniessType"  headerAlign="center" align="center">
                        交易类型
                    </div>
                    <div  field="transactionDate"  headerAlign="center" align="center" renderer="onBirthdayRenderer">
                        交易时间
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
    
	
	function search() {
        var nickname = mini.get("key").getValue();
        grid.load({ nickname: nickname });
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
