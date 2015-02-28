package com.zeekie.stock.service.homes;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 资金划转
 * 
 * @author zeekie
 *
 */
public class StockAssetMove extends AStockTrade {

	/**
	 * 账户编号
	 */
	private String fundAccount;

	/**
	 * 组合编号
	 */
	private String combineId;

	/**
	 * 目标组合编号
	 * 
	 */
	private String targetCombineId;

	/**
	 * 发生金额
	 */
	private String fund;

	public StockAssetMove(String fundAccount, String combineId,
			String targetCombineId, String fund) {
		this.fundAccount = fundAccount;
		this.combineId = combineId;
		this.targetCombineId = targetCombineId;
		this.fund = fund;
	}

	public StockAssetMove() {
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
		data.put("target_combine_id", targetCombineId);
		data.put("occur_balance", fund);
		data.put("busin_opflag", "107");
		return data;
	}

}
