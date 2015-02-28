<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Calendar rightNow = Calendar.getInstance();
String yearMonth = rightNow.get(Calendar.YEAR)+"-"+rightNow.get(Calendar.MONTH)+1;

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>设置服务费生效日期</title>
	<base href="<%=basePath%>"/>
	<script src="scripts/base/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="scripts/sui/mini-all-min.js" type="text/javascript"></script>
	<link href="scripts/sui/themes/default/miniui.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/default/plugin.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/icons.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/sqt/skin.css" rel="stylesheet" type="text/css" />
	<link href="styles/style_sqt.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.mini-checkboxlist-td label{
	font-family: microsoft yahei;
}
</style>
</head>

<body class="body_fit" >
<div class="Place_text"><span>日期列表</span></div>
	<div style="font-family: microsoft yahei" align="center">
		请选择月份：<input id="date1" class="mini-monthpicker" value="<%=yearMonth%>" format="yyyy-MM" onvaluechanged="cal()"/>
	</div>
	
	
	<div align="center" style="font-family: microsoft yahei" >
		<h3 id="month"></h3>
		<div id="cbl1" class="mini-checkboxlist" repeatItems="7" repeatLayout="table"
	        textField="text" valueField="id" value="" 
	        data='' >
	    </div>
	</div>
	
	<div align="center" style="padding-top: 40px;">
		<a class="mini-button" iconCls="icon-edit" id="selectAll" onclick="selectAll">全选</a>
		<a class="mini-button" iconCls="icon-edit" id="reverse" onclick="deselectAll">全不选</a>
		<a class="mini-button" iconCls="icon-edit" onclick="setFeeDays">设置</a>
	</div> 
    
	<script type="text/javascript">
	    mini.parse();
	    cal();
	    function setMonthValue() {
	        var t = mini.get("date1");
	        t.setValue(new Date());
	    }
	    
	    function setFeeDays() {
	    	var cbl1 = mini.get("cbl1");
	    	var t = mini.get("date1");
	    	var yearMonth = t.getFormValue();
	    	var days = cbl1.getValue();
	    	if(days==null||days==''){
	    		mini.alert("请先选择至少一个日期");
	    		return false;
	    	}
	    	mini.confirm("确定要设置当前日历", "确定？",
		            function (action) {            
		                if(action == "ok"){
		                	 $.ajax({
			     	                url: "<%=basePath%>api/stock/web/setFeeCalendar",
			     	                data: {yearMonth : yearMonth, days:days},
			     	                type: "post",
			     	                success: function (msg) {
			     	                    if(msg == "1"){
			     	                    	 mini.alert("设置成功！");
			     	                    }else{
			     	                    	 mini.alert("设置失败！");
			     	                    }
			     	                },
			     	                error: function(msg){
			     	                	mini.alert("服务器异常，提现失败！");
			     	                	return false;
			     	                }			
			     				});
		                } 
		            }
		  ); 
	    	
	    }
	
	    
	    function cal(){
	    	var t = mini.get("date1");
	    	var month = t.getFormValue();
	    	$.ajax({
	                url: "<%=basePath%>api/stock/web/setDays",
	                data: {month:month},
	                type: "post",
	                success: function (json) {
	                    if(json != ''){
	                    	var c = mini.get("cbl1");
	                    	var obj = eval( "(" + json + ")" );
	                    	c.setData(obj.calendar);
	                    	c.setValue(obj.feeDay);
	                    }
	                },
	                error: function(msg){
	                	mini.alert("服务器异常，提现失败！");
	                	return false;
	                }			
				});
	    }
	    
	    function selectAll(){
	    	 var cbl1 = mini.get("cbl1");
	    	 cbl1.selectAll();
	    }
	    
	    
	    function deselectAll(){
	    	 var cbl1 = mini.get("cbl1");
	    	 cbl1.deselectAll();
	    }

	</script>
</body>
</html>
