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
	private Float financeTotalLimit;

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
	private Float maxLimit;

	/**
	 * 最小购买额度
	 */
	private Float minLimit;

	/**
	 * 起息日
	 */
	private String carryDate;

	/**
	 * 解析日
	 */
	private String settleDate;

	public FinanceProductDO() {
		// TODO Auto-generated constructor stub
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
	public Float getFinanceTotalLimit() {
		return financeTotalLimit;
	}

	/**
	 * @param financeTotalLimit
	 *            the financeTotalLimit to set
	 */
	public void setFinanceTotalLimit(Float financeTotalLimit) {
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
		this.annualIncome = annualIncome;
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
	public Float getMaxLimit() {
		return maxLimit;
	}

	/**
	 * @param maxLimit
	 *            the maxLimit to set
	 */
	public void setMaxLimit(Float maxLimit) {
		this.maxLimit = maxLimit;
	}

	/**
	 * @return the minLimit
	 */
	public Float getMinLimit() {
		return minLimit;
	}

	/**
	 * @param minLimit
	 *            the minLimit to set
	 */
	public void setMinLimit(Float minLimit) {
		this.minLimit = minLimit;
	}

}
