<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户列表</title>
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
<div class="Place_text"><span>用户列表查询</span></div>
        <div style="width:100%;">
	        <div class="mini-toolbar" style="border-bottom:0;padding:2px;">
	            <table style="width:100%;">
	                <tr>
	                    <td style="width:100%;">
	                        <a class="mini-button" iconCls="icon-add" onclick="edit()">编辑</a>
	                    </td>
	                    <td style="white-space:nowrap;">
	                        <input id="key" class="mini-textbox" emptyText="请输入昵称" style="width:150px;" onenter="onKeyEnter"/>   
	                        <a class="mini-button" onclick="search()">查询</a>
	                    </td>
	                </tr>
	            </table>           
	        </div>
    	</div>
        <div >
            <div id="datagrid1" class="mini-datagrid" style="width:100%;height:400px;" allowResize="true"
        			url="api/stock/web/getClient"  idField="id" multiSelect="false">
                <div property="columns">
                    <!-- <div type="indexcolumn" headerAlign="center" width="5%">序号</div> -->
                    <div type="checkcolumn" width="3%">#</div>
                    <div width="8%" field="nickname" renderer="onHdRender" headerAlign="center" align="center" >
                        用户昵称
                    </div>
                    <div  field="trueName"  headerAlign="center" align="center" width="7%">
                        真实姓名
                    </div>
                    <div  field="phone"  headerAlign="center" align="center" width="7%">
                        手机
                    </div>
                    <div field="range" width="5%"  headerAlign="center" align="center" >
                        用户等级
                    </div>
                    <div  field="stopLine" headerAlign="center" align="center" width="5%">
                        平仓线
                    </div>
                    <div  field="warnLine"  headerAlign="center" align="center" width="5%">
                        止损线
                    </div>         
                    <div field="assignPercent"   headerAlign="center" align="center" width="5%">
                        配资比例
                    </div>                   
                    <div  field="toRefereePacket" width="4%"  headerAlign="center" align="center" width="10%">
                        给推荐人红包
                    </div>
                    <div field="registerPacket" width="4%"  headerAlign="center" align="center" >
                        注册红包
                    </div>
                    <div field="managementFeePercent" width="6%"  headerAlign="center"  align="center" >
                        技术服务费比例
                    </div>
                    <div field="assignCash" width="6%"  headerAlign="center"  align="center" >
                        配资额度
                    </div>
                    <div field="upLinePercent" width="5%"  headerAlign="center"  align="center" >
                        上线比例
                    </div>
                    <div field="downLinePercent" width="5%"  headerAlign="center"  align="center" >
                        下线比例
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
    
	function edit() {
        var row = grid.getSelected();
        if (row) {
            mini.open({
                url: "<%=basePath%>pages/clientEdit.jsp",
                title: "编辑信息", width: 600, height: 360,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = { action: "edit", id: row.id };
                    iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                	if('cancel'!=action){
	                    grid.reload();
                	}
                }
            });
        } else {
            mini.alert("请选中一条记录");
        }
    };
	
	function search() {
        var nickname = mini.get("key").getValue();
        grid.load({ nickname: nickname });
    }
	
    function onKeyEnter(e) {
        search();
    }
    
</script>
</body>
</html>
