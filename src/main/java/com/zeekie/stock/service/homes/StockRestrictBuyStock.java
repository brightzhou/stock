package com.zeekie.stock.service.homes;

import java.util.HashMap;
import java.util.Map;

public class StockRestrictBuyStock extends AStockTrade {

	private String operatorNo;
	// 股票操作权限
	private String stockOpright;
	// 操作类别
	private String operateFlag;

	public StockRestrictBuyStock(String operatorNo, String stockOpright,
			String operateFlag) {
		this.operatorNo = operatorNo;
		this.stockOpright = stockOpright;
		this.operateFlag = operateFlag;
	}

	public StockRestrictBuyStock() {
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
		data.put("stock_opright", stockOpright);
		data.put("busin_opflag", operateFlag);
		return data;
	}

}
