package com.zeekie.stock.service.lhomes.entity;

public class HomsEntity400 extends AHomesEntity{

	private String stockCode;

	private String exchangeType;

	/**
	 * @return the stockCode
	 */
	public String getStockCode() {
		return stockCode;
	}

	/**
	 * @param stockCode the stockCode to set
	 */
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	/**
	 * @return the exchangeType
	 */
	public String getExchangeType() {
		return exchangeType;
	}

	/**
	 * @param exchangeType the exchangeType to set
	 */
	public void setExchangeType(String exchangeType) {
		this.exchangeType = exchangeType;
	}

	public HomsEntity400(String stockCode, String exchangeType) {
		super();
		this.stockCode = stockCode;
		this.exchangeType = exchangeType;
	}
	

}
