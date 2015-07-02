package com.zeekie.stock.service.lhomes.entity;

public class HomsEntity103 {

	private String stockCode;

	private String currentAmount;

	private String enableAmount;

	private String costBalance;

	private String marketValue;

	/**
	 * @return the stockCode
	 */
	public String getStockCode() {
		return stockCode;
	}

	/**
	 * @param stockCode
	 *            the stockCode to set
	 */
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	/**
	 * @return the currentAmount
	 */
	public String getCurrentAmount() {
		return currentAmount;
	}

	/**
	 * @param currentAmount
	 *            the currentAmount to set
	 */
	public void setCurrentAmount(String currentAmount) {
		this.currentAmount = currentAmount;
	}

	/**
	 * @return the enableAmount
	 */
	public String getEnableAmount() {
		return enableAmount;
	}

	/**
	 * @param enableAmount
	 *            the enableAmount to set
	 */
	public void setEnableAmount(String enableAmount) {
		this.enableAmount = enableAmount;
	}

	/**
	 * @return the costBalance
	 */
	public String getCostBalance() {
		return costBalance;
	}

	/**
	 * @param costBalance
	 *            the costBalance to set
	 */
	public void setCostBalance(String costBalance) {
		this.costBalance = costBalance;
	}

	/**
	 * @return the marketValue
	 */
	public String getMarketValue() {
		return marketValue;
	}

	/**
	 * @param marketValue
	 *            the marketValue to set
	 */
	public void setMarketValue(String marketValue) {
		this.marketValue = marketValue;
	}

	public HomsEntity103(String stockCode, String currentAmount,
			String enableAmount, String costBalance, String marketValue) {
		super();
		this.stockCode = stockCode;
		this.currentAmount = currentAmount;
		this.enableAmount = enableAmount;
		this.costBalance = costBalance;
		this.marketValue = marketValue;
	}

}
