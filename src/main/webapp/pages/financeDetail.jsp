<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String productCode = request.getParameter("productCode");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>产品购买明细</title>
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
<div class="Place_text" ><span>用户购买明细</span>
</div><div><a class="mini-button" style="margin:0px 10px;" onclick="returns">返回</a></div>
        <div >
            <div id="datagrid1" class="mini-datagrid" style="width:100%;height:400px;" allowResize="true"
        			url="api/stock/web/financeDetail/get"  idField="id" multiSelect="false">
                <div property="columns">
                    <!-- <div type="indexcolumn" headerAlign="center" width="5%">序号</div> -->
                    <div field="nickname" renderer="onHdRender" headerAlign="center" align="center" width="7%">
                        用户昵称
                    </div>
                    <div  field="ticket"  headerAlign="center" align="center" width="7%">
                        单号
                    </div>
                    <div  field="productCode"  headerAlign="center" align="center" width="7%">
                        产品代码
                    </div>
                    <div field="financeProduct" width="5%"  headerAlign="center" align="center" >
                        产品名称
                    </div>
                    <div  field="income" headerAlign="center" align="center" width="5%">
                        总收益
                    </div>
                    <div  field="currentIncome"  headerAlign="center" align="center" width="5%">
                        每日收益
                    </div>         
                    <div field="carryDate"   headerAlign="center" align="center" width="5%">
                        起息日
                    </div>                   
                    <div  field="settleDate" width="5%"  headerAlign="center" align="center" width="10%">
                        结息日
                    </div>
                    <div field="buyDate" width="8%"  headerAlign="center" align="center" renderer="onBirthdayRenderer">
                        购买日期
                    </div>
                </div>
            </div>
        </div>
<script type="text/javascript">
    mini.parse();
    
    $(function(){
	    grid = mini.get("datagrid1");
        grid.load({productCode:'<%=productCode%>'});
	});
    
    function onBirthdayRenderer(e) {
        var value = e.value;
        if (value) return mini.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
        return "";
    }
    
    function returns(){
    	history.go(-1);
    }
</script>
</body>
</html>
