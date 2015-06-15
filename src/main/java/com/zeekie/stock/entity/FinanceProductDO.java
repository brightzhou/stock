package com.zeekie.stock.entity;

public class FinanceProductDO {

	/**
	 * 產品代碼
	 */
	private String productCode;

	/**
	 * 理财产品
	 */
	private String financeProduct;

	/**
	 * 理财额度
	 */
	private String financeTotalLimit;

	/**
	 * 年化收益
	 */
	private String annualIncome;

	/**
	 * 期限
	 */
	private String expireDay;

	/**
	 * 最大购买额度
	 */
	private String maxLimit;

	/**
	 * 最小购买额度
	 */
	private String minLimit;

	/**
	 * 起息日
	 */
	private String carryDate;

	/**
	 * 解析日
	 */
	private String settleDate;

	/*
	 * 参与人数
	 */
	private String num;

	/*
	 * 剩余天数
	 */
	private String leaveDays;

	public FinanceProductDO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the leaveDays
	 */
	public String getLeaveDays() {
		return leaveDays;
	}

	/**
	 * @param leaveDays
	 *            the leaveDays to set
	 */
	public void setLeaveDays(String leaveDays) {
		this.leaveDays = leaveDays;
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
	 * @return the productCode
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode
	 *            the productCode to set
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return the financeProduct
	 */
	public String getFinanceProduct() {
		return financeProduct;
	}

	/**
	 * @param financeProduct
	 *            the financeProduct to set
	 */
	public void setFinanceProduct(String financeProduct) {
		this.financeProduct = financeProduct;
	}

	/**
	 * @return the financeTotalLimit
	 */
	public String getFinanceTotalLimit() {
		return financeTotalLimit;
	}

	/**
	 * @param financeTotalLimit
	 *            the financeTotalLimit to set
	 */
	public void setFinanceTotalLimit(String financeTotalLimit) {
		this.financeTotalLimit = financeTotalLimit;
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
		this.annualIncome = String.format("%.4f",
				Float.parseFloat(annualIncome));
	}

	/**
	 * @return the expireDay
	 */
	public String getExpireDay() {
		return expireDay;
	}

	/**
	 * @param expireDay
	 *            the expireDay to set
	 */
	public void setExpireDay(String expireDay) {
		this.expireDay = expireDay;
	}

	/**
	 * @return the maxLimit
	 */
	public String getMaxLimit() {
		return maxLimit;
	}

	/**
	 * @param maxLimit
	 *            the maxLimit to set
	 */
	public void setMaxLimit(String maxLimit) {
		this.maxLimit = maxLimit;
	}

	/**
	 * @return the minLimit
	 */
	public String getMinLimit() {
		return minLimit;
	}

	/**
	 * @param minLimit
	 *            the minLimit to set
	 */
	public void setMinLimit(String minLimit) {
		this.minLimit = minLimit;
	}

}
