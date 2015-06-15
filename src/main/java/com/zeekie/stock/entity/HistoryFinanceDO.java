package com.zeekie.stock.entity;

import com.zeekie.stock.util.StringUtil;

public class HistoryFinanceDO {

	/**
	 * 单号
	 */
	private String ticket;

	/**
	 * 额度
	 */
	private String financeLimit;

	/**
	 * 收益
	 */
	private String income;

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
	private String annualIncome;
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
	public String getFinanceLimit() {
		return financeLimit;
	}

	/**
	 * @param financeLimit
	 *            the financeLimit to set
	 */
	public void setFinanceLimit(String financeLimit) {
		this.financeLimit = financeLimit;
	}

	/**
	 * @return the income
	 */
	public String getIncome() {
		return income;
	}

	/**
	 * @param income
	 *            the income to set
	 */
	public void setIncome(String income) {
		this.income = StringUtil.subZeroAndDot(Float.parseFloat(income));
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
	public String getAnnualIncome() {
		return annualIncome;
	}

	/**
	 * @param annualIncome
	 *            the annualIncome to set
	 */
	public void setAnnualIncome(String annualIncome) {
		this.annualIncome = String.format("%.4f",Float.parseFloat(annualIncome));
	}

	public HistoryFinanceDO() {
		// TODO Auto-generated constructor stub
	}
}
