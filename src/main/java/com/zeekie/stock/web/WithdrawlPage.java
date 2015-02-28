package com.zeekie.stock.web;

import sitong.thinker.common.page.PageQuery;

public class WithdrawlPage extends PageQuery {

	private String depositType;

	private String nickname;

	public WithdrawlPage(long pageIndex, long pageSize, String sortField,
			String sortOrder, String nickname, String depositType) {
		super(pageIndex, pageSize, sortField, sortOrder);
		this.depositType = depositType;
		this.nickname = nickname;
	}

	/**
	 * @return the depositType
	 */
	public String getDepositType() {
		return depositType;
	}

	/**
	 * @param depositType
	 *            the depositType to set
	 */
	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
