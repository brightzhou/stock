package com.zeekie.stock.entity;

public class HistoryFinanceDO {

	/**
	 * 单号
	 */
	private String ticket;

	/**
	 * 额度
	 */
	private Float financeLimit;

	/**
	 * 收益
	 */
	private Float income;

	/**
	 * 当前收益
	 */
	private Float currentIncome;

	/**
	 * 起息日
	 */
	private String carryDate;

	/**
	 * 结息日
	 */
	private String settleDate;

	/**
	 * 年化收益
	 */
	private Float annualIncome;
	/**
	 * 理财协议
	 */
	private String financeProtcol;

	/**
	 * 剩余天数
	 */
	private String num;

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
	 * @return the financeProtcol
	 */
	public String getFinanceProtcol() {
		return financeProtcol;
	}

	/**
	 * @param financeProtcol
	 *            the financeProtcol to set
	 */
	public void setFinanceProtcol(String financeProtcol) {
		this.financeProtcol = financeProtcol;
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

	/**
	 * @return the currentIncome
	 */
	public Float getCurrentIncome() {
		return currentIncome;
	}

	/**
	 * @param currentIncome
	 *            the currentIncome to set
	 */
	public void setCurrentIncome(Float currentIncome) {
		this.currentIncome = currentIncome;
	}

	/**
	 * @return the carryDate
	 */
	public String getCarryDate() {
		return carryDate;
	}

	/**
	 * @param carryDate
	 *            the carryDate to set
	 */
	public void setCarryDate(String carryDate) {
		this.carryDate = carryDate;
	}

	/**
	 * @return the settleDate
	 */
	public String getSettleDate() {
		return settleDate;
	}

	/**
	 * @param settleDate
	 *            the settleDate to set
	 */
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	/**
	 * @return the annualIncome
	 */
	public Float getAnnualIncome() {
		return annualIncome;
	}

	/**
	 * @param annualIncome
	 *            the annualIncome to set
	 */
	public void setAnnualIncome(Float annualIncome) {
		this.annualIncome = annualIncome;
	}

	public HistoryFinanceDO() {
		// TODO Auto-generated constructor stub
	}
}
