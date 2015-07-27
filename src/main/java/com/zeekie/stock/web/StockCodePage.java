package com.zeekie.stock.web;

import sitong.thinker.common.page.PageQuery;

public class StockCodePage extends PageQuery {

	private String stockCode;

	public StockCodePage(long curPage, long pageSize, String sortField,
			String sortOrder, String stockCode) {
		super(curPage, pageSize, sortField, sortOrder);
		this.stockCode = stockCode;
	}

	/**
	 * @return the stockCode
	 */
	public String getStockCode() {
		return stockCode;
	}

	/**
	 * @param stockCode
	 *            the stockCode to set
	 */
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

}
