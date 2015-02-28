package com.zeekie.stock.web;

import sitong.thinker.common.page.PageQuery;

public class TotalFundPage extends PageQuery {

	private String fundAccount;

	public TotalFundPage(long pageIndex, long pageSize, String sortField,
			String sortOrder, String fundAccount) {
		super(pageIndex, pageSize, sortField, sortOrder);
		this.fundAccount = fundAccount;
	}

	/**
	 * @return the fundAccount
	 */
	public String getFundAccount() {
		return fundAccount;
	}

	/**
	 * @param fundAccount
	 *            the fundAccount to set
	 */
	public void setFundAccount(String fundAccount) {
		this.fundAccount = fundAccount;
	}

}
