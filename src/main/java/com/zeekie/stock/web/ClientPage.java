package com.zeekie.stock.web;

import sitong.thinker.common.page.PageQuery;

public class ClientPage extends PageQuery {

	private static final long serialVersionUID = 2767775110683210541L;

	private String nickname;

	public ClientPage(long pageIndex, long pageSize, String sortField,
			String sortOrder, String nickname) {
		super(pageIndex, pageSize, sortField, sortOrder);
		this.nickname = nickname;
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
