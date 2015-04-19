<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户提现查询</title>
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
<div class="Place_text"><span>用户提现查询</span></div>
<div class="mini-fit">
    <div  class="mini-splitter " vertical="true" style="width:100%;height:100%;">
        <div size="150px" showCollapseButton="true" id="editForm1">
            <table width="70%" border="0" cellpadding="0" style="padding-left:5px;" cellspacing="0" align="center" class="mini-search">
                <tr>
                    <td class="mini-item_18">
                        提现标志：
                    </td>
                    <td class="mini-content_32">
                    <input id="type" class="mini-combobox" style="width:150px;" textField="text" valueField="id" 
    					data="[{'id': '', 'text': '全部'},{'id': '1', 'text': '已提现'},{'id': '0', 'text': '未提现'}]" />  
                    </td>
                    <td class="mini-item_18">
		提现人：
                    </td>
                    <td class="mini-item_18">
                    	<input id="nickname" class="mini-textbox" style="width:85%;"/>
                    </td>
                </tr>

                <tr>
                    <td colspan="4" align="center" style="padding-top: 20px;">
                        <a class="mini-button" style="margin:0px 10px;" onclick="search();">查 询</a>
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
                    <div width="10%" field="nickname" renderer="onHdRender" headerAlign="center" align="center">
                        提现人
                    </div>
                    <div field="truename" width="10%"  headerAlign="center" align="center" >
                        真实姓名
                    </div>
                    <div field="phone" width="10%"  headerAlign="center" align="center" >
                        手机号码
                    </div>
                    <div  field="idCard" headerAlign="center" align="center">
                        身份证号
                    </div>
                    <div  field="openBank"  headerAlign="center" align="center">
                        开户行
                    </div>  
                    <div  field="bank"  headerAlign="center" align="center">
                        银行名称
                    </div>         
                    <div field="bankNO"   headerAlign="center" align="center" >
                        银行卡号
                    </div>                   
                    <div  field="cash" width="10%"  headerAlign="center" align="center">
                        提现金额
                    </div>
                    <div field="time" width="10%"  headerAlign="center" align="center" renderer="onBirthdayRenderer">
                        提现时间
                    </div>
                    <div field="operate" width="10%"  headerAlign="center" renderer="onOpRender" align="center" >
                        操作
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    mini.parse();
    var gridUrl = "<%=basePath%>/api/stock/web/userWithdrawl/get";
    var grid = mini.get("datagrid1");
    search();
    grid.setShowEmptyText(true);
	grid.setEmptyText("查询结果为空!");
    function search(){
        grid.setUrl(gridUrl);
        grid.load({
            "depositType": mini.get("type").getValue(),
            "nickname": $.trim(mini.get("nickname").getValue())
        });
    }
    
    function reset(){
    	mini.get("type").setValue("");
    	mini.get("nickname").setValue("");
    }
    
    function onBirthdayRenderer(e) {
        var value = e.value;
        if (value) return mini.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
        return "";
    }
    
    function onOpRender(e){
    	var re = e.record;
    	if(re.status=='0'){
    		return '<span class="link_2"><a  onclick="withdrawl(\''+re.id+'\',\''+re.nickname+'\',\''+re.cash+'\')">提现</a></span>'
    		+'&nbsp;&nbsp;<span class="link_2"><a  onclick="undo(\''+re.id+'\',\''+re.nickname+'\',\''+re.cash+'\')">撤销</a></span>';
    	}else{
    		return '已提现';
    	}
    }

    function withdrawl(id,nickname,cash){
    	mini.confirm("确定要提现吗", "确定？",
	            function (action) {            
	                if(action == "ok"){
	                	 $.ajax({
		     	                url: "<%=basePath%>api/stock/web/withdrawlToUser",
		     	                data: {id:id,nickname:nickname,cash:cash},
		     	                type: "post",
		     	                success: function (msg) {
		     	                    if(msg == "1"){
		     	                    	 mini.alert("提现成功！","",function(action){
	     			                		if (action == "ok") {
	     			                			grid.reload();
	     			                		}
     			                		 });
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
    
    function undo(id,nickname,cash){
    	mini.confirm("确定要撤销吗", "确定？",
	            function (action) {            
	                if(action == "ok"){
	                	 $.ajax({
		     	                url: "<%=basePath%>api/stock/web/undoWithDrawal",
		     	                data: {id:id, nickname:nickname,cash:cash},
		     	                type: "post",
		     	                success: function (msg) {
		     	                    if(msg == "1"){
		     	                    	 mini.alert("撤销成功！","",function(action){
	     			                		if (action == "ok") {
	     			                			grid.reload();
	     			                		}
     			                		 });
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
</script>
</body>
</html>
