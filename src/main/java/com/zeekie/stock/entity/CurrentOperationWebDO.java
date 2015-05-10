package com.zeekie.stock.entity;

public class CurrentOperationWebDO extends BaseUserDO{

	private String id;

	private String userId;

	private String status;

	private Float stopLine;

	private String profitPercent;

	private Float warnLine;

	private Float actualCash;

	private String tradeAccount;

	private String ticket;

	public CurrentOperationWebDO() {
		// TODO Auto-generated constructor stub
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
	 * @return the profitPercent
	 */
	public String getProfitPercent() {
		return profitPercent;
	}

	/**
	 * @param profitPercent
	 *            the profitPercent to set
	 */
	public void setProfitPercent(String profitPercent) {
		this.profitPercent = profitPercent;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
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

}
