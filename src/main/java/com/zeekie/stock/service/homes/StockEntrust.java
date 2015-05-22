package com.zeekie.stock.service.homes;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 普通委托
 * 
 * 此代码已经开过光，绝无bug
 * 
 * @author zeekie
 * 
 */
public class StockEntrust extends AStockTrade {

	private String operatorNo;

	private String fundAccount;

	private String combineId;

	private String exchangeType = "1";

	private String stockCode;

	private String entrustDirection = "1";

	private String ampriceType = "0";

	private String entrustAmount;

	private String entrustPrice;

	public StockEntrust(String fundAccount, String combineId,
			String operatorNo, String stockCode, String entrustAmount,
			String entrustPrice) {
		this.fundAccount = fundAccount;
		this.combineId = combineId;
		this.operatorNo = operatorNo;
		this.stockCode = stockCode;
		this.entrustAmount = entrustAmount;
		this.entrustPrice = entrustPrice;
	}

	public StockEntrust() {
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
		data.put("fund_account", fundAccount);
		data.put("combine_id", combineId);
		data.put("exchange_type", exchangeType);
		data.put("stock_code", stockCode);
		data.put("entrust_direction", entrustDirection);
		data.put("amprice_type", ampriceType);
		data.put("entrust_amount", entrustAmount);
		data.put("entrust_price", entrustPrice);
		return data;
	}

}
