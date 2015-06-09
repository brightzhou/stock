package com.zeekie.stock.service.homes;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 *  查询 
 * 
 * @author zeekie
 * 
 */
public class StockCommQuery extends AStockTrade {

    private String type ="1";
    private String initDate;
	public StockCommQuery() {
		super();
	}
    public StockCommQuery(String type,String initDate){
    	this.type = type;
    	this.initDate = initDate;
    }
	@Override
	public Map<String, String> assembleColumn(String token) {
		return getData(token);
	}

	public Map<String, String> getData(String token) {
		Map<String, String> data = new HashMap<String, String>();
		if("2".equals(type)){
		  data.put("init_date", initDate);
		}
		data.put("user_token", token);
		return data;
	}
}
