package com.zeekie.stock.entity;

public class WithdrawlDO {

	private String id;

	private String nickname;

	private String truename;

	private String idCard;

	private String bank;

	private String bankNO;

	private String cash;

	private String time;

	private String status;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	/**
	 * @return the idCard
	 */
	public String getIdCard() {
		return idCard;
	}

	/**
	 * @param idCard
	 *            the idCard to set
	 */
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/**
	 * @return the bank
	 */
	public String getBank() {
		return bank;
	}

	/**
	 * @param bank
	 *            the bank to set
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * @return the bankNO
	 */
	public String getBankNO() {
		return bankNO;
	}

	/**
	 * @param bankNO
	 *            the bankNO to set
	 */
	public void setBankNO(String bankNO) {
		this.bankNO = bankNO;
	}

	/**
	 * @return the cash
	 */
	public String getCash() {
		return cash;
	}

	/**
	 * @param cash
	 *            the cash to set
	 */
	public void setCash(String cash) {
		this.cash = cash;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

}
