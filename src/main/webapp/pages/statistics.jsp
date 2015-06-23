<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Calendar rightNow = Calendar.getInstance();
String yearMonth = rightNow.get(Calendar.YEAR)+"-"+(rightNow.get(Calendar.MONTH)+1);
System.out.println(yearMonth);
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
<div class="Place_text"><span>财务统计</span></div>
	
	<div style="width:1100px;">
	        <div class="mini-toolbar" style="border-bottom:0;padding:2px;">
	            <table style="width:100%;">
	                <tbody><tr>
	                    <td style="white-space:nowrap;">
	                         
	                          <input id="date1" class="mini-monthpicker"   format="yyyy-MM-dd"  />  
	                      
	                        <a class="mini-button" href="javascript:search()"><span class="mini-button-text ">查询</span></a>
	                    </td>
	                </tr>
	            </tbody></table> 
	                    
	        </div>
    	</div>
    	
    	
    	 <div id="datagrid1" class="mini-datagrid" style="width:1100px;height:400px;" allowResize="true"
        			url="<%=basePath%>api/stock/web/statistics/get"  idField="id" multiSelect="false">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center" width="5%">序号</div>
                    <div  field="transactionDay"  headerAlign="center" align="center">
                        日期
                    </div> 
                    <div width="15%" field="serviceCharge" headerAlign="center" align="center">
                        服务费
                    </div>
                    <div  field="commission"  headerAlign="center" align="center">
                       提成
                    </div>
                    <div  field="financialOptimization"  headerAlign="center" align="center">
                        财务优化
                    </div>
                    <div  field="debt"  headerAlign="center" align="center">
                       欠款
                    </div>
                    <div  field="bond"  headerAlign="center" align="center">
                       保证金总和
                    </div>
                    <div  field="profit"  headerAlign="center" align="center">
                       盈亏金额总和
                    </div>
                    <div  field="balance"  headerAlign="center" align="center">
                       钱包余额总和
                    </div>
    
                </div>
            </div>
	
	
	 
	
	<div align="center" style="font-family: microsoft yahei" >
		<h3 id="month"></h3>
		<div id="cbl1" class="mini-checkboxlist" repeatItems="7" repeatLayout="table"
	        textField="text" valueField="id" value="" 
	        data='' >
	    </div>
	</div>
	
	
    
	<script type="text/javascript">
	    mini.parse();
	    
	    $(function(){
		    grid = mini.get("datagrid1");
	        grid.load();
		});
	    
	    function search() {
	        var day = $(".mini-buttonedit-input").val();
	        grid.load({ day: day });
	    }
 
	     
	    
	 
	 
	    
    </script>
</body>
</html>
