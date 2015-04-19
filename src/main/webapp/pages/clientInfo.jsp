<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户信息表</title>
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
<div class="Place_text"><span>用户信息查询</span></div>
        <div style="width:1100px;">
	        <div class="mini-toolbar" style="border-bottom:0;padding:2px;">
	            <table style="width:100%;">
	                <tr>
	                    <td style="white-space:nowrap;">
	                        <input id="key" class="mini-textbox" emptyText="请输入昵称" style="width:150px;" onenter="onKeyEnter"/>   
	                        <a class="mini-button" onclick="search()">查询</a>&nbsp;&nbsp;<a class="mini-button" onclick="sendMsg()">短信群发</a>
	                    </td>
	                </tr>
	            </table>           
	        </div>
    	</div>
        <div >
            <div id="datagrid1" class="mini-datagrid" style="width:1100px;height:400px;" allowResize="true"
        			url="<%=basePath%>api/stock/web/getClientInfo"  idField="id" multiSelect="false">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center" width="5%">序号</div>
                    <div width="15%" field="nickname" headerAlign="center" align="center">
                        用户昵称
                    </div>
                    <div  field="trueName"  headerAlign="center" align="center">
                        真实姓名
                    </div>
                    <div  field="phone"  headerAlign="center" align="center">
                        手机
                    </div>
                    <div field="balance" width="10%"  headerAlign="center" align="center" >
                        账户余额
                    </div>
                    <div  field="actualFund" headerAlign="center" align="center" renderer="onHdRender">
                        实盘金额
                    </div>
                    <div  field="upLine"  headerAlign="center" align="center">
                        上线
                    </div>
                    <div  field="operation"  headerAlign="center" align="center" renderer="operation">
                        操作
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
    
	
	function search() {
        var nickname = mini.get("key").getValue();
        grid.load({ nickname: nickname });
    }
	
    function onKeyEnter(e) {
        search();
    }
    
    function onHdRender(e){
       	var re = e.record;
    	if(re.actualFund==''||re.actualFund==null){
    		return '<font color=red>未操盘</font>';
    	}else{
    		return re.actualFund;
    	}
    }
    
    function operation(e){
      	var re = e.record;
    	return '<span class="link_2"><a  onclick="sendRedPacket(\''+re.nickname+'\',\''+re.phone+'\')">发红包</a></span>&nbsp;&nbsp;';
    }
    
    function sendRedPacket(nickname,phone){
    	var url = "<%=basePath%>pages/sendRedPacket.jsp";
		mini.open({
			url : url,
			title : "发红包",
			width : 500,
			height : 350,
			onload : function() {
				var iframe = this.getIFrameEl();
				var data = { telephone: phone,
						      text: nickname};
				iframe.contentWindow.SetData(data); 
			},
			currentWindow:true,
			ondestroy : function(action) {
				if('cancel'!=action){
					grid.load({
				          "nickname": nickname,
				    });
				}else{
					
				}
			}
		});
    }
    
    function sendMsg(){
    	var url = "<%=basePath%>pages/sendMsgPage.jsp";
		mini.open({
			url : url,
			title : "发红包",
			width : 500,
			height : 350,
			onload : function() {
				var iframe = this.getIFrameEl();
				var data = { telephone: phone,
						      text: nickname};
				iframe.contentWindow.SetData(data); 
			},
			currentWindow:true,
			ondestroy : function(action) {
				/* if('cancel'!=action){
					grid.load();
				} */
			}
		});
    }
</script>
</body>
</html>
