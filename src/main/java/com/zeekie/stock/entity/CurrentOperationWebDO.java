package com.zeekie.stock.entity;

public class CurrentOperationWebDO {

	private String id;

	private String nickname;

	private String status;

	private Float stopLine;

	private Float warnLine;

	private Float actualCash;

	private String tradeAccount;

	public CurrentOperationWebDO() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the tradeAccount
	 */
	public String getTradeAccount() {
		return tradeAccount;
	}

	/**
	 * @param tradeAccount
	 *            the tradeAccount to set
	 */
	public void setTradeAccount(String tradeAccount) {
		this.tradeAccount = tradeAccount;
	}

	/**
	 * @return the stopLine
	 */
	public Float getStopLine() {
		return stopLine;
	}

	/**
	 * @param stopLine
	 *            the stopLine to set
	 */
	public void setStopLine(Float stopLine) {
		this.stopLine = stopLine;
	}

	/**
	 * @return the warnLine
	 */
	public Float getWarnLine() {
		return warnLine;
	}

	/**
	 * @param warnLine
	 *            the warnLine to set
	 */
	public void setWarnLine(Float warnLine) {
		this.warnLine = warnLine;
	}

	/**
	 * @return the actualCash
	 */
	public Float getActualCash() {
		return actualCash;
	}

	/**
	 * @param actualCash
	 *            the actualCash to set
	 */
	public void setActualCash(Float actualCash) {
		this.actualCash = actualCash;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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

}
