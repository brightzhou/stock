package com.zeekie.stock.web;

import sitong.thinker.common.page.PageQuery;

public class MoveToRefereePage extends PageQuery {

	private String nickname;

	private String referee;

	public MoveToRefereePage(long pageIndex, long pageSize, String sortField,
			String sortOrder, String nickname, String referee) {
		super(pageIndex, pageSize, sortField, sortOrder);
		this.nickname = nickname;
		this.referee = referee;
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
	 * @return the referee
	 */
	public String getReferee() {
		return referee;
	}

	/**
	 * @param referee
	 *            the referee to set
	 */
	public void setReferee(String referee) {
		this.referee = referee;
	}

}
