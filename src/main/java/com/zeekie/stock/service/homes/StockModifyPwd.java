package com.zeekie.stock.service.homes;

import java.util.HashMap;
import java.util.Map;

public class StockModifyPwd extends AStockTrade {

	private String operatorNo;
	private String newPwd;
	private String historyPwd;

	public StockModifyPwd(String operatorNo, String historyPwd,
			String newPwd) {
		this.operatorNo = operatorNo;
		this.historyPwd = historyPwd;
		this.newPwd = newPwd;
	}

	public StockModifyPwd() {
		super();
	}

	@Override
	public Map<String, String> assembleColumn(String token) {
		return getData(token);
	}

	private Map<String, String> getData(String token) {
		Map<String, String> data = new HashMap<String, String>();
		data.put("user_token", token);
		data.put("operator_no", operatorNo);
		data.put("password_new", newPwd);
		return data;
	}

}
