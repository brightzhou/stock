package com.zeekie.stock.service.homes;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 委托撤销
 * 
 * 此代码已经开过光，绝无bug
 * 
 * @author zeekie
 * 
 */
public class StockEntrustWithdraw extends AStockTrade {

	private String operatorNo;

	private String entrustNo;

	public StockEntrustWithdraw(String operatorNo, String entrustNo) {
		this.operatorNo = operatorNo;
		this.entrustNo = entrustNo;
	}

	public StockEntrustWithdraw() {
		super();
	}

	@Override
	public Map<String, String> assembleColumn(String token) {
		return getData(token);
	}

	public Map<String, String> getData(String token) {
		Map<String, String> data = new HashMap<String, String>();
		data.put("user_token", token);
		data.put("operator_no", operatorNo);
		data.put("entrust_no", entrustNo);
		return data;
	}

}
