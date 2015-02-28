<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>管理页面</title>
	<base href="<%=basePath%>"/>
	<script src="scripts/base/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="scripts/sui/mini-all-min.js" type="text/javascript"></script>
	<link href="scripts/sui/themes/default/miniui.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/default/plugin.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/icons.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/sqt/skin.css" rel="stylesheet" type="text/css" />
	<link href="styles/style_sqt.css" rel="stylesheet" type="text/css" />
<style type="text/css">

    body{
        margin:0;padding:0;border:0;width:100%;height:100%;overflow:visible;
    }    
    .header
    {
        background:url('<%=basePath%>icons/header.png') repeat-x 0 -1px;
    }

	.icon-close{
		background: url("<%=basePath%>icons/cancel.gif");
	}
	.icon-edit{
		background: url("<%=basePath%>icons/edit.gif");
	}
</style>
</head>

<body class="body_fit" >
<div id="layout1" class="mini-layout" style="width:100%;height:100%;">
    <div class="header" region="north" height="70" showSplit="false" showHeader="false">
        <h1 style="margin:0;padding:15px;cursor:default;font-family:微软雅黑,黑体,宋体;">哈哈宝应用管理后台V1.0</h1>
        <div style="position:absolute;top:18px;right:10px;">
            <a class="mini-button mini-button-iconTop" iconCls="icon-edit" onclick="onClick"  plain="true" >首页</a>        
            <a class="mini-button mini-button-iconTop" iconCls="icon-close" onclick="logout"  plain="true" >登出</a>        
        </div>
        
    </div>
    <div title="south" region="south" showSplit="false" showHeader="false" height="30" >
        <div style="line-height:28px;text-align:center;cursor:default">Copyright ? Zeekie版权所有 </div>
    </div>
    <div title="center" region="center" style="border:0;" bodyStyle="overflow:hidden;">
        <!--Splitter-->
        <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0;">
            <div size="180" maxSize="250" minSize="100" showCollapseButton="true" style="border:0;">
                <!--OutlookTree-->
                <div id="leftTree" class="mini-outlooktree" url="<%=basePath%>/pages/menu.txt" onnodeselect="onNodeSelect"
                    textField="text" idField="id" parentField="pid" contextMenu="#treeMenu" expandOnLoad=true>
                </div>
            </div>
            <div showCollapseButton="false" style="border:0;">
                <!--Tabs-->
                <div id="mainTabs" class="mini-tabs bg-toolbar" activeIndex="0" style="width:100%;height:100%;">
                    <div title="首页" url="<%=basePath%>pages/over.jsp" >        
                    </div>
                </div>
            </div>        
        </div>
    </div>
</div>
<!-- <ul id="treeMenu" class="mini-contextmenu"  >        
    <li iconCls="icon-move" onclick="onMoveNode">移动节点</li>
    <li class="separator"></li>
    <li>
		<span iconCls="icon-add">新增节点</span>
		<ul>
		    <li onclick="onAddBefore">插入节点前</li>                
            <li onclick="onAddAfter">插入节点后</li>	
			<li onclick="onAddNode">插入子节点</li>	             
		</ul>
	</li>
	<li name="edit" iconCls="icon-edit" onclick="onEditNode">编辑节点</li>
	<li name="remove" iconCls="icon-remove" onclick="onRemoveNode">删除节点</li>        
</ul> -->
    

    <script type="text/javascript">
        function showTab(node) {
            var tabs = mini.get("mainTabs");

            var id = "tab$" + node.id;
            var tab = tabs.getTab(id);
            if (!tab) {
                tab = {};
                tab.name = id;
                tab.title = node.text;
                tab.showCloseButton = true;

                //这里拼接了url，实际项目，应该从后台直接获得完整的url地址
                tab.url = "<%=basePath%>pages/" + node.id + ".jsp";

                tabs.addTab(tab);
            }
            tab.url = "<%=basePath%>pages/" + node.id + ".jsp";
            tabs.activeTab(tab);
        }

        
        function onNodeSelect(e) {
            var node = e.node;
            var isLeaf = e.isLeaf;

            if (isLeaf) {
                showTab(node);
            }
        }

        function onClick(e) {
            var text = this.getText();
            alert(text);
        }
        
        function logout(){
        	window.location.href = "<%=basePath%>api/stock/web/logout";
        }
        
        history.go(1); 
    </script>
</body>
</html>
