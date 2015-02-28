<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>给推广用户分红</title>
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
<div class="Place_text"><span>查询分红列表</span></div>
<div class="mini-fit">
    <div  class="mini-splitter " vertical="true" style="width:100%;height:100%;">
        <div size="150px" showCollapseButton="true" id="editForm1">
            <table width="50%" border="0" cellpadding="0" style="padding-left:5px;" cellspacing="0" align="center" class="mini-search">
                <tr>
                    <td class="mini-item_18">
					用户： <input id="referee" class="mini-textbox" style="width:40%;"/>
                    </td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>

                <tr>
                    <td colspan="4" align="center" style="padding-top: 20px;">
                        <a class="mini-button" style="margin:0px 10px;" onclick="search()">查 询</a>
                        <a class="mini-button" style="margin:0px 10px;" onclick="reset()">重 置</a>
                    </td>
                </tr>
            </table>
        </div>
        <div >

            <div id="datagrid1" class="mini-datagrid" style="width:98%;height:90%;  padding-left:15px; margin-top:10px;" allowalternating="false"
                 allowresize="false" multiselect="true"  sizeList="[20,30,50,100]" pageSize="10" allowCellEdit="true" allowCellSelect="true">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center" width="5%">序号</div>
                    <div width="10%" field="assetId"  headerAlign="center" align="center">
                        操盘单号
                    </div>
                    <div width="10%" field="nickname"  headerAlign="center" align="center">
                        用户昵称
                    </div>
                    <div field="referee" width="10%"  headerAlign="center" align="center" >
                       推广用户
                    </div>
                    <div width="10%" field="balance" headerAlign="center" align="center">
                        用户余额
                    </div>        
                    <div field="cash" width="10%"  headerAlign="center" align="center" >
                        应转金额
                    </div>                   
                    <div field="operates" width="10%"  headerAlign="center" renderer="onBirthdayRenderer" align="center" >
                        操作
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    mini.parse();
    var gridUrl = "<%=basePath%>/api/stock/web/manager/moveCashToReferee/get";
    var grid = mini.get("datagrid1");
    search();
    grid.setShowEmptyText(true);
	grid.setEmptyText("查询结果为空!");
    function search(){
        grid.setUrl(gridUrl);
        grid.load({
            "referee": mini.get("referee").getValue()
        });
    }
    
    function reset(){
    	mini.get("referee").setValue("");
    }
    
    function onBirthdayRenderer(e){
    	var re = e.record;
    	if(re.status=='0'){
    		return '<span class="link_2"><a  onclick="deduct(\''+re.nickname+'\')">分配资产</a></span>';
    	}else{
    		return '已分配';
    	}
    }

    function deduct(nickname){
    	mini.confirm("确定要分配资产吗", "确定？",
	            function (action) {            
	                if(action == "ok"){
	                	 $.ajax({
		     	                url: "<%=basePath%>/api/stock/web/withdrawlToUser",
		     	                data: {nickname : nickname},
		     	                type: "post",
		     	                success: function (msg) {
		     	                    if(msg == "1"){
		     	                    	 mini.alert("分配资产成功！","",function(action){
	     			                		if (action == "ok") {
	     			                			grid.reload();
	     			                		}
     			                		 });
		     	                    }
		     	                },
		     	                error: function(msg){
		     	                	mini.alert("服务器异常，分配资产失败！");
		     	                	return false;
		     	                }			
		     				});
	                } 
	            }
	  ); 
    }
</script>
</body>
</html>
