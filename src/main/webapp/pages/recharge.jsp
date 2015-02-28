<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>增加总资产</title>
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
<div class="Place_text"><span>查询总资产列表</span></div>
<div class="mini-fit">
    <div  class="mini-splitter " vertical="true" style="width:100%;height:100%;">
        <div size="150px" showCollapseButton="true" id="editForm1">
            <table width="100%" border="0" cellpadding="0" style="padding-left:5px;" cellspacing="0" align="center" class="mini-search">
                <tr>
                    <td class="mini-item_18">
                                              请选择资金账户：
                    </td>
                    <td class="mini-content_32">
                    <input id="type" class="mini-combobox" style="width:150px;" textField="text" valueField="id" 
    					url="<%=basePath%>api/stock/web/manager/getFundAccount" />  
                    </td>
                </tr>
                <tr>
                	<td/>
                    <td style="padding-top: 20px;">
                        <a class="mini-button" style="margin:0px 10px;" onclick="search();">查 询</a>
                        <a class="mini-button" style="margin:0px 10px;" onclick="add()">增加总资产</a>
                    </td>
                </tr>
            </table>
        </div>
        <div >
            <div id="datagrid1" class="mini-datagrid" style="width:98%;height:90%;  padding-left:15px; margin-top:10px;" allowalternating="false"
                 allowresize="false" multiselect="true"  sizeList="[20,30,50,100]" pageSize="10" allowCellEdit="true" allowCellSelect="true">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center" width="5%">序号</div>
                    <div width="10%" field="fundAccountName" headerAlign="center" align="center" >
                        资金账号名称
                    </div>
                    <div width="10%" field="totalFund" headerAlign="center" align="center" renderer="addColor">
                        总资产(元)
                    </div>
                    <div field="modifyFund" width="10%"  headerAlign="center" align="center" >
                        金额变更情况(元)
                    </div>
                    <div width="10%" field="storeTime" headerAlign="center" align="center" renderer="onBirthdayRenderer">
                        存入时间
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    mini.parse();
    var gridUrl = "<%=basePath%>api/stock/web/manager/totalFund/get";
    var grid = mini.get("datagrid1");
    if(''==mini.get("type").getValue()){
	    mini.get("type").setValue("63380001");
    }
    search();
    grid.setShowEmptyText(true);
	grid.setEmptyText("查询结果为空!");
    function search(){
        grid.setUrl(gridUrl);
        grid.load({
          "type": mini.get("type").getValue(),
        });
    }
    
    
    function addColor(e) {
        var re = e.record;
        if (re.current=='Y') {
        	return '<span ><font color=red>'+re.totalFund+'</font></span>';
        }else{
        	return re.totalFund;
        }
    }
    
    function onBirthdayRenderer(e) {
        var value = e.value;
        if (value) return mini.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
        return "";
    }
    function add(){
    	var url = "<%=basePath%>pages/recharge_sub.jsp";
		mini.open({
			url : url,
			title : "新增资产",
			width : 400,
			height : 350,
			onload : function() {
				var iframe = this.getIFrameEl();
				var data = { value: mini.get("type").getValue(),text:mini.get("type").getText()};
				iframe.contentWindow.SetData(data); 
			},
			currentWindow:true,
			ondestroy : function(action) {
				if('cancel'!=action){
					grid.load({
				          "type": mini.get("type").getValue(),
				    });
				}
			}
		});
    }
</script>
</body>
</html>
