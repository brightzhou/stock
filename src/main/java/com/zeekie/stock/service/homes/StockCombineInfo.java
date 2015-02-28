package com.zeekie.stock.service.homes;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 组合信息查询
 * 
 * @author zeekie
 * 
 */
public class StockCombineInfo extends AStockTrade {

	/**
	 * 账户编号
	 */
	private String fundAccount;

	/**
	 * 组合编号
	 */
	private String combineId;

	public StockCombineInfo(String fundAccount, String combineId) {
		this.fundAccount = fundAccount;
		this.combineId = combineId;
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
