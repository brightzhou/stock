<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>平仓</title>
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
<div class="Place_text"><span>平仓</span></div>
<div class="mini-fit">
    <div  class="mini-splitter " vertical="true" style="width:100%;height:100%;">
        <div size="150px" showCollapseButton="true" id="editForm1">
            <table width="70%" border="0" cellpadding="0" style="padding-left:5px;" cellspacing="0" align="center" class="mini-search">
<!--                 <tr>
                    <td class="mini-item_18">
                    	用户：<input id="nickname" class="mini-textbox" style="width:100%;"/>
                    </td>
                </tr>
 -->
                <tr>
                    <td colspan="4" align="center" style="padding-top: 20px;">
                        <a class="mini-button" style="margin:0px 10px;" onclick="startFreash();">开始查询</a>
                        <!-- <a class="mini-button" style="margin:0px 10px;" onclick="reset()">重 置</a>  -->
                        <a class="mini-button" style="margin:0px 10px;" onclick="stop()">停止刷新</a>
                    </td>
                </tr>
            </table>
        </div>
        <div >

            <div id="datagrid1" class="mini-datagrid" style="width:98%;height:90%;  padding-left:15px; margin-top:10px;" allowalternating="false"
                 allowresize="false" multiselect="true"  sizeList="[20,30,50,100]" pageSize="10" allowCellEdit="true" allowCellSelect="true">
                <div property="columns">
                    <div  field="id" width="5%" headerAlign="center" align="center" align="center">
		序号
                    </div>
                    <div width="10%" field="tradeAccount"  headerAlign="center" align="center">
                        操盘账号
                    </div>
                    <div width="10%" field="nickname"  headerAlign="center" align="center">
                        用户
                    </div>
                    <div width="10%" field="actualCash"  headerAlign="center" align="center">
                        实际资产
                    </div>
                    <div width="10%" field="stopLine"  headerAlign="center" align="center">
                        平仓线
                    </div>
                    <div width="10%" field="warnLine"  headerAlign="center" align="center">
                        警戒线
                    </div>
                    <div field="operate" width="10%"  headerAlign="center" renderer="onOpRender" align="center" >
                        操作
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- <bgsound id = "bg"> -->
<audio  autoplay="true" playcount="-1" id="bg"></audio>
<script type="text/javascript">
    mini.parse();
    var gridUrl = "<%=basePath%>/api/stock/web/eveningUp/get";
    var grid = mini.get("datagrid1");
    //search();
    var si;
    var sn;
    var start = 0;
    grid.setShowEmptyText(true);
	grid.setEmptyText("查询结果为空!");
    function search(){
        grid.setUrl(gridUrl);
        grid.load({
            "nickname": ''/* $.trim(mini.get("nickname").getValue()) */
        });
        
        var total = grid.getTotalCount();
        if(start==0){
		    play(total);
        }
       
    }
    
/*     setInterval(function(){
    	search();
    },5000); */
    startFreash();
    function startFreash(){
    	sn = setInterval(search,5000);
    }
    
    function play(total){
    	if(total>0){
    		start = 1;
	    	setTimeout(cc,10);
    	}
    }
    
    function stop(){
    	start = 0;
    	clearInterval(sn);
    	clearInterval(si);
    }

    function cc(){
       si = setInterval(sing,3000);
    }
    
    function sing(){
    	document.getElementById("bg").src="<%=basePath%>files/dd.mp3"; 
    }
    
    function reset(){
    	mini.get("nickname").setValue("");
    }
    
    
    function onOpRender(e){
    	var re = e.record;
    	if(re.status=='1'){
    		return '<span class="link_2"><a  onclick="pay(\''+re.id+'\',\''+re.nickname+'\')">平仓</a></span>';
    	}else{
    		return '已平仓';
    	}
    }

    function pay(id,nickname){
    	mini.confirm("确定平仓吗", "确定？",
	            function (action) {            
	                if(action == "ok"){
	                	 $.ajax({
		     	                url: "<%=basePath%>api/stock/web/eveningUp",
		     	                data: {id : id,nickname : nickname},
		     	                type: "post",
		     	                success: function (msg) {
		     	                    if(msg == "1"){
		     	                    	 mini.alert("平仓成功！","",function(action){
	     			                		if (action == "ok") {
	     			                			grid.reload();
	     			                		}
     			                		 });
		     	                    }else{
		     	                    	mini.alert('该用户尚未在homes平仓，先进行平仓后再在此操作!');
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
