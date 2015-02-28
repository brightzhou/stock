
//获取url的param
function getUrlParam(name) {
	var str = window.location.search;
	if (str.indexOf(name) != -1) {
		var pos_start = str.indexOf(name) + name.length + 1;
		var pos_end = str.indexOf("&", pos_start);
		if (pos_end == -1) {
			return  str.substring(pos_start);
		} else {
			return str.substring(pos_start, pos_end);
		}
	} else {
		return "没有这个name值";
	}
}

//获取本月的第一天
function getFirstDayOfCurrentMonth(){
    var currentDate = new Date();
    var year = currentDate.getFullYear();
    var month = currentDate.getMonth() + 1;
    if (month < 10) {
        month = "0" + month;
    }
    return year + "-" + month + "-01";
}

function HTMLEncode(txt){
	if(txt){
		var Ntxt = txt;
		for(var i = 0; i<Ntxt.length; i++){
			Ntxt = Ntxt.replace(" ", "&nbsp;");
			Ntxt = Ntxt.replace("<", "&lt;");
			Ntxt = Ntxt.replace(">", "&gt;");
			Ntxt = Ntxt.replace("\"", "&quot;");
			Ntxt = Ntxt.replace("'", "&#39;");
			Ntxt = Ntxt.replace("\n", "<br>");
		}
		return Ntxt;
	}
};
//获取当前日
function getCurrentDay(){
    var currentDate = new Date();
    return mini.formatDate(currentDate, "yyyy-MM-dd");

}

//关闭窗口			
function CloseWindow(action){
  	if(window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
       else window.close();
}
function onCancel(){
	CloseWindow("cancel");
}


//树节点进行操作
function beforeNodeClick(e){
	//判断是否是叶子节点
	 if(!e.isLeaf){
		var childrenIds = this.tree.getAllChildNodes(e.node);//e.node.children;
    	for(var i = 0; i< childrenIds.length; i++){
    		if(e.checked){
	    		this.tree.enableNode(childrenIds[i]);
    		}
    	}
	}
}

function taxValueChange(e){
	var values = e.value.split(",");
		//所有树节点
	var allTreeDatas = this.tree.getAllChildNodes(this.tree.data[0]);
	allTreeDatas.unshift(this.tree.data[0]);
			
	//获取选中的值
	if(!values[0]){
		this.tree.uncheckAllNodes(allTreeDatas);
    	for(var i = 0 ; i < allTreeDatas.length ; i++){
    		this.tree.enableNode(allTreeDatas[i]);
    	}		
		return false;
	}
	
	for(var i = 0 ; i < values.length ; i++){
    		if(allTreeDatas.length > 0 && allTreeDatas[i]){
    		for(var j =0; j< allTreeDatas.length ; j++){
    			if(values[i] == allTreeDatas[j].id){
    				var grandsons =  this.tree.getAllChildNodes(values[i]);
    				for(var k = 0; k< grandsons.length ; k++){
    					if(this.tree.hasChildren(values[i])){
							if(this.tree.isEnabledNode(grandsons[k])){
								this.tree.disableNode(grandsons[k]);
							}
							this.tree.uncheckNode(grandsons[k]);
    					}else{
    						if(!this.tree.isEnabledNode(grandsons[k])){
	    						this.tree.enableNode(grandsons[k]);
    						}
    						this.tree.uncheckNode(grandsons[k]);
        				}
					}
    			}
    		}
		}
	}
	
	var treeValues = [];
	var treeTexts = [];
	if(this.tree.getCheckedNodes().length > 0){
    	for(var p=0 ; p< this.tree.getCheckedNodes().length; p++){
    		treeValues.push(this.tree.getCheckedNodes()[p].id);
    		treeTexts.push(this.tree.getCheckedNodes()[p].text);
    	}
   		mini.get("taxPayOffical").setValue(treeValues.toString());	
   		mini.get("taxPayOffical").setText(treeTexts.toString());	
	}
	
	}