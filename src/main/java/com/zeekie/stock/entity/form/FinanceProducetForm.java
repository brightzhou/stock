package com.zeekie.stock.entity.form;

public class FinanceProducetForm {
	
	private Long id;
	
	/**
	 * 单号
	 */
	private String ticket;
	
	/**
	 * 当前收益
	 */
	private String currentIncome;

	/**
	 * 產品代碼
	 */
	private String productCode;

	/**
	 * 理财产品
	 */
	private String financeProduct;

	/**
	 * 年化收益
	 */
	private String annualIncome;

	/**
	 * 额度
	 */
	private String financeLimit;

	/**
	 * 起息日
	 */
	private String carryDate;

	/**
	 * 结息日
	 */
	private String settleDate;

	/**
	 * 收益
	 */
	private String income;

	/**
	 * 用户id
	 */
	private String userId;
	
	/**
	 * 理财协议
	 */
	private String financeProtcol;
	
	

	/**
	 * @return the financeProtcol
	 */
	public String getFinanceProtcol() {
		return financeProtcol;
	}

	/**
	 * @param financeProtcol the financeProtcol to set
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
	 * @param ticket the ticket to set
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	/**
	 * @return the currentIncome
	 */
	public String getCurrentIncome() {
		return currentIncome;
	}

	/**
	 * @param currentIncome the currentIncome to set
	 */
	public void setCurrentIncome(String currentIncome) {
		this.currentIncome = currentIncome;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
		this.income = income;
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

}
