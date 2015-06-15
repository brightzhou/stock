<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查询</title>
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
<div class="Place_text"><span>查询</span></div>
        <div >
            <div id="datagrid1" class="mini-datagrid" style="width:1300px;height:600px;" allowResize="true"
        			url="api/stock/web/flbQuery"  idField="id" multiSelect="false">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center" width="5%">序号</div>
                    <div width="25%" field="trueName"  headerAlign="center" align="center">
                        用户名称
                    </div>
                    <div field="certification" width="25%"  headerAlign="center" align="center" >
                       身份证
                    </div>
                    <div  field="fundLimit"  headerAlign="center" align="center" width="25%">
                        操盘额度
                    </div>   
                    <div  field="operateTime" headerAlign="center" align="center" width="25%" renderer="onBirthdayRenderer">
                        操盘时间
                    </div>
                </div>
            </div>
        </div>
<script type="text/javascript">
    mini.parse();
    
    $(function(){
	    grid = mini.get("datagrid1");
	    grid.setShowEmptyText(true);
		grid.setEmptyText("查询结果为空!");
        grid.load();
	});
    
    function onBirthdayRenderer(e) {
        var value = e.value;
        if (value) return mini.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
        return "";
    }
    
</script>
</body>
</html>
