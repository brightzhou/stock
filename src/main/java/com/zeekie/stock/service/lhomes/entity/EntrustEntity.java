package com.zeekie.stock.service.lhomes.entity;

public class EntrustEntity extends AHomesEntity {

	/**
	 * 客户密码
	 */
	private String password;

	/**
	 * 交易类别
	 */
	private String exchangeType;

	/**
	 * 委托买卖
	 */
	private String entrustBs;

	/**
	 * 股票代码
	 */
	private String stockCode;

	/**
	 * 委托类别(数据字典)
	 */
	private String entrustType;

	/**
	 * 委托属性(数据字典)
	 */
	private String entrustProp;

	/**
	 * 委托价格
	 */
	private String entrustPrice;

	/**
	 * 委托数量
	 */
	private String entrustAmount;

	/**
	 * 外部流水号
	 */
	private String outerSerialNo;

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * @return the entrustBs
	 */
	public String getEntrustBs() {
		return entrustBs;
	}

	/**
	 * @param entrustBs
	 *            the entrustBs to set
	 */
	public void setEntrustBs(String entrustBs) {
		this.entrustBs = entrustBs;
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
	 * @return the entrustType
	 */
	public String getEntrustType() {
		return entrustType;
	}

	/**
	 * @param entrustType
	 *            the entrustType to set
	 */
	public void setEntrustType(String entrustType) {
		this.entrustType = entrustType;
	}

	/**
	 * @return the entrustProp
	 */
	public String getEntrustProp() {
		return entrustProp;
	}

	/**
	 * @param entrustProp
	 *            the entrustProp to set
	 */
	public void setEntrustProp(String entrustProp) {
		this.entrustProp = entrustProp;
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

	/**
	 * @return the outerSerialNo
	 */
	public String getOuterSerialNo() {
		return outerSerialNo;
	}

	/**
	 * @param outerSerialNo
	 *            the outerSerialNo to set
	 */
	public void setOuterSerialNo(String outerSerialNo) {
		this.outerSerialNo = outerSerialNo;
	}

	public EntrustEntity() {
		// TODO Auto-generated constructor stub
	}

}
