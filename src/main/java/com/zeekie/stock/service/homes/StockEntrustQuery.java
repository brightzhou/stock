package com.zeekie.stock.service.homes;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 委托查询 当日
 * 
 * 此代码已经开过光，绝无bug
 * 
 * @author zeekie
 * 
 */
public class StockEntrustQuery extends AStockTrade {

	private String fundAccount;

	private String combineId;

	public StockEntrustQuery(String fundAccount, String combineId) {
		this.fundAccount = fundAccount;
		this.combineId = combineId;
	}

	public StockEntrustQuery() {
		super();
	}

	@Override
	public Map<String, String> assembleColumn(String token) {
		return getData(token);
	}

	public Map<String, String> getData(String token) {
		Map<String, String> data = new HashMap<String, String>();
		data.put("user_token", token);
		data.put("fund_account", fundAccount);
		data.put("combine_id", combineId);
		return data;
	}

}
