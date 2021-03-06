package com.zeekie.stock.entity;

public class WarnLineDO {

	private String operateId;

	private String nickname;

	private Float actualAsset;

	private Float warnFund;

	private Float stopFund;

	private String phone;

	private String ticket;

	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the stopFund
	 */
	public String getStopFund() {
		return stopFund + "";
	}

	/**
	 * @param stopFund
	 *            the stopFund to set
	 */
	public void setStopFund(Float stopFund) {
		this.stopFund = stopFund;
	}

	/**
	 * @return the ticket
	 */
	public String getTicket() {
		return ticket;
	}

	/**
	 * @param ticket
	 *            the ticket to set
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the operateId
	 */
	public String getOperateId() {
		return operateId;
	}

	/**
	 * @param operateId
	 *            the operateId to set
	 */
	public void setOperateId(String operateId) {
		this.operateId = operateId;
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
	 * @return the actualAsset
	 */
	public String getActualAsset() {
		return actualAsset + "";
	}

	/**
	 * @param actualAsset
	 *            the actualAsset to set
	 */
	public void setActualAsset(Float actualAsset) {
		this.actualAsset = actualAsset;
	}

	/**
	 * @return the warnFund
	 */
	public String getWarnFund() {
		return warnFund + "";
	}

	/**
	 * @param warnFund
	 *            the warnFund to set
	 */
	public void setWarnFund(Float warnFund) {
		this.warnFund = warnFund;
	}

}
