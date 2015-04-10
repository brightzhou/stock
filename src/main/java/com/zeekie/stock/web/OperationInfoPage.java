package com.zeekie.stock.web;

import sitong.thinker.common.page.PageQuery;

public class OperationInfoPage extends PageQuery {

	private String nickname;

	private String range;

	public OperationInfoPage(long pageIndex, long pageSize, String sortField,
			String sortOrder, String nickname, String range) {
		super(pageIndex, pageSize, sortField, sortOrder);
		this.nickname = nickname;
		this.range = range;
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

	/**
	 * @return the range
	 */
	public String getRange() {
		return range;
	}

	/**
	 * @param range
	 *            the range to set
	 */
	public void setRange(String range) {
		this.range = range;
	}

}
