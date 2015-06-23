package com.zeekie.stock.entity;

public class StatisticsDO {
	// 当日服务费统计
	private Float serviceCharge = 0.00f;
	// 当日用户提出统计
	private Float commission = 0.00f;
	// 当日系统财务优化
	private Float financialOptimization = 0.00f;
	// 保证金
	private Float bond = 0.00f;
	// 欠债总和
	private Float debt = 0.00f;
	//盈亏金额
	private Float profit = 0.00f;
	//钱包余额
	private Float balance = 0.00f;
	
	/**
	 * @return the debt
	 */
	public Float getDebt() {
		return debt;
	}

	/**
	 * @param debt the debt to set
	 */
	public void setDebt(Float debt) {
		this.debt = debt;
	}

	/**
	 * @return the profit
	 */
	public Float getProfit() {
		return profit;
	}

	/**
	 * @param profit the profit to set
	 */
	public void setProfit(Float profit) {
		this.profit = profit;
	}

	/**
	 * @return the balance
	 */
	public Float getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public Float getBond() {
		return bond;
	}

	public void setBond(Float bond) {
		this.bond = bond;
	}

	// 日期
	private String transactionDay;

	public Float getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(Float serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public Float getCommission() {
		return commission;
	}

	public void setCommission(Float commission) {
		this.commission = commission;
	}

	public Float getFinancialOptimization() {
		return financialOptimization;
	}

	public void setFinancialOptimization(Float financialOptimization) {
		this.financialOptimization = financialOptimization;
	}

	public String getTransactionDay() {
		return transactionDay;
	}

	public void setTransactionDay(String transactionDay) {
		this.transactionDay = transactionDay;
	}

}
