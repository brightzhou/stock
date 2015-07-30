<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>猜涨跌</title>
	<base href="<%=basePath%>"/>
	<script src="scripts/base/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="scripts/sui/mini-all-min.js" type="text/javascript"></script>
	<link href="scripts/sui/themes/default/miniui.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/default/plugin.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/icons.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/sqt/skin.css" rel="stylesheet" type="text/css" />
	<link href="styles/style_sqt.css" rel="stylesheet" type="text/css" />

<style type="text/css">

.my{
	padding-left: 20px;
	color: red;
}
</style>
</head>

<body class="body_fit" >
<div class="Place_text"><span>猜涨跌发标</span></div>
<div class="mini-fit">
    <div  class="mini-splitter " vertical="true" style="width:100%;height:100%;">
        <div size="150px" showCollapseButton="true" id="editForm1">
            <table width="100%" border="0" cellpadding="0" style="padding-left:5px;" cellspacing="0" align="center" class="mini-search">
                <tr>
                    <td class="mini-item_18">
                                              发标日期：
                    </td>
                    <td class="mini-content_32">
                    	<input id="date1" class="mini-datepicker" format="yyyy-MM-dd" />
                    </td>
                </tr>
                <tr>
                	<td/>
                    <td style="padding-top: 20px;">
                        <a class="mini-button" style="margin:0px 10px;" onclick="search();">查 询</a>
                        <a class="mini-button" style="margin:0px 10px;" onclick="add()">发标</a>
                    </td>
                </tr>
            </table>
        </div>
        <div >
            <div id="datagrid1" class="mini-datagrid" style="width:98%;height:100%;  padding-left:15px;" allowalternating="false"
                 allowresize="false"  sizeList="[20,30,50,100]" pageSize="10" >
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center" width="3%">序号</div>
                    <div width="7%" field="code" headerAlign="center" align="center">
                       产品代码
                    </div>
                    <div width="7%" field="name" headerAlign="center" align="center">
                        产品名称
                    </div>
                    <div width="6%" field="riseNum" headerAlign="center" align="center" renderer="queryDetail('rise')">
                        参与人数(涨)
                    </div>
                    <div width="6%" field="failNum" headerAlign="center" align="center" renderer="queryDetail('fail')">
                        参与人数(跌)
                    </div>
                    <div width="7%" field="publishTime" headerAlign="center" align="center" renderer="onBirthdayRenderer">
                        发布日期
                    </div>
                    <div width="10%" field="status" headerAlign="center" align="center" renderer="changeStatus" allowSort="true">
                        状态
                    </div>
                    <div field="guessResult" width="10%"  headerAlign="center" align="center" renderer="setResult">
                        竞猜结果
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    mini.parse();
    var gridUrl = "api/stock/web/guessProduct/query";
    var grid = mini.get("datagrid1");
    search();
    grid.setShowEmptyText(true);
	grid.setEmptyText("查询结果为空!");
    function search(){
        grid.setUrl(gridUrl);
        grid.load({
          "date": mini.get("date1").getFormValue()
        });
    }
    
    function changeStatus(e) {
        var re = e.record;
        if (re.status=='Y') {
        	return '<span><font color=green>正在进行</font></span>';
        }else{
        	return '<span><font color=gray>已经结束</font></span>';
        }
    }
    
    function setResult(e){
    	var re = e.record;
    	if(re.guessResult!=''){
			return  '<a class="New_Button" href="javascript:newRow('1')">涨</a>'
                    + ' <a class="Edit_Button" href="javascript:editRow('0')" >跌</a>';  		
    	}else{
    		return '<span><font size=20px color=red>'+re.guessResult+'</font></span>';
    	}
    }
    
    
    function queryDetail(type){
    	window.location.href='/pages/guessDetail.jsp?type='+type;
    }
    
    function onBirthdayRenderer(e) {
        var value = e.value;
        if (value) return mini.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
        return "";
    }
    function add(){
    	var line = grid.select(1,null);
    	if(line.status=='Y'){
    		mini.alert('已经发布了一个竞猜，不能新增');
    		return;
    	}
    	var url = "pages/guess_sub.jsp";
		mini.open({
			url : url,
			title : "新增竞猜",
			width : 600,
			height : 500,
			onload : function() {
				/* var iframe = this.getIFrameEl();
				var data = { value: mini.get("type").getValue(),
						      text:mini.get("type").getText()};
				iframe.contentWindow.SetData(data);  */
			},
			currentWindow:true,
			ondestroy : function(action) {
				if('cancel'!=action){
					grid.load();
				}
			}
		});
    }
</script>
</body>
</html>
