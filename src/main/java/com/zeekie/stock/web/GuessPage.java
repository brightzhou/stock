package com.zeekie.stock.web;

import sitong.thinker.common.page.PageQuery;

;

public class GuessPage extends PageQuery {

	private String date;

	private String stockCode;

	public GuessPage(long pageIndex, long pageSize, String sortField,
			String sortOrder, String date) {
		super(pageIndex, pageSize, sortField, sortOrder);
		this.date = date;
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

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
}
