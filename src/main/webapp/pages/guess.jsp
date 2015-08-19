<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>猜涨跌</title>
	<base href="<%=basePath%>"/>
	<script src="scripts/base/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="scripts/sui/mini-all-min.js" type="text/javascript"></script>
	<link href="scripts/sui/themes/default/miniui.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/default/plugin.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/icons.css" rel="stylesheet" type="text/css" />
	<link href="scripts/sui/themes/sqt/skin.css" rel="stylesheet" type="text/css" />
	<link href="styles/style_sqt.css" rel="stylesheet" type="text/css" />

<style type="text/css">

.my{
	padding-left: 20px;
	color: red;
}
</style>
</head>

<body class="body_fit" >
<div class="Place_text"><span>猜涨跌发标</span></div>
<div class="mini-fit">
    <div  class="mini-splitter " vertical="true" style="width:100%;height:100%;">
        <div size="150px" showCollapseButton="true" id="editForm1">
            <table width="100%" border="0" cellpadding="0" style="padding-left:5px;" cellspacing="0" align="center" class="mini-search">
                <tr>
                    <td class="mini-item_18">
                                              发标日期：
                    </td>
                    <td class="mini-content_32">
                    	<input id="date1" class="mini-datepicker" format="yyyy-MM-dd" />
                    </td>
                </tr>
                <tr>
                	<td/>
                    <td style="padding-top: 20px;">
                        <a class="mini-button" style="margin:0px 10px;" onclick="search();">查 询</a>
                        <a class="mini-button" style="margin:0px 10px;" onclick="add()">发标</a>
                    </td>
                </tr>
            </table>
        </div>
        <div >
            <div id="datagrid1" class="mini-datagrid" style="width:98%;height:100%;  padding-left:15px;" allowalternating="false"
                 allowresize="false"  sizeList="[20,30,50,100]" pageSize="10" >
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center" width="3%">序号</div>
                    <div width="7%" field="code" headerAlign="center" align="center">
                       产品代码
                    </div>
                    <div width="7%" field="name" headerAlign="center" align="center">
                        产品名称
                    </div>
                    <div width="6%" field="riseNum" headerAlign="center" align="center" renderer="joinDetailRise">
                        参与人数(涨)
                    </div>
                    <div width="6%" field="failNum" headerAlign="center" align="center" renderer="joinDetailFail">
                        参与人数(跌)
                    </div>
                    <div width="6%" field="perNum" headerAlign="center" align="center" >
                       押注个数
                    </div>
                    <div width="6%" field="pumpedPercent" headerAlign="center" align="center" >
                       抽成比例
                    </div>
                    <div width="7%" field="publishTime" headerAlign="center" align="center" renderer="onBirthdayRenderer">
                        发布日期
                    </div>
                    <div width="10%" field="status" headerAlign="center" align="center" renderer="changeStatus" >
                        状态
                    </div>
                    <div field="guessResult" width="10%"  headerAlign="center" align="center" renderer="setResult">
                        竞猜结果
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    mini.parse();
    var gridUrl = "api/stock/web/guessProduct/query";
    var grid = mini.get("datagrid1");
    grid.setUrl(gridUrl);
    search();
    grid.setShowEmptyText(true);
	grid.setEmptyText("查询结果为空!");
    function search(){
        grid.load({
          "date": mini.get("date1").getFormValue()
        });
    }
    
    function changeStatus(e) {
        var re = e.record;
        if (re.status=='Y') {
        	return '<span><font color=green>正在进行</font></span>';
        }else if(re.status=='N'){
        	return '<span><font color=gray>已经结束</font></span>';
        }else{
        	return '<span><font color=gray>正在进行</font></span>';
        }
    }

    function setResult(e){
    	var re = e.record;
    	if(re==null||re==undefined){
    		return;
    	}
    	var publishdate = mini.formatDate(re.publishTime, 'yyyy-MM-dd HH:mm:ss');
    	var beginstr = re.publishTime.toLocaleDateString()+" 15:05:00";
    	var begin = new Date(beginstr.replace(/-/g, "/"));
    	
    	var db1 = new Date(publishdate);
    	db1.setDate(db1.getDate()+1);
        var endstr = db1.toLocaleDateString()+" 9:00:00";
        var end = new Date(endstr.replace(/-/g, "/"));
    	
    	if(re.status!='N'){
    		//提前不可设置
   			return  "<a class='New_Button' href=javascript:setResults('rise','"+re.code+"')><font size=3px color=blue>涨</font></a>"
               + "&nbsp;&nbsp;&nbsp;&nbsp;<a class='New_Button' href=javascript:setResults('fail','"+re.code+"')><font size=3px color=blue>跌</font></a>"  	
/*     		var curr = new Date();
    		if(Date.parse(begin) < curr  && curr< Date.parse(end) ){
    		}else{
    			return  '未到公布结果时间';  		
    		} */
    	}else{
    		return '<span><font size=4px color=red>'+re.guessResult+'</font></span>';
    	}
    }
    
    function setResults(type,code){
    	var content = (type=='rise')?'涨':'跌';
    	var st = '<font color=red size=4px>'+content+'</font>';
    	mini.confirm("确定设置  "+st+" 吗,傻逼,请慎重?", "确定？",
    	function(action){
    		if(action=='ok'){
		    	$.ajax({
		            url: 'api/stock/web/guessResult/update',
		            type: "POST",
		            data: {
		                "type": type,
		                "code": code
		            },
		            success: function(data) {
		            	if(data=='1'){
		            		grid.load({
		            	          "date": mini.get("date1").getFormValue()
		            	    });
		            	}else{
		            		mini.alert('设置失败，请联系管理员');
		            	}
		            }
		        });
	    		}
    		}		
    	);
    }

    function joinDetailRise(e) {
        var re = e.record;
        if (re.riseNum==0) {
        	return re.riseNum;
        }else{
        	return '<span class="link_2"><a  onclick="queryDetail(\''+re.code+'\')">'+re.riseNum+'</a></span>';
        }
    }
    
    function joinDetailFail(e) {
        var re = e.record;
        if (re.failNum==0) {
        	return re.failNum;
        }else{
        	return '<span class="link_2"><a  onclick="queryDetail(\''+re.code+'\')">'+re.failNum+'</a></span>';
        }
    }
    
    function queryDetail(bidCode){
    	window.location.href='<%=basePath%>/pages/guessDetail.jsp?bidCode='+bidCode;
    }
    
    
    
    function onBirthdayRenderer(e) {
        var value = e.value;
        if (value) return mini.formatDate(value, 'yyyy-MM-dd HH:mm:ss');
        return "";
    }
    var myDate = new Date();
    var date = myDate.toLocaleDateString()+" 15:30:00"; 
    var dB = new Date(date.replace(/-/g, "/"));
    function add(){
    	var line = grid.data[0];
    	if(line!=null&&line.status=='Y'){
    		mini.alert('已经发布了一个竞猜，不能新增');
    		return;
    	}
    	
/*     	if(new Date()<Date.parse(dB)){
    		mini.alert('15:30以后方可设置');
    		return;
    	} */
    	
    	var url = "pages/guess_sub.jsp";
		mini.open({
			url : url,
			title : "新增竞猜",
			width : 600,
			height : 300,
			onload : function() {
				/* var iframe = this.getIFrameEl();
				var data = { value: mini.get("type").getValue(),
						      text:mini.get("type").getText()};
				iframe.contentWindow.SetData(data);  */
			},
			currentWindow:true,
			ondestroy : function(action) {
				if('cancel'!=action){
					grid.load();
				}
			}
		});
    }
</script>
</body>
</html>
