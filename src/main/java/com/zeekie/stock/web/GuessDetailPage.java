package com.zeekie.stock.web;

import sitong.thinker.common.page.PageQuery;

;

public class GuessDetailPage extends PageQuery {

	private String bidCode;

	public GuessDetailPage(long pageIndex, long pageSize, String sortField,
			String sortOrder, String date) {
		super(pageIndex, pageSize, sortField, sortOrder);
		this.bidCode = date;
	}

	/**
	 * @return the bidCode
	 */
	public String getBidCode() {
		return bidCode;
	}

	/**
	 * @param bidCode
	 *            the bidCode to set
	 */
	public void setBidCode(String bidCode) {
		this.bidCode = bidCode;
	}

}
