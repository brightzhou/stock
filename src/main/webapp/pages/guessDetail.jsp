<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String bidCode = request.getParameter("bidCode");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>猜大盘明细</title>
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
        			url="api/stock/web/guessDetail/query"  idField="id" multiSelect="false">
                <div property="columns">
                    <!-- <div type="indexcolumn" headerAlign="center" width="5%">序号</div> -->
                    <div field="nickname" renderer="onHdRender" headerAlign="center" align="center" width="7%">
                        用户昵称
                    </div>
                    <div  field="code"  headerAlign="center" align="center" width="7%">
                        产品代码
                    </div>
                    <div field="name" width="5%"  headerAlign="center" align="center" >
                        产品名称
                    </div>
                    <div field="purchaseNum" width="5%"  headerAlign="center" align="center" >
                        投入哈哈币
                    </div>
                    <div  field="actualStatus" headerAlign="center" align="center" width="5%">
                        实际涨跌
                    </div>
                    <div  field="guessStatus"  headerAlign="center" align="center" width="5%">
                        猜测
                    </div>         
                    <div field="profitNum"   headerAlign="center" align="center" width="5%">
                        收入
                    </div>                   
                    <div field="purchaseTime" width="8%"  headerAlign="center" align="center" renderer="onBirthdayRenderer">
                        购买日期
                    </div>
                </div>
            </div>
        </div>
<script type="text/javascript">
    mini.parse();
    
    $(function(){
	    grid = mini.get("datagrid1");
        grid.load({bidCode:'<%=bidCode%>'});
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
