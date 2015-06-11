<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Calendar rightNow = Calendar.getInstance();
String yearMonth = rightNow.get(Calendar.YEAR)+"-"+(rightNow.get(Calendar.MONTH)+1);
System.out.println(yearMonth);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>设置服务费生效日期</title>
	<base href="<%=basePath%>"/>
	<script src="scripts/base/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="scripts/sui/mini-all-min.js" type="text/javascript"></script>
	<link href="scripts/sui/themes/default/miniui.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/default/plugin.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/icons.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/sqt/skin.css" rel="stylesheet" type="text/css" />
	<link href="styles/style_sqt.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.mini-checkboxlist-td label{
	font-family: microsoft yahei;
}
</style>
</head>

<body class="body_fit" >
<div class="Place_text"><span>字典管理</span></div>
	
	<div style="width:1100px;">
	        <div class="mini-toolbar" style="border-bottom:0;padding:2px;">
	            <table style="width:100%;">
	                <tbody><tr>
	                    <td style="white-space:nowrap;">
	                                                                                    字典名 <input   name="dicName"  id="dicName"/>  
	                        <a class="mini-button" href="javascript:search()"><span class="mini-button-text ">查询</span></a>&nbsp;&nbsp;<a class="mini-button" onclick="add()">添加</a>
	                    </td>
	                </tr>
	            </tbody></table> 
	                    
	        </div>
    	</div>
    	
    	
    	 <div id="datagrid1" class="mini-datagrid" style="width:1100px;height:400px;" allowResize="true"
        			url="<%=basePath%>api/stock/web/dictionaries/get"  idField="id" multiSelect="false">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center" width="5%">序号</div>
                    <div  field="dicName"  headerAlign="center" align="center">
                        字典名
                    </div> 
                    <div width="15%" field="dicWord" headerAlign="center" align="center">
                       字典key
                    </div>
                    <div  field="dicValue"  headerAlign="center" align="center">
                         字典值
                    </div>
                    <div  field="isCache"  headerAlign="center" align="center"  renderer="onIsCache">
                          是否缓存
                    </div>
                    <div  field="status"  headerAlign="center" align="center"   renderer="onStatus">
                      状态
                    </div>
                     <div  field="operation"  headerAlign="center" align="center" renderer="operation">
                        操作
                    </div>
                             
    
                </div>
            </div>
	
	
	 
	
	<div align="center" style="font-family: microsoft yahei" >
		<h3 id="month"></h3>
		<div id="cbl1" class="mini-checkboxlist" repeatItems="7" repeatLayout="table"
	        textField="text" valueField="id" value="" 
	        data='' >
	    </div>
	</div>
	
	
    
	<script type="text/javascript">
	    mini.parse();
	    
	    $(function(){
		    grid = mini.get("datagrid1");
	        grid.load();
		});
	    
	    function search() {
	        var dicName = $("#dicName").val();
	        grid.load({dicName: dicName});
	    }
 
	    function operation(e){
	      	var re = e.record;
	    	return '<span class="link_2"><a  onclick="update(\''+re.id+'\')">修改</a></span>&nbsp;&nbsp;<span class="link_2"><a  onclick="deleteInfo(\''+re.id+'\')">删除</a></span';
	    } 
	    
	    function onStatus(e){
	       	var re = e.record;
	    	if(re.status=='1'){
	    		return '<font color=red>可用</font>';
	    	}else{
	    		return '<font color=red>不可用</font>';
	    	}
	    }
	    function onIsCache(e){
	       	var re = e.record;
	    	if(re.isCache=='1'){
	    		return '<font color=red>是</font>';
	    	}else{
	    		return '<font color=red>否 </font>';
	    	}
	    }
	    function add(){
	    	var url = "<%=basePath%>pages/dictionariesAdd.jsp";
			mini.open({
				url : url,
				title : "添加字典信息",
				width : 600,
				height :500,
				onload : function() {
					
				},
				currentWindow:true,
				ondestroy : function(action) {
				 
				}
			});
	    }
	    
	    function update(id){
	    	var url = "<%=basePath%>pages/dictionariesUpdate.jsp";
			mini.open({
				url : url,
				title : "修改字典信息",
				width : 600,
				height :500,
				onload : function() {
					var iframe = this.getIFrameEl();
					var data = {"id":id};
					iframe.contentWindow.SetData(data); 
				},
				currentWindow:true,
				ondestroy : function(action) {
				 
				}
			});
	    }
	    
	    function deleteInfo(id){
	    	$.ajax({
	            url:"<%=basePath%>/api/stock/web/dictionaries/delete",
	            type: "POST",
	            data: {"id":id},
	            success: function(data) {
                	if(data=='1'){
                		mini.alert("删除成功！","",function(action){
	                		if (action == "ok") {
	                			 grid = mini.get("datagrid1");
	                		     grid.load();
	                		}
	                	});
                	}else{
                		mini.alert('添加失败 ');
                	}
                }
	        });
			
			
	    }
	    
	    
	 
	    function CloseWindow(action) {
	      	if (window.CloseOwnerWindow){
	      		return window.CloseOwnerWindow(action);
	      	}else{
	      		window.close();
	      	}
	    }

		function close() {
	    	CloseWindow("cancel");
	    }
	    
    </script>
</body>
</html>
