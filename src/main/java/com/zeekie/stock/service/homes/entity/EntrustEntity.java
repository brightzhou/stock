package com.zeekie.stock.service.homes.entity;

public class EntrustEntity extends BaseEntity {

	private String entrust_no;

	private String batch_no;
	// 证券代码
	private String stockCode;
	// 交易市场 1：上交所 2：生交所
	private String exchangeType;
	// 委托方向
	private String entrustDirection;
	// 委托价格
	private String entrustPrice;
	// 委托数量
	private String entrustAmount;

	/**
	 * @return the entrust_no
	 */
	public String getEntrust_no() {
		return entrust_no;
	}

	/**
	 * @param entrust_no
	 *            the entrust_no to set
	 */
	public void setEntrust_no(String entrust_no) {
		this.entrust_no = entrust_no;
	}

	/**
	 * @return the batch_no
	 */
	public String getBatch_no() {
		return batch_no;
	}

	/**
	 * @param batch_no
	 *            the batch_no to set
	 */
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

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
	 * @return the exchangeType
	 */
	public String getExchangeType() {
		return exchangeType;
	}

	/**
	 * @param exchangeType
	 *            the exchangeType to set
	 */
	public void setExchangeType(String exchangeType) {
		this.exchangeType = exchangeType;
	}

	/**
	 * @return the entrustDirection
	 */
	public String getEntrustDirection() {
		return entrustDirection;
	}

	/**
	 * @param entrustDirection
	 *            the entrustDirection to set
	 */
	public void setEntrustDirection(String entrustDirection) {
		this.entrustDirection = entrustDirection;
	}

	/**
	 * @return the entrustPrice
	 */
	public String getEntrustPrice() {
		return entrustPrice;
	}

	/**
	 * @param entrustPrice
	 *            the entrustPrice to set
	 */
	public void setEntrustPrice(String entrustPrice) {
		this.entrustPrice = entrustPrice;
	}

	/**
	 * @return the entrustAmount
	 */
	public String getEntrustAmount() {
		return entrustAmount;
	}

	/**
	 * @param entrustAmount
	 *            the entrustAmount to set
	 */
	public void setEntrustAmount(String entrustAmount) {
		this.entrustAmount = entrustAmount;
	}

	public void setIndividualParam(String exchangeType, String entrustPrice,
			String entrustAmount, String entrustDirection, String stockCode) {
		this.exchangeType = exchangeType;
		this.entrustPrice = entrustPrice;
		this.entrustAmount = entrustAmount;
		this.entrustDirection = entrustDirection;
		this.stockCode = stockCode;
	}

}
