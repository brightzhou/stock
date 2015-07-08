package com.zeekie.stock.entity;

public class AddCashErrorDO {

	private String nickname;

	private Float cash;

	private String reason;

	private String dates;

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
	 * @return the cash
	 */
	public Float getCash() {
		return cash;
	}

	/**
	 * @param cash
	 *            the cash to set
	 */
	public void setCash(Float cash) {
		this.cash = cash;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * @return the dates
	 */
	public String getDates() {
		return dates;
	}

	/**
	 * @param dates
	 *            the dates to set
	 */
	public void setDates(String dates) {
		this.dates = dates;
	}

}
