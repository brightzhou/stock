<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>图片管理</title>
<base href="<%=basePath%>" />
<script src="scripts/base/jquery-1.8.3.min.js" type="text/javascript"></script>
<script src="scripts/sui/mini-all-min.js" type="text/javascript"></script>
<script src="swfupload/swfupload.js" type="text/javascript"></script>
<link href="scripts/sui/themes/default/miniui.css" rel="stylesheet"
	type="text/css" />
<link href="scripts/sui/themes/default/plugin.css" rel="stylesheet"
	type="text/css" />
<link href="scripts/sui/themes/icons.css" rel="stylesheet"
	type="text/css" />
<link href="scripts/sui/themes/sqt/skin.css" rel="stylesheet"
	type="text/css" />
<link href="styles/style_sqt.css" rel="stylesheet" type="text/css" />
</head>

<body class="body_fit">
	<div class="Place_text">
		<span>图片设置</span>
	</div>
	<div style="width: 1100px;">

		<input id="fileupload1" class="mini-fileupload" name="Fdata"
			limitType="*.png;*.jpg;*.apk;*.txt"
			flashUrl="<%=basePath%>swfupload/swfupload.swf"
			uploadUrl="<%=basePath%>api/stock/file/upload"
			onuploadsuccess="onUploadSuccess" onuploaderror="onUploadError" /> 
			&nbsp;&nbsp;&nbsp;
		类别：<input id="storeType" class="mini-combobox" style="width: 150px;"
			textField="text" valueField="id"
			data="[{'id': 'main', 'text': '首页'},{'id': 'start', 'text': '启动页'},{'id': 'apk', 'text': 'APK'},{'id': 'version', 'text': '版本信息'},{'id': 'picVersion', 'text': '图片版本信息'}]"
			value="main" name="type"/> 
			<br /> 
			<a class="mini-button" style="margin:0px 10px;" onclick="startUpload()" id="uploadButton">上传</a>
			
	</div>

	<script type="text/javascript">
		mini.parse();

		function onUploadSuccess(e) {

			mini.alert("上传成功：" + e.file.name);
			mini.get('uploadButton').enable();
			this.setText("");
		}
		function onUploadError(e) {
			mini.alert("上传失败："+e.file.name+",请联系管理员");
			mini.get('uploadButton').enable();
		}

		function startUpload() {
			var fileupload = mini.get("fileupload1");
			var picType = mini.get("storeType").getFormValue();

			fileupload.setPostParam({
				type : picType
			});
			fileupload.startUpload();
			if(fileupload.getText()){
				mini.get('uploadButton').disable();
			}
		}
	</script>
</body>
</html>
