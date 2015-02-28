<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户属性</title>
	<base href="<%=basePath%>"/>
	<script src="scripts/base/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="scripts/sui/mini-all-min.js" type="text/javascript"></script>
	<link href="scripts/sui/themes/default/miniui.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/default/plugin.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/icons.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/sqt/skin.css" rel="stylesheet" type="text/css" />
	<link href="styles/style_sqt.css" rel="stylesheet" type="text/css" />
   <style type="text/css">
    html, body
    {
        font-size:12px;
        padding:0;
        margin:0;
        border:0;
        height:100%;
        overflow:visible;
    }
    </style>
</head>

<body class="body_fit" >
<form id="form1" method="post">
        <input name="id" class="mini-hidden" />
        <div style="padding-left:11px;padding-bottom:5px;">
            <table style="table-layout:fixed;">
                <tr>
                    <td style="width:70px;">用户昵称：</td>
                    <td style="width:150px;">    
                        <input name="nickname" class="mini-textbox" required="true" readonly="readonly"/>
                    </td>
                    <td style="width:70px;">用户等级：</td>
                    <td style="width:150px;">    
                        <input name="range" class="mini-combobox" valueField="id" textField="text" 
                            data="[{'id': '1', 'text': '小生'},{'id': '2', 'text': '秀才'},{'id': '3', 'text': '举人'},{'id': '4', 'text': '探花'},{'id': '5', 'text': '榜眼'},{'id': '6', 'text': '状元'}]"
                             required="true"
                            />
                    </td>
                </tr>
                <tr>
                    <td >平仓线：</td>
                    <td >    
                        <input name="stopLine" class="mini-textbox" required="true" vtype="float" vtype="float;rangeDecimals:2"/>
                    </td>
                    <td >止损线：</td>
                    <td >    
                        <input name="warnLine" class="mini-textbox" required="true" vtype="float" vtype="float;rangeDecimals:2"/>
                    </td>
                </tr>
               
                <tr>
                    <td >配资比例：</td>
                    <td >    
                        <input name="assignPercent" class="mini-textbox"  required="true" vtype="int"/>
                    </td>
                    <td >给推荐人红包：</td>
                    <td >    
                        <input name="toRefereePacket"  class="mini-textbox" required="true" vtype="float" vtype="float;rangeDecimals:2"/>
                    </td>
                </tr>     
                
                <tr>
                    <td >注册红包：</td>
                    <td >    
                        <input name="registerPacket"  class="mini-textbox" required="true" vtype="float" vtype="float;rangeDecimals:2"/>
                    </td>
                    <td >技术服务费比例：</td>
                    <td >    
                        <input name="managementFeePercent"  class="mini-textbox" required="true" vtype="float" vtype="float;rangeDecimals:3"/>
                    </td>
                </tr>   
                
                <tr>
                    <td >配资额度：</td>
                    <td >    
                        <input name="assignCash"   class="mini-textbox" required="true" vtype="int"/>
                    </td>
                    <td >上线比例：</td>
                    <td >    
                        <input name="upLinePercent" class="mini-textbox" required="true"  vtype="float" vtype="float;rangeDecimals:2"/>
                    </td>
                </tr>   
                <tr>
                    <td >下线比例：</td>
                    <td >    
                        <input name="downLinePercent"  class="mini-textbox" required="true"  vtype="float" vtype="float;rangeDecimals:2"/>
                    </td>
                </tr>   
                      
            </table>
        </div>
        <div style="text-align:center;padding:10px;">               
            <a class="mini-button" onclick="onOk" style="width:60px;margin-right:20px;">确定</a>       
            <a class="mini-button" onclick="onCancel" style="width:60px;">取消</a>       
        </div>        
    </form>
    <script type="text/javascript">
        mini.parse();

        var form = new mini.Form("form1");

        function SaveData() {
            var o = form.getData();            

            form.validate();
            if(!form.isChanged()){
            	mini.alert('数据没有修改，不用提交');
            	return;
            }
            if (form.isValid() == false) return;

            var json = mini.encode(o);
            $.ajax({
                url: "<%=basePath%>api/stock/web/saveClientInfo",
                data: { data: json },
                cache: false,
                success: function (text) {
                	if(text=='1'){
	                    CloseWindow("save");
                	}else{
                		mini.alert('保存数据发生异常，请联系管理员');
                	}
                },
                error: function (text) {
                    alert('服务器发生异常');
                    CloseWindow();
                }
            });
        }

        ////////////////////
        //标准方法接口定义
        function SetData(data) {
            if (data.action == "edit") {
                //跨页面传递的数据对象，克隆后才可以安全使用
                data = mini.clone(data);

                $.ajax({
                    url: "<%=basePath%>api/stock/web/getClientById?id=" + data.id,
                    cache: false,
                    success: function (text) {
                        var o = mini.decode(text);
                        form.setData(o);
                        form.setChanged(false);

                        //mini.getbyName("position").setValue(o.position);
                    }
                });
            }
        }

        function GetData() {
            var o = form.getData();
            return o;
        }
        function CloseWindow(action) {            
            if (action == "close" && form.isChanged()) {
                if (confirm("数据被修改了，是否先保存？")) {
                    return false;
                }
            }
            if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
            else window.close();            
        }
        function onOk(e) {
            SaveData();
        }
        function onCancel(e) {
            CloseWindow("cancel");
        }

    </script>
</body>
</html>
