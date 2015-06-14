package com.zeekie.stock.web;

import sitong.thinker.common.page.PageQuery;

public class FinancePage extends PageQuery {

	private static final long serialVersionUID = 2767775110683210541L;

	private String date;

	public FinancePage(long pageIndex, long pageSize, String sortField,
			String sortOrder, String date) {
		super(pageIndex, pageSize, sortField, sortOrder);
		this.date = date;
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
