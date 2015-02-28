<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户支付</title>
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
<div class="Place_text"><span>用户支付</span></div>
<div class="mini-fit">
    <div  class="mini-splitter " vertical="true" style="width:100%;height:100%;">
        <div >

            <div id="datagrid1" class="mini-datagrid" style="width:98%;height:90%;  padding-left:15px; margin-top:10px;" allowalternating="false"
                 allowresize="false" multiselect="true"  sizeList="[20,30,50,100]" pageSize="10" allowCellEdit="true" allowCellSelect="true">
                <div property="columns">
                    <!-- <div type="indexcolumn" headerAlign="center" width="5%">序号</div> -->
                    <div  field="id"  headerAlign="center" align="center" align="center">
		序号
                    </div>
                    <div  field="nickname" renderer="onHdRender" headerAlign="center" align="center">
                        用户昵称
                    </div>
                    <div field="payAccount"   headerAlign="center" align="center" >
                        支付宝账号
                    </div>
                    <div  field="fund" headerAlign="center" align="center">
                        充值金额
                    </div>
                    <div field="payDate" width="10%"  headerAlign="center" align="center" renderer="onBirthdayRenderer">
                        充值时间
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
    var gridUrl = "<%=basePath%>/api/stock/web/pay/get";
    var grid = mini.get("datagrid1");
    search();
    grid.setShowEmptyText(true);
	grid.setEmptyText("查询结果为空!");
    function search(){
        grid.setUrl(gridUrl);
        grid.load({
           /*  "depositType": mini.get("type").getValue(),
            "nickname": $.trim(mini.get("nickname").getValue()) */
        });
    }
    
/*     function reset(){
    	mini.get("type").setValue("");
    	mini.get("nickname").setValue("");
    } */
    
    function onBirthdayRenderer(e) {
        var value = e.value;
        if (value) {
        	var dates = mini.formatDate(value, 'yyyy-mm-dd HH:mm:ss');
        	return dates;
        }
        return "";
    }
    
    function onOpRender(e){
    	var re = e.record;
    	if(re.status=='0'){
    		return '<span class="link_2"><a  onclick="pay(\''+re.id+'\',\''+re.nickname+'\',\''+re.fund+'\')">付款</a></span>';
    	}else{
    		return '已付款';
    	}
    }

    function pay(id,nickname,fund){
    	mini.confirm("确定要付款吗", "确定？",
	            function (action) {            
	                if(action == "ok"){
	                	 $.ajax({
		     	                url: "<%=basePath%>api/stock/web/payToUs",
		     	                data: {id:id, nickname : nickname, fund : fund},
		     	                type: "post",
		     	                success: function (msg) {
		     	                    if(msg == "1"){
		     	                    	 mini.alert("付款成功！","",function(action){
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
