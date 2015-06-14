package com.zeekie.stock.entity;

public class FinanceIncomeDO {

	private String nickname;

	private Float income;

	private Float financeLimit;

	private String num;

	private String ticket;
	

	/**
	 * @return the ticket
	 */
	public String getTicket() {
		return ticket;
	}

	/**
	 * @param ticket the ticket to set
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	/**
	 * @return the financeLimit
	 */
	public Float getFinanceLimit() {
		return financeLimit;
	}

	/**
	 * @param financeLimit
	 *            the financeLimit to set
	 */
	public void setFinanceLimit(Float financeLimit) {
		this.financeLimit = financeLimit;
	}

	/**
	 * @return the num
	 */
	public String getNum() {
		return num;
	}

	/**
	 * @param num
	 *            the num to set
	 */
	public void setNum(String num) {
		this.num = num;
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
	 * @return the income
	 */
	public Float getIncome() {
		return income;
	}

	/**
	 * @param income
	 *            the income to set
	 */
	public void setIncome(Float income) {
		this.income = income;
	}

	public FinanceIncomeDO() {
		// TODO Auto-generated constructor stub
	}

}
