package com.zeekie.stock.service.homes;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 重新计算各种值
 * 
 * fuck you
 * 
 * @author zeekie
 *
 */
public class StockCapitalChanges extends AStockTrade {

	private String fundAccount;

	private String combineId;

	public StockCapitalChanges(String fundAccount, String combineId) {
		this.fundAccount = fundAccount;
		this.combineId = combineId;
	}

	public StockCapitalChanges() {
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
