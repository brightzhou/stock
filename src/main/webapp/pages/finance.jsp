<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>理财</title>
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
<div class="Place_text"><span>查询理财李彪</span></div>
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
                 allowresize="false" multiselect="true"  sizeList="[20,30,50,100]" pageSize="10" allowCellEdit="true" allowCellSelect="true">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center" width="3%">序号</div>
                    <div width="7%" field="productCode" headerAlign="center" align="center">
                        理财代码
                    </div>
                    <div width="7%" field="financeProduct" headerAlign="center" align="center">
                        理财名称
                    </div>
                    <div width="6%" field="financeTotalLimit" headerAlign="center" align="center">
                        额度
                    </div>
                    <div field="annualIncome" width="5%"  headerAlign="center" align="center">
                        年收益
                    </div>
                    <div field="expireDay" width="6%"  headerAlign="center" align="center">
                        期限
                    </div>
                    <div field="maxLimit" width="10%"  headerAlign="center" align="center">
                        限购最大额度
                    </div>
                    <div width="10%" field="minLimit" headerAlign="center" align="center">
                        限购最小额度
                    </div>
                    <div width="7%" field="carryDate" headerAlign="center" align="center">
                        结息日期
                    </div>
                    <div width="6%" field="num" headerAlign="center" align="center" renderer="changeContent">
                        购买人数
                    </div>
                    <div width="10%" field="leaveDays" headerAlign="center" align="center" renderer="changeStatus">
                        状态
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    mini.parse();
    var gridUrl = "api/stock/web/finance/get";
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
    
    var status='0';
    function changeStatus(e) {
        var re = e.record;
        if (parseInt(re.leaveDays)>0) {
        	status = '1';
        	return '<span><font color=green>正在售卖,剩余('+re.leaveDays+')天</font></span>';
        }else{
        	return '<span><font color=red>已经结束</font></span>';
        }
    }
    
    function changeContent(e) {
        var re = e.record;
        if (re.num=='' || re.num==null) {
        	return '<span><font color=red>未购买</font></span>';
        }else{
        	status = '1';
        	return '<span class="link_2"><a  onclick="queryDetail(\''+re.productCode+'\')">'+re.num+'</a></span>&nbsp;&nbsp;';
        }
    }
    
    function queryDetail(productCode){
    	window.location.href='<%=basePath%>/pages/financeDetail.jsp?productCode='+productCode;
    }
    
    function onBirthdayRenderer(e) {
        var value = e.value;
        if (value) return mini.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
        return "";
    }
    function add(){
    	
    	if(status=='1'){
    		mini.alert('仍然有产品在售卖中，不能新增');
    		return;
    	}
    	
    	var url = "pages/finance_sub.jsp";
		mini.open({
			url : url,
			title : "新增理财产品",
			width : 500,
			height : 350,
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
