package com.zeekie.stock.service.lhomes.entity;

import java.util.HashMap;
import java.util.Map;

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;
import com.zeekie.stock.Constants;
import com.zeekie.stock.util.StringUtil;

/**
 * 
 * 普通委托
 * 
 * 此代码已经开过光，绝无bug
 * 
 * @author zeekie
 * 
 */
public class HomesEntrust extends AHomesEntity {

	private String exchangeType;

	private String stockCode;

	private String entrustDirection;

	private String entrustAmount;

	private String entrustPrice;

	private String entrustbs;

	public HomesEntrust(String investAccount, String clientNo,
			String exchangeType, String stockCode, String entrustDirection,
			String entrustAmount, String entrustPrice, String entrustbs) {
		super(investAccount, clientNo);
		this.exchangeType = exchangeType;
		this.stockCode = stockCode;
		this.entrustDirection = entrustDirection;
		this.entrustAmount = entrustAmount;
		this.entrustPrice = entrustPrice;
		this.entrustbs = entrustbs;
	}

	/**
	 * @return the entrustbs
	 */
	public String getEntrustbs() {
		return entrustbs;
	}

	/**
	 * @param entrustbs
	 *            the entrustbs to set
	 */
	public void setEntrustbs(String entrustbs) {
		this.entrustbs = entrustbs;
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

}
