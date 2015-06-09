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
	private long financeTotalLimit;

	/**
	 * 年化收益
	 */
	private long annualIncome;

	/**
	 * 期限
	 */
	private String expireDay;

	public FinanceProductDO() {
		// TODO Auto-generated constructor stub
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
	public long getFinanceTotalLimit() {
		return financeTotalLimit;
	}

	/**
	 * @param financeTotalLimit
	 *            the financeTotalLimit to set
	 */
	public void setFinanceTotalLimit(long financeTotalLimit) {
		this.financeTotalLimit = financeTotalLimit;
	}

	/**
	 * @return the annualIncome
	 */
	public long getAnnualIncome() {
		return annualIncome;
	}

	/**
	 * @param annualIncome
	 *            the annualIncome to set
	 */
	public void setAnnualIncome(long annualIncome) {
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

}
