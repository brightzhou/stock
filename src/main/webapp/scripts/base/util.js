util={
	//获取当前日期之后n天的日期
	getDate : function(n){
		var dd = new Date();    
	    dd.setDate(dd.getDate()+ n);//获取n天后的日期 
	    return dd.toLocaleDateString();
	}
		
};
