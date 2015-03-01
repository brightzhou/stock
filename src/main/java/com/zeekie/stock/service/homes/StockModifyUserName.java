package com.zeekie.stock.service.homes;

import java.util.HashMap;
import java.util.Map;

public class StockModifyUserName extends AStockTrade {

	private String fundAccount;

	private String combineId;

	private String assetName;

	public StockModifyUserName(String fundAccount, String combineId,
			String assetName) {
		super();
		this.fundAccount = fundAccount;
		this.combineId = combineId;
		this.assetName = assetName;
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
		data.put("asset_name", assetName);
		return data;
	}

}
