package com.zeekie.stock.entity;

public class UserBankDO extends BankInfoDO {

	private String nickname;

	private String truename;

	private String id;

	public UserBankDO() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the truename
	 */
	public String getTruename() {
		return truename;
	}

	/**
	 * @param truename
	 *            the truename to set
	 */
	public void setTruename(String truename) {
		this.truename = truename;
	}

}
